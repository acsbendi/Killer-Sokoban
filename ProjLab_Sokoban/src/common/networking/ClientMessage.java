package common.networking;

public class ClientMessage {
    private ClientMessageType type;
    private byte[] value;

    public ClientMessage(ClientMessageType type, byte[] value) {
        this.type = type;
        this.value = value;
    }

    public ClientMessageType GetType() {
        return type;
    }

    public int GetLength() {
        return value.length;
    }

    public byte[] GetValue() {
        return value;
    }
}
