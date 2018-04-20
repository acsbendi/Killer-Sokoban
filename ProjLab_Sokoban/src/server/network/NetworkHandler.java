package server.network;

import common.messages.ClientMessage;
import server.controller.Client;
import server.controller.SokobanServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Set;

public class NetworkHandler {
    private SokobanServer sokobanServer;
    private Selector acceptSelector;
    private Selector readSelector;
    private Selector writeSelector;

    private HashMap<SocketChannel, MessageReader> readers;
    private HashMap<SocketChannel, MessageWriter> writers;
    private HashMap<Client, SocketChannel> channels;
    private HashMap<SocketChannel, Client> clients;

    public NetworkHandler(SokobanServer sokobanServer) {
        this.sokobanServer = sokobanServer;
        this.readers = new HashMap<>();
        this.writers = new HashMap<>();
        this.channels = new HashMap<>();
        this.clients = new HashMap<>();

        try {
            acceptSelector = Selector.open();
            readSelector = Selector.open();
            writeSelector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(8888));
            serverChannel.configureBlocking(false);

            serverChannel.register(acceptSelector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started listening on port 8888.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AcceptClient()
    {
        try {
            int readyChannels = acceptSelector.select();

            if (readyChannels == 0)
                return;

            Set<SelectionKey> selectedKeys = acceptSelector.selectedKeys();

            SelectionKey key = selectedKeys.iterator().next();
            if (key.isAcceptable())
            {
                ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                SocketChannel clientChannel = serverChannel.accept();
                clientChannel.configureBlocking(false);
                clientChannel.register(readSelector,SelectionKey.OP_READ);
                readers.put(clientChannel, new MessageReader(this, clientChannel));
                writers.put(clientChannel, new MessageWriter(this, clientChannel));
                Client client = new Client();
                clients.put(clientChannel, client);
                channels.put(client, clientChannel);
                //sokobanServer.AddClient(client);
                System.out.println(clientChannel.socket().getRemoteSocketAddress() + " connected.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Disconnected(SocketChannel channel) {
        // todo
    }

    public void MessageArrived(SocketChannel channel, ClientMessage msg) {
        // todo
    }


}
