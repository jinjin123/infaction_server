package com.jimmy.infaction.netty.config;

import com.jimmy.infaction.common.Handler.ReadAllBytebufInboundHandler;
import com.jimmy.infaction.netty.handle.ProxyConnenctionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author jimmy on 2019/9/29 21:41
 */
public  class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

	 public static NettyClientInitializer instance=new NettyClientInitializer();

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new ReadAllBytebufInboundHandler()   );
//            channel.pipeline().addLast(new FastJsonHttpMessageDecoder());
		channel.pipeline().addLast(new ProxyConnenctionHandler(channel));
	}
}