package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**Logout parancs implementálása*/
public class UIELogout implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
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
