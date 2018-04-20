package server.network;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class NetworkHandler {
    private SokobanServer sokobanServer;
    private Selector acceptSelector;
    private Selector readSelector;
    private Selector writeSelector;

    private HashMap<SocketChannel, MessageReader> readers;
    private HashMap<SocketChannel, MessageWriter> writers;
    private HashMap<Client, SocketChannel> channels;
    private HashMap<SocketChannel, Client> clients;

    public static NetworkHandler Create()

    private NetworkHandler() {

    }
}
