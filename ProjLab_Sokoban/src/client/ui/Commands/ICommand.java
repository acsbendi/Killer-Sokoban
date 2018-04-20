package client.ui.Commands;

import client.controller.UserInputExecutor;

public interface ICommand {
    public void Execute(UserInputExecutor executor,  String[] args) throws InvalidArgumentException;
    public String getHelp();
    public String getName();

}
