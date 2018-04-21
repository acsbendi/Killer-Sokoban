package common.networking;

import java.util.TreeMap;

public enum ClientMessageType {
    Register((byte)0),
    Login((byte)1),
    Logout((byte)2),
    Enter((byte)3),
    Leave((byte)4),
    Move((byte)5),
    PlaceHoney((byte)6),
    PlaceOil((byte)7),
    Download((byte)8),
    WarehouseReady((byte)9),
    AskResult((byte)10);

    private byte value;
    private static TreeMap<Byte, ClientMessageType> map;

    static {
        map = new TreeMap<>();
        ClientMessageType[] values = ClientMessageType.values();
        for(ClientMessageType value : values) {
            map.put(value.ConvertToByte(), value);
        }
    }

    public static ClientMessageType Create(byte value) {
        return map.get(value);
    }

    private ClientMessageType(byte value) {
        this.value = value;
    }

    public byte ConvertToByte() {
        return value;
    }
}
