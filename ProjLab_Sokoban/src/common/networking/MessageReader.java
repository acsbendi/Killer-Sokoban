package common.networking;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MessageReader<Message extends IMessage> {
    private INetworkHandler networkHandler;
    private SocketChannel channel;
    private ByteBuffer header;
    private ByteBuffer body;

    public MessageReader(INetworkHandler networkHandler, SocketChannel channel) {
        this.networkHandler = networkHandler;
        this.channel = channel;
        header = ByteBuffer.allocate(3);
        body = ByteBuffer.allocate(1024);
    }

    public void CollectMessages() {
        try {
            boolean stop = false;
            do {
                if (header.hasRemaining()) {
                    int bytesRead = channel.read(header);
                    //System.out.println(bytesRead + " bytes read.");
                    if (!(bytesRead > 0)) {
                        stop = true;
                        if (bytesRead == -1) {
                            networkHandler.Disconnected(channel);
                        }
                    }
                    else if (!header.hasRemaining()) {
                        body.position(0);
                        int b1 = header.get(1) & 0xFF;
                        int b2 = header.get(2) & 0xFF;
                        int length = 256*b1+b2;
                        body.limit(length);
                    }
                }
                else if (body.hasRemaining()) {
                    int bytesRead = channel.read(body);
                    //System.out.println(bytesRead + " bytes read.");
                    if (!(bytesRead > 0)) {
                        stop = true;
                        if (bytesRead == -1) {
                            networkHandler.Disconnected(channel);
                        }
                    }
                }
                else {
                    body.flip();
                    byte[] value = new byte[body.remaining()];
                    body.get(value);
                    header.clear();
                    body.clear();
                    networkHandler.MessageArrived(channel, header.get(0), value);
                }
            }
            while (!stop);
        }
        catch (IOException e) {
            networkHandler.Disconnected(channel);
        }
    }
}
