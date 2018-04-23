package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**A UserInputExecutor által vérehajtandó parancsok interfésze*/
public interface UIECommand {
    /**A parancs végrehajtása*/
    public void Execute(UserInputExecutor executor,  String[] args) throws InvalidArgumentException;
    /**Parancs leírásának lekérdezése*/
    public String getHelp();
    /**Parancs nevének lekérdezése*/
    public String getName();

}
