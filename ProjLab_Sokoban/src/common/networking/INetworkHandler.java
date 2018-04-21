package common.networking;

import java.nio.channels.SocketChannel;

public interface INetworkHandler {
    void Disconnected(SocketChannel channel);

    void MessageArrived(SocketChannel channel, byte b, byte[] value);
}
