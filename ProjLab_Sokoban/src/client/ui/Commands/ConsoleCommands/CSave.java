package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;
/**save parancs implementálása*/
public class CSave implements CCommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        else if (args.length < 2)
            throw new InvalidArgumentException(1);
        executor.save(args[1]);
    }

    @Override
    public String getHelp() {
        return "Saves the output to file";
    }

    @Override
    public String getName() {
        return "save";
    }
}
