package server.controller;

import common.networking.*;
import common.util.Direction;
import server.controller.Client;
import server.controller.ControllerLogic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NetworkHandler implements INetworkHandler {
    private static final byte down = 0;
    private static final byte up = 1;
    private static final byte right = 2;
    private static final byte left = 3;

    private ControllerLogic controllerLogic;
    private Selector selector;

    private HashMap<SocketChannel, MessageReader<ClientMessage>> readers;
    private HashMap<SocketChannel, MessageWriter<ServerMessage>> writers;
    private HashMap<Client, SocketChannel> channels;
    private HashMap<SocketChannel, Client> clients;

    public NetworkHandler(ControllerLogic controllerLogic) {
        this.controllerLogic = controllerLogic;
        this.readers = new HashMap<>();
        this.writers = new HashMap<>();
        this.channels = new HashMap<>();
        this.clients = new HashMap<>();

        try {
            selector = Selector.open();

            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8888));
            serverChannel.configureBlocking(false);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started listening on port 8888.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WriteRegister(SocketChannel channel) {
        try {
            SelectionKey key = channel.keyFor(selector);
            key.cancel();
            channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public void WriteDeregister(SocketChannel channel) {
        try {
            SelectionKey key = channel.keyFor(selector);
            key.cancel();
            channel.register(selector, SelectionKey.OP_READ);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        }
    }

    public void Listen() {
        while(true) {
            try {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        System.out.println("ACCEPT event triggered!");
                        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);

                        readers.put(clientChannel, new MessageReader(this, clientChannel));
                        writers.put(clientChannel, new MessageWriter(this, clientChannel));
                        Client client = new Client();
                        clients.put(clientChannel, client);
                        channels.put(client, clientChannel);
                        System.out.println(clientChannel.socket().getRemoteSocketAddress() + " connected.");
                    }

                    if (key.isReadable()) {
                        System.out.println("READ event triggered!");
                        SocketChannel channel = (SocketChannel) key.channel();
                        readers.get(channel).CollectMessages();
                    }

                    iter.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void RegistrationSuccess(Client client) {
        byte[] value = new byte[1];
        value[0] = 0;
        ServerMessage msg = new ServerMessage(ServerMessageType.RegisterResponse, value);
        writers.get(channels.get(client)).EnqueueMessage(msg);
    }

    public void RegistrationFailure(Client client, String err) {

    }

    public void LoginSuccess(Client client) {
        byte[] value = new byte[1];
        value[0] = 0;
        ServerMessage msg = new ServerMessage(ServerMessageType.LoginResponse, value);
        writers.get(channels.get(client)).EnqueueMessage(msg);
    }

    public void LoginFailure(Client client, String err) {

    }

    public void LogoutSuccess(Client client) {

    }

    public void LogoutFailure(Client client, String err) {

    }

    public void Results(Client client, String msg) {

    }

    public void ResultFailure(Client client, String err) {

    }

    public void EnterSuccess(Client client) {

    }

    public void EnterFailure(Client client, String err) {

    }

    public void LeaveSuccess(Client client) {

    }

    public void LeaveFailure(Client client, String err) {

    }

    public void CheckLevel(Client client, int level_id) {

    }

    public void GameStarted(Client client, int worker) {

    }

    public void WorkerMoved(Client client, int player,Direction dir) {

    }

    public void OilPlaced(Client client, int player) {

    }

    public void HoneyPlaced(Client client, int player) {

    }

    public void GameFinished(Client client) {

    }


    /*public void AcceptClient()
    {
        try {
            int readyChannels = acceptSelector.selectNow();

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
    }*/

    /*public void CollectMessages() {
        try {
            System.out.println("Checking read selector...");
            int readyChannels = readSelector.selectNow();
            if (readyChannels == 0)
                return;
            System.out.println("Something to read...");
            Set<SelectionKey> selectedKeys = readSelector.selectedKeys();
            for(SelectionKey key : selectedKeys) {
                readers.get(key.channel()).CollectMessages();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*public void SendMessages() {
        try {
            int readyChannels = writeSelector.selectNow();
            if (readyChannels == 0)
                return;
            Set<SelectionKey> selectedKeys = writeSelector.selectedKeys();
            for(SelectionKey key : selectedKeys) {
                writers.get(key.channel()).SendMessages();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

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

    @Override
    public void MessageArrived(SocketChannel channel, byte type, byte[] value) {
        switch(ClientMessageType.Create(type)) {
            case Register:
                this.InterpretRegister(channel, value);
                break;
            case Login:
                this.InterpretLogin(channel, value);
                break;
            case Logout:
                this.InterpretLogout(channel, value);
                break;
            case Enter:
                this.InterpretEnter(channel, value);
                break;
            case Leave:
                this.InterpretLeave(channel, value);
                break;
            case Move:
                this.InterpretMove(channel, value);
                break;
            case PlaceHoney:
                this.InterpretPlaceHoney(channel, value);
                break;
            case PlaceOil:
                this.InterpretPlaceOil(channel, value);
                break;
            case Download:
                this.InterpretDownload(channel, value);
                break;
            case WarehouseReady:
                this.InterpretWarehouseReady(channel, value);
                break;
            case AskResult:
                this.InterpretAskResult(channel, value);
                break;
        }
    }

    private void InterpretRegister(SocketChannel channel, byte[] value) {
        byte username_length = value[0];
        byte password_length = value[1];
        byte[] username_bytes = new byte[username_length];
        byte[] password_bytes = new byte[password_length];
        System.arraycopy(value, 2, username_bytes, 0, username_length);
        System.arraycopy(value, 2+username_length, password_bytes, 0, password_length);
        String username = new String(username_bytes, StandardCharsets.UTF_8);
        String password = new String(password_bytes, StandardCharsets.UTF_8);
        System.out.println(channel.socket().getRemoteSocketAddress().toString() + " send Register " + username + "; " + password + ")");
        controllerLogic.Register(clients.get(channel), username, password);
    }

    private void InterpretLogin(SocketChannel channel, byte[] value) {
        byte username_length = value[0];
        byte password_length = value[1];
        byte[] username_bytes = new byte[username_length];
        byte[] password_bytes = new byte[password_length];
        System.arraycopy(value, 2, username_bytes, 0, username_length);
        System.arraycopy(value, 2+username_length, password_bytes, 0, password_length);
        String username = new String(username_bytes, StandardCharsets.UTF_8);
        String password = new String(password_bytes, StandardCharsets.UTF_8);
        System.out.println(channel.socket().getRemoteSocketAddress().toString() + " sent Login(" + username + "; " + password + ")");
        controllerLogic.Login(clients.get(channel), username, password);
    }

    private void InterpretLogout(SocketChannel channel, byte[] value) {
        System.out.println(channel.socket().getRemoteSocketAddress().toString() + " sent Logout");
        controllerLogic.Logout(clients.get(channel));
    }

    private void InterpretEnter(SocketChannel channel, byte[] value) {
        int players = value[0];
        controllerLogic.Enter(clients.get(channel), players);
    }

    private void InterpretLeave(SocketChannel channel, byte[] value) {
        controllerLogic.Leave(clients.get(channel));
    }

    private void InterpretMove(SocketChannel channel, byte[] value) {
        int dir = value[0];
        if (dir == down) {
            controllerLogic.Move(clients.get(channel), Direction.Down);
        }
        else if (dir == up) {
            controllerLogic.Move(clients.get(channel), Direction.Up);
        }
        else if (dir == right) {
            controllerLogic.Move(clients.get(channel), Direction.Right);
        }
        else if (dir == left) {
            controllerLogic.Move(clients.get(channel), Direction.Left);
        }
    }

    private void InterpretPlaceHoney(SocketChannel channel, byte[] value) {
        controllerLogic.PlaceHoney(clients.get(channel));
    }

    private void InterpretPlaceOil(SocketChannel channel, byte[] value) {
        controllerLogic.PlaceOil(clients.get(channel));
    }

    private void InterpretDownload(SocketChannel channel, byte[] value) {
        // todo
    }

    private void InterpretWarehouseReady(SocketChannel channel, byte[] value) {
        controllerLogic.WarehouseReady(clients.get(channel));
    }

    private void InterpretAskResult(SocketChannel channel, byte[] value) {
        int mode = value[0];
        if (mode == 0) {
            controllerLogic.OwnResults(clients.get(channel));
        }
        else if (mode == 1) {
            controllerLogic.TopResults(clients.get(channel));
        }
    }
}