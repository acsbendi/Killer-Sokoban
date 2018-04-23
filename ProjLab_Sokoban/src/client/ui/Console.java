package client.ui;

import client.ui.Commands.ConsoleCommands.*;
import client.ui.Commands.UserInputExecutorCommands.*;
import client.ui.Commands.InvalidArgumentException;
import org.omg.CORBA.DynAnyPackage.Invalid;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Console extends UserInterface {
    private List<String> output = new LinkedList<>();
    private Map<String, UIECommand> UIECommands = new HashMap<>();
    private Map<String, CCommand> CCommands = new HashMap<>();
    /**Nullaparaméteres konstruktor, a parancsok inicializálásához*/
    public Console(){
        InitCommands();
    }
    /**Parancsok inicializálása, parancs tárolók feltöltése*/
    private void InitCommands(){
        UIECommand cmd = new UIEConnect();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEDisconnect();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEEnter();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIELeave();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIELogin();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIELogout();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEMove();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEList();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPlaceOil();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPutHoney();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPlaceHoney();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEPutOil();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIERegistration();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEStep();
        UIECommands.put(cmd.getName(), cmd);
        cmd = new UIEMaketest();
        UIECommands.put(cmd.getName(), cmd);


        CCommand cCmd = new CClear();
        CCommands.put(cCmd.getName(), cCmd);
        cCmd = new CCompare();
        CCommands.put(cCmd.getName(), cCmd);
        cCmd = new CInfo();
        CCommands.put(cCmd.getName(), cCmd);
        cCmd = new CExit();
        CCommands.put(cCmd.getName(), cCmd);
        cCmd = new CRun();
        CCommands.put(cCmd.getName(), cCmd);
        cCmd = new CSave();
        CCommands.put(cCmd.getName(), cCmd);
        cCmd = new CWait();
        CCommands.put(cCmd.getName(), cCmd);
    }
    // Amíg nincs exit parancs: fogadni inputot!!
    // ha pl. connect parancs jött: userInputExecutor.Connect();
    // ha login jött: userInputExecutor.Login(username, password); stb
    /**A konzolról parancsok fogadása*/
    public void Run(){
        /**A stdin-ről olvas, az inputot tovább adja a feldolgozónak*/
        BufferedReader inputReader;
        try {
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            String inputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                Interpret(inputLine);
            }
        }catch(IOException ioe){
            logStackTrace(ioe);
        }
    }
    /**A parancs értelmezését végzi, meghívja a mgefelelő parancson a végrehajtó függvényt*/
    private void Interpret(String command){
        if(command.isEmpty())
            return;
        /**A bemenet feldarabolása szavakra*/
        String[] splitted = command.split(" ");
        /**Megvizsgáljuk, hogy benne van-e valamelyik parancstárolóban, ha igen végrehajtjuk, ha nem visszajelezzük, hogy ismeretlen a parancs*/
        try {
            if (UIECommands.containsKey(splitted[0])){
                UIECommands.get(splitted[0]).Execute(userInputExecutor, splitted);
            }
            else if(CCommands.containsKey(splitted[0])) {
                CCommands.get(splitted[0]).Execute(this, splitted);
            }else{
                log("Invalid command");
            }
        }catch(InvalidArgumentException iae){
            log(iae.getMessage());
        }
    }
    /**A paraméterként kapott parancsról kiírja az információt, amennyiben érvényes parancs.*/
    public void printInfo(String cmdName) throws InvalidArgumentException {
        if("all".equals(cmdName)){
            for (String s:CCommands.keySet())
                log(s);
            for (String s:UIECommands.keySet())
                log(s);
        }

        if(UIECommands.containsKey(cmdName))
            log(UIECommands.get(cmdName).getHelp());
        else if(CCommands.containsKey(cmdName))
            log(CCommands.get(cmdName).getHelp());
        else throw new InvalidArgumentException(2);
    }
    /**Összehasonlítja a paraméterben kapott file tartalmát az output buffer tartalmával, amennyiben a fájl létezik*/
    public void compare(String fileName)   {
        File sketch = new File(System.getProperty("user.dir"), fileName);
        BufferedReader br = null;
        String inputLine;
        try{
            //for each line int the output buffer
            for (int i = 0; i < output.size(); i++) {
                int j = i;
                //open new buffered reader to start from the beginnning of the file
                try {
                    br = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(sketch)));
                }
                catch(FileNotFoundException fnfe){
                    log("File not found");
                    return;
                }
                //compare the file with the next lines
                while(true){
                    //if we reached EOF, the whole file matches
                    if((inputLine = br.readLine()) == null){
                        br.close();
                        log("Correct output");
                        return;
                    }
                    if(!inputLine.equals(output.get(j))) {
                        br.close();
                        break;
                    }
                    j++;
                }
            }
            if(br != null)
                br.close();
        }catch (IOException ioe){
            logStackTrace(ioe);
        }catch(IndexOutOfBoundsException ioobe){
            //if we get IndexOutOfBoundsException the file'd be too long to match
        }

        //if we reached the end of the output buffer without match or encountered IndexOutOfBoundsException that means that the output differs
        log("Different output.");
    }
    /**elmenti az output buffer tartalmát a paraméterben kapott fájlba, amennyiben az még nem létezik*/
    public void save(String fileName)   {
        File sketch = new File(System.getProperty("user.dir"), fileName);
        if(sketch.exists()) {
               log("File already exists");
               return;
        }
        /**Ha még nem létezik a fájl, létrehozza és beleírja az output tartalmát*/
        try {
            if(sketch.createNewFile()){
                PrintWriter pw = new PrintWriter(new FileOutputStream(sketch));
                for (String line : output) {
                    pw.println(line);
                }
                pw.close();
            }
        } catch (IOException ioe) {
            logStackTrace(ioe);
        }
        log("File printed succesfully");
    }
    /**Törli az outputbuffer tartalmát*/
    public void clear(){
        output.clear();
    }
    /**Várakozik a paraméterben megadott számi ms-t*/
    public void wait(int ms){
        try {
            Thread.sleep(ms);
        }catch(InterruptedException ie){/*if thread is interrupted just go with the flow*/}
    }
    /**a paraméterben megadott nevű fájlból beolvas parancsokat és végrehajtja, amennyiben a fájl létezik*/
    public void runSketch(String fileName) throws FileNotFoundException {
        File sketch = new File(System.getProperty("user.dir"), fileName);
        if(sketch.exists()){
            BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(sketch)));
            String inputLine;
            try {
                while ((inputLine = br.readLine()) != null) {
                    Interpret(inputLine);
                }
            } catch (IOException ioe){
                logStackTrace(ioe);
            }
        }else{
            throw new FileNotFoundException(fileName);
        }
    }
    /**Kilép az alkalmazásból*/
    public void exit(){
        System.exit(0);
    }
    /**A paraméterben átadott kivétel stackTrace-ét logolja*/
    private void logStackTrace(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        log(sw.toString());
    }
    /**az átadott stringet a stdout-ra és az output bufferbe is beírja*/
    private void log(String s){
        output.add(s);
        System.out.println(s);
    }
    /**Szervertől hívott függvények, a műveletek sikerességéről adnak visszajelzést*/
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
        for (int i = 0; i < 3*fields.length; i++) {
            for (int j = 0; j < fields[i/3].length; j++) {
                System.out.print(fields[i/3][j].ToString().charAt(i%3));
            }
            System.out.println();
        }
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

    @Override
    public void MakeTestFailure() {
        log("maketest command not in correct format.");
    }
}
