package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIERegistration implements UIECommand {
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
        return "Registers a new user.";
    }

    @Override
    public String getName() {
        return "reg";
    }
}
