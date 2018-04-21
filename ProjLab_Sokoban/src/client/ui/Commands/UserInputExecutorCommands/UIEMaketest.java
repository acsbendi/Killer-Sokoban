package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIEMaketest implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {

    }

    @Override
    public String getHelp() {
        return "Creates a new test scenario according to the arguments.";
    }

    @Override
    public String getName() {
        return null;
    }
}
