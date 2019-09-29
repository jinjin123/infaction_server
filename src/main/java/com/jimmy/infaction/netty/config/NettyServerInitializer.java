package com.jimmy.infaction.netty.config;

import com.jimmy.infaction.common.Handler.ReadAllBytebufInboundHandler;
import com.jimmy.infaction.common.Handler.length.MyLengthFieldBasedFrameDecoder;
import com.jimmy.infaction.common.Handler.length.MyLengthFieldPrepender;
import com.jimmy.infaction.netty.handle.NewProxyConnectionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author jimmy on 2019/9/29 20:05
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {
	public static NettyServerInitializer instance=new NettyServerInitializer();

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new ReadAllBytebufInboundHandler());
		//======================
		//length的粘包解决
		channel.pipeline().addLast(new MyLengthFieldBasedFrameDecoder());
		channel.pipeline().addLast(new MyLengthFieldPrepender());
		//================================


//            //打印内容，debug用而已
//            channel.pipeline().addLast(new PrintByteBufHandler());
		channel.pipeline().addLast(new DefaultHttpRequestDecoder());
		channel.pipeline().addLast(new NewProxyConnectionHandler(channel));
	}
}
