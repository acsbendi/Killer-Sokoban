package client.network;

import client.controller.ControllerLogic;
import common.messages.ClientMessage;
import common.messages.ClientMessageType;
import common.messages.ServerMessage;
import common.util.Direction;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NetworkHandler {
    private ControllerLogic controllerLogic;
    private Selector readSelector;
    private Selector writeSelector;
    private SocketChannel channel;
    private MessageReader reader;
    private MessageWriter writer;

    public void MessageArrived(ServerMessage msg) {
        switch(msg.GetType()) {
            case RegisterResponse:
                this.InterpretRegisterResponse(msg.GetValue());
                break;
            case LoginResponse:
                this.InterpretLoginResponse(msg.GetValue());
                break;
            case LogoutResponse:
                this.InterpretLogoutResponse(msg.GetValue());
                break;
            case EnterResponse:
                this.InterpretEnterResponse(msg.GetValue());
                break;
            case CheckLevel:
                this.InterpretCheckLevel(msg.GetValue());
                break;
            case LevelData:
                this.InterpretLevelData(msg.GetValue());
                break;
            case GameStarted:
                this.InterpretGameStarted(msg.GetValue());
                break;
            case WorkerMoved:
                this.InterpretWorkerMoved(msg.GetValue());
                break;
            case OilPlaced:
                this.InterpretOilPlaced(msg.GetValue());
                break;
            case HoneyPlaced:
                this.InterpretHoneyPlaced(msg.GetValue());
                break;
            case GameFinished:
                this.InterpretGameStarted(msg.GetValue());
                break;
            case ResultResponse:
                this.InterpretResultResponse(msg.GetValue());
                break;
        }
    }

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
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Leave, new byte[0]));
    }

    public void Move(Direction dir) {
        byte[] value = new byte[1];
        switch(dir) {
            case Down: value[0] = 0; break;
            case Up: value[0] = 1; break;
            case Right: value[0] = 2; break;
            case Left: value[0] = 3; break;
        }
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Move, value));
    }

    public void PlaceHoney() {
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.PlaceHoney, new byte[0]));
    }

    public void PlaceOil() {
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.PlaceOil, new byte[0]));
    }

    public void Download(int level_id) {
        byte[] value = ByteBuffer.allocate(4).putInt(level_id).order(ByteOrder.BIG_ENDIAN).array();
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.Download, value));
    }

    public void WarehouseReady() {
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.WarehouseReady, new byte[0]));
    }

    public void OwnResults() {
        byte[] value = new byte[1];
        value[0] = 0;
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.AskResult, value));
    }

    public void TopResults() {
        byte[] value = new byte[1];
        value[0] = 1;
        writer.EnqueueMessage(new ClientMessage(ClientMessageType.AskResult, value));
    }

    public void Disconnected() {

    }

    private void InterpretRegisterResponse(byte[] value) {
        byte res = value[0];
        if (res == (byte)0) {
            controllerLogic.RegistrationSuccess();
        }
        else if (res == (byte)1) {
            controllerLogic.RegistrationFailure("Username already exists.");
        }
        else if (res == (byte)2) {
            byte[] msg = new byte[value.length-1];
            System.arraycopy(value, 1, msg, 0, value.length-1);
            String message = new String(msg);
            controllerLogic.RegistrationFailure(message);
        }
        else if (res == (byte)255) {
            controllerLogic.RegistrationFailure("Not available");
        }
    }

    private void InterpretLoginResponse(byte[] value) { }

    private void InterpretLogoutResponse(byte[] value) { }

    private void InterpretEnterResponse(byte[] value) { }

    private void InterpretLeaveResponse(byte[] value) { }

    private void InterpretCheckLevel(byte[] value) { }

    private void InterpretLevelData(byte[] value) { }

    private void InterpretGameStarted(byte[] value) { }

    private void InterpretWorkerMoved(byte[] value) { }

    private void InterpretOilPlaced(byte[] value) { }

    private void InterpretHoneyPlaced(byte[] value) { }

    private void InterpretGameFinished(byte[] value) { }

    private void InterpretResultResponse(byte[] value) { }
}
