package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CLogout implements ICommand {
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
