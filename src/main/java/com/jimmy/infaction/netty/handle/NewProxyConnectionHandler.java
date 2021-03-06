package com.jimmy.infaction.netty.handle;

/**
 * @author jimmy on 2019/9/29 20:18
 */

import com.jimmy.infaction.common.Handler.ReadAllBytebufInboundHandler;
import com.jimmy.infaction.common.httpentity.HttpMethod;
import com.jimmy.infaction.common.httpentity.HttpRequest;
import com.jimmy.infaction.common.httpentity.HttpResponse;
import  com.jimmy.infaction.common.util.ExceptionUtil;
import com.jimmy.infaction.netty.server.NettyServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class NewProxyConnectionHandler extends ChannelInboundHandlerAdapter {
	private EventLoopGroup remoteLoopGroup = NettyServer.REMOTEWORKER;
	private SocketChannel localChannel = null;
	private SocketChannel remoteChannel = null;
	private String host = null;
	private int port = 80;
	private boolean isTunnel = false;
	private ChannelFuture hostConnectFuture;
	private static Logger logger = LoggerFactory.getLogger(NewProxyConnectionHandler.class);

	public NewProxyConnectionHandler(SocketChannel channel) {
		localChannel = channel;
	}


	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		boolean canWrite = ctx.channel().isWritable();
		logger.warn(ctx.channel() + " 可写性：" + canWrite);
		//流量控制，不允许继续读
		remoteChannel.config().setAutoRead(canWrite);
		super.channelWritabilityChanged(ctx);
	}

	public void channelRead(ChannelHandlerContext localCtx, Object msg) throws Exception {
		HttpRequest request = (HttpRequest) msg;
		if (host == null && request.getHost()!=null&&!request.getHost().equals("")) {
			host = request.getHost();
			port = request.getPort();
			isTunnel = HttpMethod.CONNECT.equals(request.getMethod());
		}
		//检查这个请求是否有效
		if (host != null) {
			if (remoteChannel != null) {
				write2Target(request);
			} else {
				if (hostConnectFuture == null) {//进行连接
					hostConnectFuture = connectTarget();
				}
				if(!HttpMethod.CONNECT.equals(request.getMethod())){
					hostConnectFuture.addListener(future -> {
						if(future.isSuccess()){
							write2Target(request);
						}
					});
				}
			}
		} else {
			ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer();
			localChannel.writeAndFlush(buf.writeBytes(HttpResponse.ERROR503())).addListener(future -> {
				logger.warn("错误的第一次请求：没有指定host。关闭channel");
			});
		}
	}

	private void write2Target(HttpRequest request) throws UnsupportedEncodingException {
		ByteBuf buf=PooledByteBufAllocator.DEFAULT.buffer();
		if(isTunnel){
			byte[] body= request.getRequestBody();
			buf.writeBytes(body);
		}else{
			StringBuffer sb=new StringBuffer();
			if(request.getRequestLine()!=null){
				sb.append(request.getRequestLine());
				sb.append("\r\n");
			}
			List<HttpRequest.HttpRequestHeader> headers=request.getHeaders();
			if(headers!=null){
				for (int i = 0; i <headers.size() ; i++) {
					HttpRequest.HttpRequestHeader header=headers.get(i);
					sb.append(header.getKey());
					sb.append(": ");
					sb.append(header.getValue());
					sb.append("\r\n");
				}
				sb.append("\r\n");
			}
			buf.writeBytes(sb.toString().getBytes());
			if(request.getRequestBody()!=null){
				byte[] body= request.getRequestBody();
				buf.writeBytes(body);
			}
		}
		remoteChannel.writeAndFlush(buf).addListener(future -> {
			if(future.isSuccess()){
			}else{
				logger.warn(ExceptionUtil.getMessage(future.cause()));
			}
		});
	}

	private ChannelFuture connectTarget() {
		Bootstrap bootstrap = new Bootstrap();
//        Bootstrap astrill = new Bootstrap();
		bootstrap.group(remoteLoopGroup)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
				.handler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						remoteChannel = ch;
						ch.pipeline().addLast(new ReadAllBytebufInboundHandler());
						ch.pipeline().addLast(new NewProxyConnectionHandler.SendBack2ClientHandler());
					}
				});
//        ChannelFuture atr =astrill.connect("localhsot", 3213);
		ChannelFuture future=bootstrap.connect(host, port);
		future.addListener(future1 -> {
			if (future1.isSuccess()) {
				logger.info("连接成功: " + host + ":" + port);
				if (isTunnel) {
					ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer();
					localChannel.writeAndFlush(byteBuf.writeBytes(HttpResponse.ESTABLISHED())).addListener(future2 -> {
						if (future2.isSuccess()) {
							logger.info("success：通知隧道建立成功 " + localChannel.remoteAddress());
						} else {
							logger.warn("向" + localChannel.remoteAddress() + "写失败，异常信息如下：");
							logger.warn(ExceptionUtil.getMessage(future2.cause()));
						}
					});
				}
			} else {
				logger.error("连接失败: " + host + ":" + port);
				ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.buffer();
				localChannel.writeAndFlush(byteBuf.writeBytes(HttpResponse.ERROR503())).addListener((localFuture -> {
					localChannel.close();
				}));
			}
		});
		return future;
	}

	/**
	 * 当本channel被关闭时，及时关闭对应的另一个channel
	 * 出现异常和正常关闭都会导致本channel被关闭
	 *
	 * @param ctx
	 * @throws Exception
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		if (remoteChannel != null && remoteChannel.isActive()) {
			remoteChannel.writeAndFlush(PooledByteBufAllocator.DEFAULT.buffer()).addListener(future -> {
				remoteChannel.close().addListener(future1 -> {
					logger.info("返回0字节：browser关闭连接，因此关闭到webserver连接");
				});
			});
		}
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.error("出现异常：" + cause.getMessage() + "——" + ctx.channel().remoteAddress());
		logger.error(ExceptionUtil.getMessage(cause));
	}

	private class SendBack2ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
		private Logger logger = LoggerFactory.getLogger(NewProxyConnectionHandler.SendBack2ClientHandler.class);

		@Override
		public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
			boolean canWrite = ctx.channel().isWritable();
			logger.warn(ctx.channel() + " 可写性：" + canWrite);
			//流量控制，不允许继续读
			localChannel.config().setAutoRead(canWrite);
			super.channelWritabilityChanged(ctx);
		}

		@Override
		protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
			localChannel.writeAndFlush(byteBuf.retain()).addListener(future -> {
				if (future.isSuccess()) {
					logger.info("返回响应 " + byteBuf.writerIndex() + "字节 " + channelHandlerContext.channel().remoteAddress());
				} else {
					logger.warn("向" + channelHandlerContext.channel() + "写失败，异常信息如下：");
					logger.warn(ExceptionUtil.getMessage(future.cause()));
				}
			});
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			logger.error("出现异常：" + cause.getMessage() + "——" + ctx.channel());
			logger.error(ExceptionUtil.getMessage(cause));
		}

		/**
		 * 当本channel被关闭时，及时关闭对应的另一个channel
		 * 出现异常和正常关闭都会导致本channel被关闭
		 *
		 * @param ctx
		 * @throws Exception
		 */
		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			if (localChannel != null && localChannel.isActive()) {
				localChannel.writeAndFlush(PooledByteBufAllocator.DEFAULT.buffer()).addListener(future -> {
					localChannel.close().addListener(future1 -> {
						logger.info("返回0字节：webserver关闭连接，因此关闭到browser连接");
					});
				});
			}
			super.channelInactive(ctx);
		}
	}
}
