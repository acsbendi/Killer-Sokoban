package server;

import server.controller.SokobanServer;

public class Application {
    public static void main(String[] args) {
        SokobanServer sokobanServer = SokobanServer.Create();
        sokobanServer.Run();
    }
}
