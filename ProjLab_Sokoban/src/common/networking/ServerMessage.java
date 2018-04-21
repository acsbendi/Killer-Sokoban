package common.networking;

public class ServerMessage implements IMessage {
    private ServerMessageType type;
    private byte[] value;

    public ServerMessage(ServerMessageType type, byte[] value) {
        this.type = type;
        this.value = value;
    }

    public ServerMessageType GetType() {
        return type;
    }

    public byte ConvertTypeToByte() {
        return this.GetType().ConvertToByte();
    }

    public int GetLength() {
        return value.length;
    }

    public byte[] GetValue() {
        return value;
    }
}
