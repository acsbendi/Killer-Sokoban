package client.ui;

import client.ui.Commands.ConsoleCommands.*;
import client.ui.Commands.UserInputExecutorCommands.*;
import client.ui.Commands.InvalidArgumentException;
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
        cmd = new UIEEnter();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEExit();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIELeave();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIELogin();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIELogout();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEMove();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEOwnResults();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPlaceHoney();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPlaceOil();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPutHoney();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPlaceOil();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPutOil();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIERegistration();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEStep();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIETopResults();
        UIECommands.put(cmd.getName(), cmd);


        CCommand cCmd = new CClear();
        CCommands.put(cCmd.getHelp(), cCmd);
        cCmd = new CCompare();
        CCommands.put(cCmd.getHelp(), cCmd);
        cCmd = new CInfo();
        CCommands.put(cCmd.getHelp(), cCmd);
        cCmd = new CList();
        CCommands.put(cCmd.getHelp(), cCmd);
        cCmd = new CRun();
        CCommands.put(cCmd.getHelp(), cCmd);
        cCmd = new CSave();
        CCommands.put(cCmd.getHelp(), cCmd);
        cCmd = new CWait();
        CCommands.put(cCmd.getHelp(), cCmd);
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
        if(UIECommands.containsKey(cmdName)) {
            write(UIECommands.get(cmdName).getHelp());
            return;
        }
        if(UIECommands.containsKey(cmdName)) {
            write(UIECommands.get(cmdName).getHelp());
            return;
        }
    }

    public void compare(String fileName){
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

    public void exit(){
        System.exit(0);
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
