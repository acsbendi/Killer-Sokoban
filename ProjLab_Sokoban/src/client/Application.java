package client;

import client.controller.SokobanClient;
import client.ui.GUI;
import javafx.stage.Stage;

public class Application extends javafx.application.Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public static void main(String[] args) {
        //TODO set up everything
        SokobanClient sokobanClient = SokobanClient.Create(new GUI());
    }
}
