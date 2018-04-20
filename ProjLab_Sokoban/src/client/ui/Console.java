package client.ui;

import client.controller.UserInputExecutor;
import common.util.Direction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console extends UserInterface {
    // Amíg nincs exit parancs: fogadni inputot!!
    // ha pl. connect parancs jött: userInputExecutor.Connect();
    // ha login jött: userInputExecutor.Login(username, password); stb
    public void Run() {
        BufferedReader inputReader;
        inputReader  = new BufferedReader(new InputStreamReader(System.in));
        String inputString;
        String[] inputSplitted = {""};
        while(true){
            try {
                inputString = inputReader.readLine();
                inputSplitted = inputString.split(" ");

                if("connect".equals(inputSplitted[0])){
                    userInputExecutor.Connect();
                } else  if("disconnect".equals(inputSplitted[0])){
                    userInputExecutor.Disconnect();
                } else if("register".equals(inputSplitted[0])){
                    userInputExecutor.Register(inputSplitted[1], inputSplitted[2]);
                } else if("login".equals(inputSplitted[0])){
                    userInputExecutor.Login(inputSplitted[1], inputSplitted[2]);
                } else if("logout".equals(inputSplitted[0])){
                    userInputExecutor.Logout();
                } else if("enter".equals(inputSplitted[0])){
                    userInputExecutor.Enter(Integer.parseInt(inputSplitted[1]));
                } else if("leave".equals(inputSplitted[0])){
                    userInputExecutor.Leave();
                } else if("move".equals(inputSplitted[0])){
                    if("w".equals(inputSplitted[2])){
                        userInputExecutor.Move(Direction.Up);
                    }else if("a".equals(inputSplitted[2])){
                        userInputExecutor.Move(Direction.Left);
                    }else if("s".equals(inputSplitted[2])){
                        userInputExecutor.Move(Direction.Down);
                    }else if("d".equals(inputSplitted[2])){
                        userInputExecutor.Move(Direction.Right);
                    } else{
                        System.out.println("Unknown direction");
                    }
                } else if("placehoney".equals(inputSplitted[0])){
                    userInputExecutor.PlaceHoney();
                } else if("placeoil".equals(inputSplitted[0])){
                    userInputExecutor.PlaceOil();
                } else if("step".equals(inputSplitted[0])){
                    if("w".equals(inputSplitted[2])){
                        userInputExecutor.Step(Direction.Up);
                    }else if("a".equals(inputSplitted[2])){
                        userInputExecutor.Step(Direction.Left);
                    }else if("s".equals(inputSplitted[2])){
                        userInputExecutor.Step(Direction.Down);
                    }else if("d".equals(inputSplitted[2])){
                        userInputExecutor.Step(Direction.Right);
                    } else{
                        System.out.println("Unknown direction");
                    }
                } else if("puthoney".equals(inputSplitted[0])){
                    userInputExecutor.PutHoney();
                } else if("putoil".equals(inputSplitted[0])){
                    userInputExecutor.PutOil();
                } else if("ownresults".equals(inputSplitted[0])){
                    userInputExecutor.OwnResults();
                } else if("topresults".equals(inputSplitted[0])){
                    userInputExecutor.TopResults();
                } else if("exit".equals(inputSplitted[0])){
                    inputReader.close();
                    break;
                }else{
                    System.out.println("Unknown command");
                }

            }catch(IOException e){
                e.printStackTrace(System.out);
            }
        }
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
