package common.messages;

import java.util.TreeMap;

public enum ServerMessageType {
    RegisterResponse((byte)0),
    LoginResponse((byte)1),
    LogoutResponse((byte)2),
    EnterResponse((byte)3),
    LeaveResponse((byte)4),
    CheckLevel((byte)5),
    LevelData((byte)6),
    GameStarted((byte)7),
    WorkerMoved((byte)8),
    OilPlaced((byte)9),
    HoneyPlaced((byte)10),
    GameFinished((byte)11),
    ResultResponse((byte)12);

    private byte value;
    private static TreeMap<Byte, ServerMessageType> map;

    static {
        map = new TreeMap<>();
        ServerMessageType[] values = ServerMessageType.values();
        for(ServerMessageType value : values) {
            map.put(value.ConvertToByte(), value);
        }
    }

    public static ServerMessageType Create(byte value) {
        return map.get(value);
    }

    private ServerMessageType(byte value) {
        this.value = value;
    }

    public byte ConvertToByte() {
        return value;
    }
}
