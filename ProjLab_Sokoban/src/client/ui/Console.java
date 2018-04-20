package client.ui;

import client.controller.UserInputExecutor;
import com.sun.org.apache.bcel.internal.generic.IADD;
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
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.Connect();
                } else
                if("disconnect".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.Disconnect();
                } else
                if("reg".equals(inputSplitted[0])){
                    if(inputSplitted.length > 3)
                        throw new InvalidArgumentException(3);
                    else if(inputSplitted.length < 2)
                        throw new InvalidArgumentException(1);
                    else if(inputSplitted.length < 3)
                        throw new InvalidArgumentException(2);
                    userInputExecutor.Register(inputSplitted[1], inputSplitted[2]);
                } else
                if("login".equals(inputSplitted[0])){
                    if(inputSplitted.length > 3)
                        throw new InvalidArgumentException(3);
                    else if(inputSplitted.length < 2)
                        throw new InvalidArgumentException(1);
                    else if(inputSplitted.length < 3)
                        throw new InvalidArgumentException(2);
                    userInputExecutor.Login(inputSplitted[1], inputSplitted[2]);
                } else
                if("logout".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.Logout();
                } else
                if("enter".equals(inputSplitted[0])){
                    if(inputSplitted.length > 2)
                        throw new InvalidArgumentException(2);
                    int arg;
                    try{arg = Integer.parseInt(inputSplitted[1]);}
                    catch (NumberFormatException nfe){throw new InvalidArgumentException(1);}
                    userInputExecutor.Enter(arg);
                } else
                if("leave".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.Leave();
                } else
                if("move".equals(inputSplitted[0])){
                    if(inputSplitted.length > 2){
                        throw new InvalidArgumentException(2);
                    } else if("w".equals(inputSplitted[1])){
                        userInputExecutor.Move(Direction.Up);
                    }else if("a".equals(inputSplitted[1])){
                        userInputExecutor.Move(Direction.Left);
                    }else if("s".equals(inputSplitted[1])){
                        userInputExecutor.Move(Direction.Down);
                    }else if("d".equals(inputSplitted[1])){
                        userInputExecutor.Move(Direction.Right);
                    } else{
                        throw new InvalidArgumentException(1);
                    }
                } else
                if("placehoney".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.PlaceHoney();
                } else
                if("placeoil".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.PlaceOil();
                } else
                if("step".equals(inputSplitted[0])){
                    if(inputSplitted.length > 2){
                        throw new InvalidArgumentException(2);
                    } else if("w".equals(inputSplitted[1])){
                        userInputExecutor.Step(Direction.Up);
                    }else if("a".equals(inputSplitted[1])){
                        userInputExecutor.Step(Direction.Left);
                    }else if("s".equals(inputSplitted[1])){
                        userInputExecutor.Step(Direction.Down);
                    }else if("d".equals(inputSplitted[1])){
                        userInputExecutor.Step(Direction.Right);
                    } else{
                        throw new InvalidArgumentException(1);
                    }
                } else
                if("puthoney".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.PutHoney();
                } else
                if("putoil".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.PutOil();
                } else
                if("ownresults".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.OwnResults();
                } else
                if("topresults".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    userInputExecutor.TopResults();
                } else
                if("exit".equals(inputSplitted[0])){
                    if(inputSplitted.length > 1)
                        throw new InvalidArgumentException(1);
                    inputReader.close();
                    break;
                }
                else{
                    System.out.println("Invalid command");
                }

            }catch(IOException e){
                e.printStackTrace(System.out);
            }catch(InvalidArgumentException iae){
                System.out.println(iae.getMessage());
            }
        }
    }

    private class InvalidArgumentException extends Exception{
        public InvalidArgumentException(int argumentNo){
            super("Invalid argument " + argumentNo);
        }
    }


    @Override
    public void ConnectionResult(boolean res) {
        if(res)
            System.out.println("Connection successful.");
        else
            System.out.println("Connection failed.");
    }

    @Override
    public void Disconnected() {
        System.out.println("Disconnected");
    }

    @Override
    public void RegistrationSuccess() {
        System.out.println("Registration succesful");
    }

    @Override
    public void RegistrationFailure(String err) {
        System.out.println(err);
    }

    @Override
    public void LoginSuccess() {
        System.out.println("Login successful.");
    }

    @Override
    public void LoginFailure(String err) {
        System.out.println(err);
    }

    @Override
    public void LogoutSuccess() {

    }

    @Override
    public void LogoutFailure(String err) {
        System.out.println(err);
    }

    @Override
    public void Results(String msg) {
        System.out.println(msg);
    }

    @Override
    public void ResultFailure(String err) {
        System.out.println(err);
    }

    @Override
    public void EnterSuccess() {
        System.out.println("Entered queue");
    }

    @Override
    public void EnterFailure(String err) {
        System.out.println(err);
    }

    @Override
    public void LeaveSuccess() {
        System.out.println("Queue left");
    }

    @Override
    public void LeaveFailure(String err) {
        System.out.println(err);
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

    @Override
    public void OfflineFailure() {
        System.out.println("Not available in offline mode.");
    }

    @Override
    public void OnlineFailure() {
        System.out.println("Not available in online mode.");
    }
}
