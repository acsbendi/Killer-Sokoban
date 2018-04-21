package common.networking;

public class ServerMessage {
    private ServerMessageType type;
    private byte[] value;

    public ServerMessage(ServerMessageType type, byte[] value) {
        this.type = type;
        this.value = value;
    }

    public ServerMessageType GetType() {
        return type;
    }

    public int GetLength() {
        return value.length;
    }

    public byte[] GetValue() {
        return value;
    }
}
