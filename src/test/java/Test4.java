/**
 * @author jimmy on 2019/9/28 18:49
 */
import java.net.InetSocketAddress;

		import io.netty.bootstrap.Bootstrap;
		import io.netty.bootstrap.ServerBootstrap;
		import io.netty.buffer.ByteBuf;
		import io.netty.channel.Channel;
		import io.netty.channel.ChannelFuture;
		import io.netty.channel.ChannelFutureListener;
		import io.netty.channel.ChannelHandlerContext;
		import io.netty.channel.ChannelInitializer;
		import io.netty.channel.SimpleChannelInboundHandler;
		import io.netty.channel.nio.NioEventLoopGroup;
		import io.netty.channel.socket.nio.NioServerSocketChannel;
		import io.netty.channel.socket.nio.NioSocketChannel;
		import io.netty.handler.codec.string.StringDecoder;
		import io.netty.handler.codec.string.StringEncoder;

public class Test4 {
	public static void main(String[] params) {
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup())
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<Channel>() {
					@Override
					protected void initChannel(Channel ch) throws Exception {
						// TODO Auto-generated method stub
//						ch.pipeline().addLast(new StringDecoder());
//						ch.pipeline().addLast(new StringEncoder());
						ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
							private ChannelHandlerContext innerCtx;
							ChannelFuture connectFuture;
							@Override
							public void channelActive(ChannelHandlerContext ctx)
									throws Exception {
								Bootstrap bootstrap = new Bootstrap();
								bootstrap.channel(NioSocketChannel.class).handler(
										new SimpleChannelInboundHandler<ByteBuf>() {
											//内层建立的连接，从这里接收内层的应答，在这里是服务端的应答
											@Override
											protected void channelRead0(
													ChannelHandlerContext ctx, ByteBuf in)
													throws Exception {
												innerCtx = ctx;

												byte[] dst = new byte[in.readableBytes()];
												in.readBytes(dst);

												System.out.println("Received data" + new String(dst));
											}

											@Override
											public void channelActive(ChannelHandlerContext ctx) throws Exception {
												System.out.println("链接服务"+ctx.channel().toString());
											}
										} );
								bootstrap.group(ctx.channel().eventLoop());//关键在这里。把外层channel的eventLoop挂接在内层上
								connectFuture = bootstrap.connect(
//                                new InetSocketAddress("192.168.60.49", 23456));
										new InetSocketAddress("localhost", 3213));
							}

							@Override
							protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
								// TODO Auto-generated method stub
								if (connectFuture.isDone()) {
									// do something with the data
									//channel并不共享，共享的是线程EventLoop，所以如果想向内层转发的话
									//需要持有内层的channel

									if (innerCtx != null && innerCtx.channel().isActive()) {
										innerCtx.writeAndFlush(msg);
									}
								}
							}
						});
						ChannelFuture future = bootstrap.bind(new InetSocketAddress(9999)).sync();
						future.addListener(new ChannelFutureListener() {
							@Override
							public void operationComplete(ChannelFuture channelFuture)
									throws Exception {
								if (channelFuture.isSuccess()) {
									System.out.println("Server bound 8080");
								} else {
									System.err.println("Bind attempt failed");
									channelFuture.cause().printStackTrace();
								}
							}
						});
					}
				});
	}
}
