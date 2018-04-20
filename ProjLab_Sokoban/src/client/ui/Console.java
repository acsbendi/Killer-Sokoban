package client.ui;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
import com.sun.org.apache.bcel.internal.generic.IADD;
import com.sun.org.apache.bcel.internal.generic.SWAP;
import common.util.Direction;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Console extends UserInterface {
    List output = new LinkedList<String>();
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
                    write("Invalid command");
                }
            }catch(IOException e){
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                write(sw.toString());
            }catch(InvalidArgumentException iae){
                write(iae.getMessage());
            }
        }
    }



    private void write(String s){
        output.add(s);
        System.out.println(s);
    }

    @Override
    public void ConnectionResult(boolean res) {
        if(res) {
            write("Connection successful.");
        }
        else {
            write("Connection failed.");
        }
    }

    @Override
    public void Disconnected() {
        write("Disconnected");
    }

    @Override
    public void RegistrationSuccess() {
        write("Registration succesful");
    }

    @Override
    public void RegistrationFailure(String err) {
        write(err);
    }

    @Override
    public void LoginSuccess() {
        write("Login successful.");
    }

    @Override
    public void LoginFailure(String err) {
        write(err);
    }

    @Override
    public void LogoutSuccess() {
        write("Logout successful.");
    }

    @Override
    public void LogoutFailure(String err) {
        write(err);
    }

    @Override
    public void Results(String msg) {
        write(msg);
    }

    @Override
    public void ResultFailure(String err) {
        write(err);
    }

    @Override
    public void EnterSuccess() {
        write("Entered queue");
    }

    @Override
    public void EnterFailure(String err) {
        write(err);
    }

    @Override
    public void LeaveSuccess() {
        write("Queue left");
    }

    @Override
    public void LeaveFailure(String err) {
        write(err);
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
        write("Not available in offline mode.");
    }

    @Override
    public void OnlineFailure() {
        write("Not available in online mode.");
    }
}
