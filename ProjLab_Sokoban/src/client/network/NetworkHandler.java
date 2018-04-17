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
        byte[] username_bytes = username.getBytes();
        byte[] password_bytes = password.getBytes();
        byte username_length = (byte)username_bytes.length;
        byte password_length = (byte)password_bytes.length;
        byte[] value = new byte[2+username_length+password_length];
        value[0] = username_length;
        value[1] = password_length;
        System.arraycopy(username_bytes, 0, value, 2, username_length);
        System.arraycopy(password_bytes, 0, value, 2+username_length, password_length);
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Register, value));
    }

    public void Login(String username, String password) {
        byte[] username_bytes = username.getBytes();
        byte[] password_bytes = password.getBytes();
        byte username_length = (byte)username_bytes.length;
        byte password_length = (byte)password_bytes.length;
        byte[] value = new byte[2+username_length+password_length];
        value[0] = username_length;
        value[1] = password_length;
        System.arraycopy(username_bytes, 0, value, 2, username_length);
        System.arraycopy(password_bytes, 0, value, 2+username_length, password_length);
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Login, value));
    }

    public void Logout() {
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Logout, new byte[0]));
    }

    public void Enter(int players) {
        byte[] value = new byte[1];
        value[0] = (byte)players;
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Enter, value));
    }

    public void Leave() {

    }

    public void Move(Direction dir) {}

    public void PlaceHoney() {}

    public void PlaceOil() {}

    public void Download(int level_id) {}

    public void WarehouseReady() {}

    public void OwnResults() {}

    public void TopResults() {}

    public void Disconnected() {}
}
