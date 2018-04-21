package common.networking;

public class ClientMessage implements IMessage {
    private ClientMessageType type;
    private byte[] value;

    public ClientMessage(ClientMessageType type, byte[] value) {
        this.type = type;
        this.value = value;
    }

    public ClientMessageType GetType() {
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
