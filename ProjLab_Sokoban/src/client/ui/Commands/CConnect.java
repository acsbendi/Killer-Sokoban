package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CConnect implements ICommand {
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
