package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**Enter parancs implementálása*/
public class UIEEnter implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        if(args.length < 2)
            throw new InvalidArgumentException(1);
        int arg;
        try{arg = Integer.parseInt(args[1]);}
        catch (NumberFormatException nfe){throw new InvalidArgumentException(1);}
        executor.Enter(arg);

    }

    @Override
    public String getHelp() {
        return "Enters";
    }

    @Override
    public String getName() {
        return "enter";
    }
}
