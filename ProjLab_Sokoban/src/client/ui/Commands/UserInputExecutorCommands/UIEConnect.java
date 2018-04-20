package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIEConnect implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {

        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.Connect();
    }

    @Override
    public String getHelp() {
        return "Connects to the server.";
    }

    @Override
    public String getName() {
        return "connect";
    }
}
