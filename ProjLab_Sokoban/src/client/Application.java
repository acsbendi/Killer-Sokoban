package client;

import client.controller.SokobanClient;
import client.ui.GUI;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Application extends javafx.application.Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        SokobanClient sokobanClient = SokobanClient.Create(new GUI(primaryStage));
        primaryStage.show(); // -.-"
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        sokobanClient.Iterate();
                    }
                });
            }
        }, 0, 100);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
