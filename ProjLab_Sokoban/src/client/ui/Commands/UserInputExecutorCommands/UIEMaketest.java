package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**Maketest parancs implementálása*/
public class UIEMaketest implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length < 2)
            throw new InvalidArgumentException(1);
        executor.MakeTest(args);
    }

    @Override
    public String getHelp() {
        return "Creates a new test scenario according to the arguments.";
    }

    @Override
    public String getName() {
        return "maketest";
    }
}
