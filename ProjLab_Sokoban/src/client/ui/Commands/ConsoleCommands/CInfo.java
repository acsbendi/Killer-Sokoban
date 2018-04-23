package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;
/**info parancs implementálása*/
public class CInfo implements CCommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        if(args.length < 2)
            executor.printInfo("all");
        else
            executor.printInfo(args[1]);
    }

    @Override
    public String getHelp() {
        return "Prints more details about the command in the argument";
    }

    @Override
    public String getName() {
        return "info";
    }
}
