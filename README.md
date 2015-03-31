## Netty String Server/Client Example
Simple string server and client implementation for TCP/UDP based on Netty

### Run
```
java ServerStarter <connection_type> <port> [maximum_packet_length]
```
Example:
```
java ServerStarter tcp 9091
java ServerStarter udp 9091 1024
```
