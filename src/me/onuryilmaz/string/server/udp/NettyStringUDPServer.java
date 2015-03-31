package me.onuryilmaz.string.server.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

import me.onuryilmaz.string.server.NettyAbstractStringServer;
import me.onuryilmaz.string.server.NettyStringServerHandler;

/**
 * Netty String Server based on UDP
 *
 */
public final class NettyStringUDPServer extends NettyAbstractStringServer {

	/**
	 * {@inheritDoc}
	 */
	public void start() throws InterruptedException {

		Bootstrap serverStarter = new Bootstrap();
		serverStarter.group(bossGroup).channel(NioDatagramChannel.class).handler(new ChannelInitializer<DatagramChannel>() {
			@Override
			public void initChannel(DatagramChannel channel) {
				ChannelPipeline pipeline = channel.pipeline();

				// Get datagram from downstream layer
				pipeline.addLast(new MessageToMessageDecoder<DatagramPacket>() {

					/**
					 * Send the contained string to upstream layer
					 */
					@Override
					protected void decode(ChannelHandlerContext ctx, DatagramPacket msg, List<Object> out) throws Exception {
						out.add(msg.content().toString(CharsetUtil.UTF_8));
					}

				});
				// Add business logic related handler here
				pipeline.addLast(new NettyStringServerHandler());
			}
		});

		serverStarter.bind(port).sync();
		System.out.println("Server is started at port " + port);
	}
}
