package me.onuryilmaz.string.server.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;
import me.onuryilmaz.string.server.NettyAbstractStringServer;
import me.onuryilmaz.string.server.NettyStringServerHandler;

/**
 * Netty String Server based on TCP
 *
 */
public final class NettyStringTCPServer extends NettyAbstractStringServer {

	/**
	 * {@inheritDoc}
	 */
	public void start() throws InterruptedException {

		ServerBootstrap serverStarter = new ServerBootstrap();
		serverStarter.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
			@Override
			public void initChannel(SocketChannel ch) {
				ChannelPipeline pipeline = ch.pipeline();
				// Line delimeter based decoder
				pipeline.addLast("frameDecoder", new DelimiterBasedFrameDecoder(packetLength, Delimiters.lineDelimiter()));
				// String decoder
				pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
				// Business logic handler
				pipeline.addLast(new NettyStringServerHandler());
			}
		});

		serverStarter.bind(port).sync();
	}
}
