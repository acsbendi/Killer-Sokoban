package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;
/**Compare parancs megvalósítása*/
public class CCompare implements CCommand {
    /**ha az argumentumok száma megfelelő akkor meghívja a végrehajtó megfelelő függvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        if(args.length < 2)
            throw new InvalidArgumentException(1);
        executor.compare(args[1]);
    }

    @Override
    public String getHelp() {
        return "Compares the output with the file specified int first argument";
    }

    @Override
    public String getName() {
        return "compare";
    }
}
