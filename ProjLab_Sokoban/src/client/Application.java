package client;

import client.controller.SokobanClient;
import client.ui.Console;
import common.util.Position;

public class Application {
    private static class SokobanClientThread extends Thread {
        SokobanClient sokobanClient;

        public SokobanClientThread(SokobanClient sokobanClient) {
            this.sokobanClient = sokobanClient;
        }

        public void run() {
            while (true) {
                try {
                    sokobanClient.Iterate();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        /*Console console = new Console();
        SokobanClient sokobanClient = SokobanClient.Create(console);
        Thread t = new SokobanClientThread(sokobanClient);
        t.start();
        console.Run();*/
        System.out.println(new Position(1,1).hashCode());
        System.out.println(new Position(1,1).hashCode());
    }
}
