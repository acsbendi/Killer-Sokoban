package common.networking;

public interface IMessage {
    byte ConvertTypeToByte();

    public int GetLength();

    public byte[] GetValue();
}
