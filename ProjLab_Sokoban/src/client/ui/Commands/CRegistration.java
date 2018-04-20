package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CRegistration implements ICommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 3)
            throw new InvalidArgumentException(3);
        else if(args.length < 2)
            throw new InvalidArgumentException(1);
        else if(args.length < 3)
            throw new InvalidArgumentException(2);
        executor.Register(args[1], args[2]);
    }

    @Override
    public String getHelp() {
        return "Regesters a new user.";
    }

    @Override
    public String getName() {
        return "reg";
    }
}
