package client.network;

import common.messages.ClientMessage;
import common.messages.ClientMessageType;
import common.messages.ServerMessage;
import common.util.Direction;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NetworkHandler {
    private Selector readSelector;
    private Selector writeSelector;
    private SocketChannel channel;
    private MessageReader reader;
    private MessageWriter writer;

    public void MessageArrived(ServerMessage msg) {}

    public void SendMessages() {}

    public synchronized boolean IsConnected() {
        return channel.isConnected();
    }

    public void Connect() {}

    public void Disconnect() {

    }

    public void Register(String username, String password) {
        byte username_length = (byte)username.length();
        byte password_length = (byte)password.length();
        byte[] username_bytes = username.getBytes();
        byte[] password_bytes = password.getBytes();
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Register, ""));
    }

    public void Login(String username, String password) {}

    public void Logout() {}

    public void Enter(int player) {}

    public void Leave() {}

    public void Move(Direction dir) {}

    public void PlaceHoney() {}

    public void PlaceOil() {}

    public void Download(int level_id) {}

    public void WarehouseReady() {}

    public void OwnResults() {}

    public void TopResults() {}

    public void Disconnected() {}
}
