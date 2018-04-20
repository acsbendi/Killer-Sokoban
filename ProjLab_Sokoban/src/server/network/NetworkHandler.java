package server.network;

import common.messages.ClientMessage;
import common.messages.ClientMessageType;
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

    public void MessageArrived(ClientMessage msg) {
        switch(msg.GetType()) {
            case Register:

                break;
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

    public void CollectMessages() {
        try {
            int readyChannels = readSelector.selectNow();
            if (readyChannels == 0)
                return;
            Set<SelectionKey> selectedKeys = readSelector.selectedKeys();
            for(SelectionKey key : selectedKeys) {
                readers.get(key.channel()).CollectMessages();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Disconnected(SocketChannel channel) {
        try {
            System.out.println(channel.socket().getRemoteSocketAddress().toString() + " disconnected.");
            readers.remove(channel);
            writers.remove(channel);
            Client client = clients.get(channel);
            clients.remove(channel);
            channels.remove(client);
            channel.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MessageArrived(SocketChannel channel, ClientMessage msg) {
        switch(msg.GetType()) {
            case Register:
                this.InterpretRegister(channel, msg.GetValue());
                break;
            case Login:
                this.InterpretLogin(channel, msg.GetValue());
                break;
            case Logout:
                this.InterpretLogout(channel, msg.GetValue());
                break;
            case Enter:
                this.InterpretEnter(channel, msg.GetValue());
                break;
            case Leave:
                this.InterpretLeave(channel, msg.GetValue());
                break;
            case Move:
                this.InterpretMove(channel, msg.GetValue());
                break;
            case PlaceHoney:
                this.InterpretPlaceHoney(channel, msg.GetValue());
                break;
            case PlaceOil:
                this.InterpretPlaceOil(channel, msg.GetValue());
                break;
            case Download:
                this.InterpretDownload(channel, msg.GetValue());
                break;
            case WarehouseReady:
                this.InterpretWarehouseReady(channel, msg.GetValue());
                break;
            case AskResult:
                this.InterpretAskResult(channel, msg.GetValue());
                break;
        }
    }

    private void InterpretRegister(SocketChannel channel, byte[] value) {
        sokobanServer.
    }

    private void InterpretLogin(SocketChannel channel, byte[] value) {
        // todo
    }

    private void InterpretLogout(SocketChannel channel, byte[] value) {
        // todo
    }

    private void InterpretEnter(SocketChannel channel, byte[] value) {
        // todo
    }

    private void InterpretLeave(SocketChannel channel, byte[] value) {
        // todo
    }

    private void InterpretMove(SocketChannel channel, byte[] value) {
        // todo
    }

    private void InterpretPlaceHoney(SocketChannel channel, byte[] value) {

    }

    private void InterpretPlaceOil(SocketChannel channel, byte[] value) {

    }

    private void InterpretDownload(SocketChannel channel, byte[] value) {

    }

    private void InterpretWarehouseReady(SocketChannel channel, byte[] value) {

    }

    private void InterpretAskResult(SocketChannel channel, byte[] value) {

    }
}
