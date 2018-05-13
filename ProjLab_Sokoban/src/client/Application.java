package client;

import client.controller.SokobanClient;
import client.ui.GUI;
import javafx.stage.Stage;

public class Application extends javafx.application.Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

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
        //TODO set up everything
        SokobanClient sokobanClient = SokobanClient.Create(new GUI());
        Thread t = new SokobanClientThread(sokobanClient);
        t.start();
    }
}
