package common.messages;

public class ServerMessage {
    private ServerMessageType type;
    private String value;

    public ServerMessage(ServerMessageType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ServerMessageType GetType() {
        return type;
    }

    public int GetLength() {
        return value.length();
    }

    public String GetValue() {
        return value;
    }
}
