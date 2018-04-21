package client.ui;

import client.ui.Commands.ConsoleCommands.CCommand;
import client.ui.Commands.UserInputExecutorCommands.UIECommand;
import client.ui.Commands.InvalidArgumentException;
import client.ui.Commands.UserInputExecutorCommands.UIEConnect;
import client.ui.Commands.UserInputExecutorCommands.UIEDisconnect;
import common.util.Direction;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Console extends UserInterface {
    private List output = new LinkedList<String>();
    private Map<String, UIECommand> UIECommands = new HashMap<>();
    private Map<String, CCommand> CCommands = new HashMap<>();

    public Console(){
        InitCommands();
    }

    private void InitCommands(){
        UIECommand cmd = new UIEConnect();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEDisconnect();
        UIECommands.put(cmd.getName(), cmd);
        //TODO> minden UIECommandra

        //TODO> minden CCommandra
        CCommand cCmd = ;
    }
    // Amíg nincs exit parancs: fogadni inputot!!
    // ha pl. connect parancs jött: userInputExecutor.Connect();
    // ha login jött: userInputExecutor.Login(username, password); stb

    public void Run(){
        BufferedReader inputReader;
        try {
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            boolean keepRunning = true;
            while (keepRunning) {
                keepRunning = interprete(inputReader.readLine());
            }
        }catch(IOException ioe){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ioe.printStackTrace(pw);
            write(sw.toString());
        }
    }

    private boolean interprete(String command){
        try {
            if (UIECommands.containsKey(command)){
                UIECommands.get(command).Execute(userInputExecutor, command.split(" "));
                return true;
            }
            else if(CCommands.containsKey(command)) {
                CCommands.get(command).Execute(this, command.split(" "));
                return true;
            }
            else if("exit".equals(command.split(" ")[0]))
                return false;
        }catch(InvalidArgumentException iae){
            write(iae.getMessage());
        }
    }

    public void printInfo(String cmdName){
        //TODO
        write(Command.getHelp);
    }

    public void compare(){
        //TODO
    }

    public void save(String fileName){
        //TODO
    }

    public void clear(){
        output.clear();
    }

    public void wait(int ms){
        try {
            Thread.sleep(ms);
        }catch(InterruptedException ie){}
    }

    public void runSketch(String fileName){
        //TODO
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
