package client.ui;

import client.ui.Commands.ConsoleCommands.*;
import client.ui.Commands.UserInputExecutorCommands.*;
import client.ui.Commands.InvalidArgumentException;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Console extends UserInterface {
    private List<String> output = new LinkedList<>();
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
        cCmd = new CExit();
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
            String inputLine = "";
            while ((inputLine = inputReader.readLine()) != null) {
                interprete(inputLine);
            }
        }catch(IOException ioe){
            logStackTrace(ioe);
        }
    }

    private void interprete(String command){
        if(command.isEmpty())
            return;
        String[] splitted = command.split(" ");
        try {
            if (UIECommands.containsKey(splitted[0])){
                UIECommands.get(splitted[0]).Execute(userInputExecutor, splitted);
            }
            else if(CCommands.containsKey(splitted[0])) {
                CCommands.get(splitted[0]).Execute(this, splitted);
            }else{
                System.out.println("Invalid command");
            }
        }catch(InvalidArgumentException iae){
            log(iae.getMessage());
        }
    }

    public void printInfo(String cmdName){
        if(UIECommands.containsKey(cmdName)) {
            log(UIECommands.get(cmdName).getHelp());
            return;
        }
        if(UIECommands.containsKey(cmdName)) {
            log(UIECommands.get(cmdName).getHelp());
            return;
        }
    }

    public void compare(String fileName)   {
        File sketch = new File(System.getProperty("user.dir"), fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(sketch)));
        }
        catch(FileNotFoundException fnfe){
            log("File not found");
            return;
        }
        String inputLine;
        try{
            //for each line int the output buffer
            for (int i = 0; i < output.size(); i++) {
                int j = i;
                //compare the file with the next lines
                while(true){
                    //if we reached EOF, the whole file matches
                    if((inputLine = br.readLine()) == null){
                        log("Correct output");
                        return;
                    }
                    if(!inputLine.equals(output.get(j)))
                        break;
                    j++;
                }
            }
        }catch (IOException ioe){
            logStackTrace(ioe);
        }catch(IndexOutOfBoundsException ioobe){
            //if we get IndexOutOfBoundsException the file'd be too long to match
        }
        //if we reached the end of the output buffer without match or encountered IndexOutOfBoundsException that means that the output differs
        log("Different output.");
    }

    public void save(String fileName)   {
        //TODO
        File sketch = new File(System.getProperty("user.dir"), fileName);
        if(sketch.exists()) {
               log("File already exists");
               return;
        }
        try {
            if(sketch.createNewFile()){
                PrintWriter pw = new PrintWriter(new FileOutputStream(sketch));
                for (String line : output) {
                    pw.println(line);
                }
            }
        } catch (IOException ioe) {
            logStackTrace(ioe);
        }
    }

    public void clear(){
        output.clear();
    }

    public void wait(int ms){
        try {
            Thread.sleep(ms);
        }catch(InterruptedException ie){}
    }

    public void runSketch(String fileName) throws FileNotFoundException {
        File sketch = new File(System.getProperty("user.dir"), fileName);
        if(sketch.exists()){
            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(sketch)));
            String inputLine = "";
            try {
                while ((inputLine = br.readLine()) != null) {
                    interprete(inputLine);
                }
            } catch (IOException ioe){
                logStackTrace(ioe);
            }
        }else{
            throw new FileNotFoundException(fileName);
        }
    }

    public void exit(){
        System.exit(0);
    }

    private void logStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log(sw.toString());
    }

    private void log(String s){
        output.add(s);
        System.out.println(s);
    }

    @Override
    public void ConnectionResult(boolean res) {
        if(res) {
            log("Connection successful.");
        }
        else {
            log("Connection failed.");
        }
    }

    @Override
    public void Disconnected() {
        log("Disconnected");
    }

    @Override
    public void RegistrationSuccess() {
        log("Registration succesful");
    }

    @Override
    public void RegistrationFailure(String err) {
        log(err);
    }

    @Override
    public void LoginSuccess() {
        log("Login successful.");
    }

    @Override
    public void LoginFailure(String err) {
        log(err);
    }

    @Override
    public void LogoutSuccess() {
        log("Logout successful.");
    }

    @Override
    public void LogoutFailure(String err) {
        log(err);
    }

    @Override
    public void Results(String msg) {
        log(msg);
    }

    @Override
    public void ResultFailure(String err) {
        log(err);
    }

    @Override
    public void EnterSuccess() {
        log("Entered queue");
    }

    @Override
    public void EnterFailure(String err) {
        log(err);
    }

    @Override
    public void LeaveSuccess() {
        log("Queue left");
    }

    @Override
    public void LeaveFailure(String err) {
        log(err);
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
        log("Not available in offline mode.");
    }

    @Override
    public void OnlineFailure() {
        log("Not available in online mode.");
    }
}
