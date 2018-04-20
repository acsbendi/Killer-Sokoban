package server.network;

import common.messages.ServerMessage;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class MessageWriter {
    NetworkHandler networkHandler;
    SocketChannel channel;
    ByteBuffer buffer;
    ArrayList<ServerMessage> queue;

    public MessageWriter(NetworkHandler networkHandler, SocketChannel channel) {
        this.networkHandler = networkHandler;
        this.channel = channel;
        buffer = ByteBuffer.allocate(1024);
        /* We have to read data from the buffer in order to write it to the channel,
        thus: */ buffer.flip(); // Setting the buffer into reader mode.
        queue = new ArrayList<>();
    }

    public void EnqueueMessage(ServerMessage msg) {
        queue.add(msg);
    }

    public void SendMessages() {
        try {
            boolean stop = false;
            do {
                if (buffer.hasRemaining()) {
                    int bytesSent = channel.write(buffer);
                    if (!(bytesSent > 0)) {
                        stop = true;
                    }
                }
                else {
                    if (queue.size() > 0) {
                        buffer.clear(); // Setting the buffer into writer mode.
                        ServerMessage msg = queue.get(0);
                        queue.remove(0);
                        byte type = msg.GetType().ConvertToByte();
                        byte[] length = ByteBuffer.allocate(4).putInt(msg.GetLength()).order(ByteOrder.BIG_ENDIAN).array();
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