package client.network;

import common.messages.ClientMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class MessageWriter {
    NetworkHandler networkHandler;
    SocketChannel channel;
    ByteBuffer buffer;
    ArrayList<ClientMessage> queue;

    public MessageWriter(NetworkHandler networkHandler, SocketChannel channel) {
        this.networkHandler = networkHandler;
        this.channel = channel;
        buffer = ByteBuffer.allocate(1024);
        /* We have to read data from the buffer in order to write it to the channel,
        thus: */ buffer.flip(); // Setting the buffer into reader mode.
        queue = new ArrayList<>();
    }

    public void EnqueueMessage(ClientMessage msg) {
        queue.add(msg);
    }

    public void SendMessages() {
        try {
            boolean stop = false;
            do {
                if (buffer.hasRemaining()) {
                    int bytesSent = channel.write(buffer);
                    System.out.println(bytesSent + " bytes sent.");
                    if (!(bytesSent > 0)) {
                        stop = true;
                    }
                }
                else {
                    if (queue.size() > 0) {
                        buffer.clear(); // Setting the buffer into writer mode.
                        ClientMessage msg = queue.get(0);
                        queue.remove(0);
                        byte type = msg.GetType().ConvertToByte();
                        byte[] length_big_endian = ByteBuffer.allocate(4).putInt(msg.GetLength()).order(ByteOrder.BIG_ENDIAN).array();
                        byte[] length = { length_big_endian[2], length_big_endian[3] };
                        byte[] value = msg.GetValue();
                        buffer.put(type);
                        buffer.put(length);
                        buffer.put(value);
                        buffer.flip(); // Setting back to reader mode.
                    }
                    else {
                        stop = true;
                    }
                }
            }
            while (!stop);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
