package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIEDisconnect implements UIECommand {

    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.Disconnect();
    }

    @Override
    public String getHelp() {
        return "Disconnects the server.";
    }

    @Override
    public String getName() {
        return "disconnect";
    }
}
