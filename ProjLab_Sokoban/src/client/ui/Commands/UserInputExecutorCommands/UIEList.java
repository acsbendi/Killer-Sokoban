package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIEList implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {

    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getName() {
        return "list";
    }
}
