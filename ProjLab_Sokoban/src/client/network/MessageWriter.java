package client.network;

import common.messages.ClientMessage;

import java.nio.channels.SocketChannel;

public class MessageWriter {
    NetworkHandler networkHandler;
    SocketChannel channel;

    public MessageWriter(NetworkHandler networkHandler, SocketChannel channel) {
        this.networkHandler = networkHandler;
        this.channel = channel;
        // todo
    }

    public void EnqueueMessage(ClientMessage msg) {}

    public void SendMessages() {}
}
