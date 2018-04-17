package common.messages;

public class ClientMessage {
    private ClientMessageType type;
    private String value;

    public ClientMessage(ClientMessageType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ClientMessageType GetType() {
        return type;
    }

    public int GetLength() {
        return value.length();
    }

    public String GetValue() {
        return value;
    }
}
