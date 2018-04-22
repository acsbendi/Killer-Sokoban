package client.controller;

import common.networking.*;
import common.util.Direction;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class NetworkHandler implements INetworkHandler {
    private static final byte down = 0;
    private static final byte up = 1;
    private static final byte right = 2;
    private static final byte left = 3;

    private static final byte msg_success = 0;
    private static final byte msg_error1 = 1;
    private static final byte msg_error2 = 2;
    private static final byte msg_invalid = (byte)255;

    private ControllerLogic controllerLogic;
    private Selector selector;
    private SocketChannel channel;
    private MessageReader<ServerMessage> reader;
    private MessageWriter<ClientMessage> writer;

    public NetworkHandler(ControllerLogic controllerLogic) {
        this.controllerLogic = controllerLogic;
        try {
            this.selector = Selector.open();
            channel = SocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Connect() {
        if (!channel.isConnected()) {
            boolean success;
            try {
                channel.configureBlocking(true);
                channel.socket().connect(new InetSocketAddress("vm.ik.bme.hu", 7305), 2000);
                success = true;
            } catch (SocketTimeoutException e) {
                success = false;
            } catch (IOException e) {
                success = false;
                e.printStackTrace();
            }
            if (success) {
                try {
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                reader = new MessageReader<>(this, channel);
                writer = new MessageWriter<>(this, channel);
                controllerLogic.ConnectionResult(true);
            }
            else {
                try {
                    channel = SocketChannel.open();
                    channel.configureBlocking(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controllerLogic.ConnectionResult(false);
            }
        }
        else {
            controllerLogic.OnlineFailure();
        }
    }

    public void Disconnect() {
        if (channel.isConnected()) {
            try {
                channel.close();
                channel = SocketChannel.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            controllerLogic.Disconnected();
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void Disconnected(SocketChannel channel) {
        try {
            channel.close();
            this.channel = SocketChannel.open();
            reader = null;
            writer = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        controllerLogic.Disconnected();
    }

    public void Listen() {
        try {
            selector.selectNow();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isValid() && key.isReadable()) {
                    System.out.println("READ event triggered!");
                    reader.CollectMessages();
                }

                if (key.isValid() && key.isWritable()) {
                    System.out.println("WRITE event triggered!");
                    writer.SendMessages();
                }

                iter.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MessageArrived(SocketChannel channel, byte type, byte[] value) {
        switch(ServerMessageType.Create(type)) {
            case RegisterResponse:
                this.InterpretRegisterResponse(value);
                break;
            case LoginResponse:
                this.InterpretLoginResponse(value);
                break;
            case LogoutResponse:
                this.InterpretLogoutResponse(value);
                break;
            case EnterResponse:
                this.InterpretEnterResponse(value);
                break;
            case LeaveResponse:
                this.InterpretLeaveResponse(value);
                break;
            case CheckLevel:
                this.InterpretCheckLevel(value);
                break;
            case LevelData:
                this.InterpretLevelData(value);
                break;
            case GameStarted:
                this.InterpretGameStarted(value);
                break;
            case WorkerMoved:
                this.InterpretWorkerMoved(value);
                break;
            case OilPlaced:
                this.InterpretOilPlaced(value);
                break;
            case HoneyPlaced:
                this.InterpretHoneyPlaced(value);
                break;
            case GameFinished:
                this.InterpretGameFinished(value);
                break;
            case ResultResponse:
                this.InterpretResultResponse(value);
                break;
        }
    }

    public void WriteRegister(SocketChannel channel) {
        SelectionKey key = channel.keyFor(selector);
        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    public void WriteDeregister(SocketChannel channel) {
        SelectionKey key = channel.keyFor(selector);
        key.interestOps(SelectionKey.OP_READ);
    }

    public void Register(String username, String password) {
        if (channel.isConnected()) {
            byte[] username_bytes = username.getBytes(StandardCharsets.UTF_8);
            byte[] password_bytes = password.getBytes(StandardCharsets.UTF_8);
            byte username_length = (byte) username_bytes.length;
            byte password_length = (byte) password_bytes.length;
            byte[] value = new byte[2 + username_length + password_length];
            value[0] = username_length;
            value[1] = password_length;
            System.arraycopy(username_bytes, 0, value, 2, username_length);
            System.arraycopy(password_bytes, 0, value, 2 + username_length, password_length);
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Register, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void Login(String username, String password) {
        if (channel.isConnected()) {
            byte[] username_bytes = username.getBytes(StandardCharsets.UTF_8);
            byte[] password_bytes = password.getBytes(StandardCharsets.UTF_8);
            byte username_length = (byte) username_bytes.length;
            byte password_length = (byte) password_bytes.length;
            byte[] value = new byte[2 + username_length + password_length];
            value[0] = username_length;
            value[1] = password_length;
            System.arraycopy(username_bytes, 0, value, 2, username_length);
            System.arraycopy(password_bytes, 0, value, 2 + username_length, password_length);
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Login, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void Logout() {
        if (channel.isConnected()) {
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Logout, new byte[0]));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void Enter(int players) {
        if (channel.isConnected()) {
            byte[] value = new byte[1];
            value[0] = (byte) players;
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Enter, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void Leave() {
        if (channel.isConnected()) {
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Leave, new byte[0]));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void Move(Direction dir) {
        if (channel.isConnected()) {
            byte[] value = new byte[1];
            switch (dir) {
                case Down:
                    value[0] = down;
                    break;
                case Up:
                    value[0] = up;
                    break;
                case Right:
                    value[0] = right;
                    break;
                case Left:
                    value[0] = left;
                    break;
            }
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Move, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void PlaceHoney() {
        if (channel.isConnected()) {
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.PlaceHoney, new byte[0]));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void PlaceOil() {
        if (channel.isConnected()) {
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.PlaceOil, new byte[0]));
        }
    }

    public void Download(int level_id) {
        if (channel.isConnected()) {
            byte[] value = ByteBuffer.allocate(4).putInt(level_id).order(ByteOrder.BIG_ENDIAN).array();
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.Download, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void WarehouseReady() {
        if (channel.isConnected()) {
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.WarehouseReady, new byte[0]));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void OwnResults() {
        if (channel.isConnected()) {
            byte[] value = new byte[1];
            value[0] = 0;
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.AskResult, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    public void TopResults() {
        if (channel.isConnected()) {
            byte[] value = new byte[1];
            value[0] = 1;
            writer.EnqueueMessage(new ClientMessage(ClientMessageType.AskResult, value));
        }
        else {
            controllerLogic.OfflineFailure();
        }
    }

    private void InterpretRegisterResponse(byte[] value) {
        byte res = value[0];
        if (res == msg_success) {
            controllerLogic.RegistrationSuccess();
        }
        else if (res == msg_error1) {
            controllerLogic.RegistrationFailure("Username already exists.");
        }
        else if (res == msg_error2) {
            byte[] msg = new byte[value.length-1];
            System.arraycopy(value, 1, msg, 0, value.length-1);
            String message = new String(msg, StandardCharsets.UTF_8);
            controllerLogic.RegistrationFailure("Invalid password: " + message);
        }
        else if (res == msg_invalid) {
            controllerLogic.RegistrationFailure("Not available");
        }
    }

    private void InterpretLoginResponse(byte[] value) {
        byte res = value[0];
        if (res == msg_success) {
            controllerLogic.LoginSuccess();
        }
        else if (res == msg_error1) {
            controllerLogic.LoginFailure("Unknown username or invalid password");
        }
        else if (res == msg_invalid) {
            controllerLogic.LoginFailure("Not available");
        }
    }

    private void InterpretLogoutResponse(byte[] value) {
        byte res = value[0];
        if (res == msg_success) {
            controllerLogic.LogoutSuccess();
        }
        else if (res == msg_invalid) {
            controllerLogic.LogoutFailure("Not available");
        }
    }

    private void InterpretEnterResponse(byte[] value) {
        byte res = value[0];
        if (res == msg_success) {
            controllerLogic.EnterSuccess();
        }
        else if (res == msg_invalid) {
            controllerLogic.EnterFailure("Not available");
        }
    }

    private void InterpretLeaveResponse(byte[] value) {
        byte res = value[0];
        if (res == msg_success) {
            controllerLogic.LeaveSuccess();
        }
        else if (res == msg_invalid) {
            controllerLogic.EnterFailure("Not available");
        }
    }

    private void InterpretCheckLevel(byte[] value) {
        // todo
    }

    private void InterpretLevelData(byte[] value) {
        // todo
    }

    private void InterpretGameStarted(byte[] value) {
        controllerLogic.GameStarted(value[0]);
    }

    private void InterpretWorkerMoved(byte[] value) {
        int worker = value[0];
        byte dir = value[1];
        if (dir == down) {
            controllerLogic.WorkerMoved(worker, Direction.Down);
        }
        else if (dir == up) {
            controllerLogic.WorkerMoved(worker, Direction.Up);
        }
        else if (dir == right) {
            controllerLogic.WorkerMoved(worker, Direction.Right);
        }
        else if (dir == left) {
            controllerLogic.WorkerMoved(worker, Direction.Left);
        }
    }

    private void InterpretOilPlaced(byte[] value) {
        int worker = value[0];
        controllerLogic.OilPlaced(worker);
    }

    private void InterpretHoneyPlaced(byte[] value) {
        int worker = value[0];
        controllerLogic.HoneyPlaced(worker);
    }

    private void InterpretGameFinished(byte[] value) {
        controllerLogic.GameFinished();
    }

    private void InterpretResultResponse(byte[] value) {
        byte res = value[0];
        if (res == msg_success) {
            byte[] msg = new byte[value.length-1];
            System.arraycopy(value, 1, msg, 0, value.length-1);
            String message = new String(msg, StandardCharsets.UTF_8);
            controllerLogic.Results(message);
        }
        else if (res == msg_invalid) {
            controllerLogic.ResultFailure("Not available");
        }
    }
}
