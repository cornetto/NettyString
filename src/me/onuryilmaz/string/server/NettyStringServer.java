package me.onuryilmaz.string.server;

/**
 * Interface for String Server
 *
 */
public interface NettyStringServer {

	/**
	 * Initialize the server resources
	 */
	public void initialize();

	/**
	 * Configure server properties
	 * 
	 * @param port
	 * @param packetLength
	 */
	public void configure(int port, int packetLength);

	/**
	 * Start server
	 * 
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public void start() throws InterruptedException, Exception;

	/**
	 * Stop server
	 */
	public void stop();

}
