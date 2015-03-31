/**
 * 
 */
package me.onuryilmaz.string;

import me.onuryilmaz.string.server.NettyStringServer;
import me.onuryilmaz.string.server.tcp.NettyStringTCPServer;
import me.onuryilmaz.string.server.udp.NettyStringUDPServer;

/**
 *
 */
public final class ServerStarter {

	private static final String UDP = "UDP";
	private static final String TCP = "TCP";

	private static NettyStringServer server;

	/**
	 * @param args
	 * @throws Exception
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException, Exception {

		if (args.length < 2) {
			System.err.println("Try calling with: <connection_type: tcp or udp> <port> [maximum_packet_length]");
		}

		// Read and assign connection type
		String connectionType = args[0];
		if (UDP.equalsIgnoreCase(connectionType)) {
			server = new NettyStringUDPServer();
		} else if (TCP.equalsIgnoreCase(connectionType)) {
			server = new NettyStringTCPServer();
		} else {
			System.err.println("Connection type is not matching to TCP or UDP!");
			System.err.println("Try calling with: <connection_type: tcp or udp> <port> [maximum_packet_length]");
			return;
		}

		// Read and assign port
		Integer port = Integer.parseInt(args[1]);
		if (port < 1 || port > 65535) {
			System.err.println("Provided port is should be between 1..65535!");
			return;
		}

		// Read and assign maximum packet length
		Integer maxPacketLength;
		if (args.length > 2) {
			maxPacketLength = Integer.parseInt(args[2]);
		} else {
			maxPacketLength = 65535;
			System.out.println("Packet length is set to " + maxPacketLength);
		}

		System.out.println("Initializing the server...");
		server.initialize();

		System.out.println("Configuring the server...");
		server.configure(port, maxPacketLength);

		System.out.println("Starting the server...");
		server.start();

		while (true) {
		}
	}
}
