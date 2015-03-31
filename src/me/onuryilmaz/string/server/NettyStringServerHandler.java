package me.onuryilmaz.string.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Simple handler for received string messages
 *
 */
public class NettyStringServerHandler extends SimpleChannelInboundHandler<String> {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, String msg) {
		// Handle your string message here
		System.out.println(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.err.println("Exception caught on channel!");
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("Channel is inactive");
		super.channelInactive(ctx);
	}
}