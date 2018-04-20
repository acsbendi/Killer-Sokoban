package client.ui;

import client.controller.UserInputExecutor;

import java.util.Scanner;

public class Console extends UserInterface {
    public void Run() {
        Scanner scanner = new Scanner(System.in);
        // Amíg nincs exit parancs: fogadni inputot!!
        // ha pl. connect parancs jött: userInputExecutor.Connect();
        // ha login jött: userInputExecutor.Login(username, password); stb.
    }

    @Override
    public void ConnectionResult(boolean res) {

    }

    @Override
    public void Disconnected() {

    }

    @Override
    public void RegistrationSuccess() {

    }

    @Override
    public void RegistrationFailure(String error) {

    }

    @Override
    public void LoginSuccess() {

    }

    @Override
    public void LoginFailure(String err) {

    }

    @Override
    public void Results(String msg) {

    }

    @Override
    public void ResultFailure(String err) {

    }

    @Override
    public void EnterSuccess() {

    }

    @Override
    public void EnterFailure(String err) {

    }

    @Override
    public void LeaveSuccess() {

    }

    @Override
    public void LeaveFailure(String err) {

    }

    @Override
    public void GameStarted() {

    }

    @Override
    public void UpdateScreen() {

    }

    @Override
    public void GameFinished() {

    }
}
