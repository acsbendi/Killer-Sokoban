package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**Connect parancs implementálása*/
public class UIEConnect implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
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
