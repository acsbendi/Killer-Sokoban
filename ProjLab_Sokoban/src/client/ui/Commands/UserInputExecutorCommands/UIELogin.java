package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIELogin implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 3)
            throw new InvalidArgumentException(3);
        else if(args.length < 2)
            throw new InvalidArgumentException(1);
        else if(args.length < 3)
            throw new InvalidArgumentException(2);
        executor.Login(args[1], args[2]);
    }

    @Override
    public String getHelp() {
        return "Tries to log in the user with the given name and password";
    }

    @Override
    public String getName() {
        return "login";
    }
}
