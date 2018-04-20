package client.ui.Commands.ConsoleCommands;

import client.ui.Console;
import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;

public interface CCommand {
    public void Execute(Console executor, String[] args) throws InvalidArgumentException;
    public String getHelp();
    public String getName();

}
