package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public interface UIECommand {
    public void Execute(UserInputExecutor executor,  String[] args) throws InvalidArgumentException;
    public String getHelp();
    public String getName();

}
