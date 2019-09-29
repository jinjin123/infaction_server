package com.jimmy.infaction.netty.config;

import com.jimmy.infaction.common.Handler.HttpMessageDecoder;
import com.jimmy.infaction.common.httpentity.HttpRequest;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jimmy on 2019/9/29 20:13
 */
public class DefaultHttpRequestDecoder extends HttpMessageDecoder {

	static Logger logger= LoggerFactory.getLogger(DefaultHttpRequestDecoder.class);
	@Override
	public void processRequest(ChannelHandlerContext ctx, HttpRequest request) {
		logger.info("处理请求："+request );
		ctx.fireChannelRead(request);
	}
}
