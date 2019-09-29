package com.jimmy.infaction.netty.server;

/**
 * @author jimmy on 2019/9/29 19:46
 */
import io.netty.channel.ChannelOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jimmy.infaction.netty.config.NettyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

public class NettyServer implements Runnable {
	private static Logger logger= LoggerFactory.getLogger(NettyServer.class);
	public static int serverport;
	//	private static int port=8080;
	public final static EventLoopGroup REMOTEWORKER =new NioEventLoopGroup(4);
	private final static  EventLoopGroup BOSS = new NioEventLoopGroup(4);
	private final static EventLoopGroup LOCALWORKER = new NioEventLoopGroup(1);

//	private Channel channel;
//	private EventLoopGroup bossGroup;
//	private EventLoopGroup workerGroup;
	private Thread nserver;
//	@Autowired
//	NettyServerInitializer nettyServerInitializer;
	@PostConstruct
	public void init() {
		nserver = new Thread(this);
		nserver.start();
	}
	public int getServerport() {
		return serverport;
	}

	public void setServerport(int serverport) {
		this.serverport = serverport;
	}
	public void destory() {
		System.out.println("destroy server resources");
		BOSS.shutdownGracefully();
		LOCALWORKER.shutdownGracefully();
		REMOTEWORKER.shutdownGracefully();
	}

	@Override
	public void run() {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(BOSS, LOCALWORKER)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
					.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
					.childHandler(NettyServerInitializer.instance);
			ChannelFuture future = bootstrap.bind(NettyServer.serverport ).sync();
			logger.info("开启代理 端口:"+NettyServer.serverport);
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			BOSS.shutdownGracefully();
			LOCALWORKER.shutdownGracefully();
			REMOTEWORKER.shutdownGracefully();
		}
	}
}