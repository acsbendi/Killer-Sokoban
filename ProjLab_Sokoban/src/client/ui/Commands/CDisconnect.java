package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CDisconnect implements ICommand{

    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.Disconnect();
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
