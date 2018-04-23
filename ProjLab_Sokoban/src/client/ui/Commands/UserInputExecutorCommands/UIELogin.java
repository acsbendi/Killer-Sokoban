package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**Login parancs implementálása*/
public class UIELogin implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
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
