package client.ui.Commands.ConsoleCommands;

import client.ui.Console;
import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;
/**A Console által végrehajtandó parancsok interfésze*/
public interface CCommand {
    /**A parancs végrehajtása*/
    public void Execute(Console executor, String[] args) throws InvalidArgumentException;
    /**A parancs leírása*/
    public String getHelp();
    /**A parancs nevének lekérdezése*/
    public String getName();

}
