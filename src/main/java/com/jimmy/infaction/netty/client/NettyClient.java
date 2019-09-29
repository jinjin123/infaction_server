package com.jimmy.infaction.netty.client;
import com.jimmy.infaction.netty.config.NettyClientInitializer;
import com.jimmy.infaction.netty.server.NettyServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * @author jimmy on 2019/9/29 21:30
 */
public class NettyClient implements Runnable {
	private static Logger logger= LoggerFactory.getLogger(NettyClient.class);
	private static  int clientPort ;
	public static  int serverPort = NettyServer.serverport;
	public static  String serverHost ="127.0.0.1";

	public final static EventLoopGroup REMOTEWORKER =new NioEventLoopGroup(4);
	private final static  EventLoopGroup BOSS = new NioEventLoopGroup(4);
	private final static EventLoopGroup LOCALWORKER = new NioEventLoopGroup(1);
	private Thread nclient;

	@PostConstruct
	public void init() {
		nclient = new Thread(this);
		nclient.start();
	}
	public int getClientport() {
		return clientPort;
	}

	public void setClientport(int serverport) {
		this.clientPort = serverport;
	}
	public void destory() {
		System.out.println("destroy client resources");
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
					.childOption(ChannelOption.AUTO_READ,false)//等到连接到代理服务器再设为true
					.childHandler(NettyClientInitializer.instance);
			ChannelFuture future = bootstrap.bind(NettyClient.clientPort).sync();
			logger.info("开启代理 端口:"+NettyClient.clientPort);
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
