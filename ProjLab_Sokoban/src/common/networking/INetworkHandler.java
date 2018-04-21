package common.networking;

import java.nio.channels.SocketChannel;

public interface INetworkHandler {
    void Disconnected(SocketChannel channel);

    void MessageArrived(SocketChannel channel, byte type, byte[] value);

    void WriteRegister(SocketChannel channel);

    void WriteDeregister(SocketChannel channel);
}
