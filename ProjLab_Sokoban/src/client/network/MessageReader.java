package client.network;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class MessageReader {
    private NetworkHandler networkHandler;
    private SocketChannel channel;
    private FileChannel file;
    private ByteBuffer header;
    private ByteBuffer value;

    public MessageReader(NetworkHandler networkHandler, SocketChannel channel) {
        this.networkHandler = networkHandler;
        this.channel = channel;
        // todo
    }

    public void CollectMessages() {}
}
