package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIELogout implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.Logout();
    }

    @Override
    public String getHelp() {
        return "Logs out the currently logged in user";
    }

    @Override
    public String getName() {
        return "logout";
    }
}
