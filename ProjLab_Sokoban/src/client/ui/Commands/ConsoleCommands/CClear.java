package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;
/**Clear parancs megvalósítása*/
public class CClear implements CCommand {
    /**Ha megfelelő az argumentumok száma (0), akkor visszahívaj a végrehajtó függvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.clear();
    }
    @Override
    public String getHelp() {
        return "Clears the output buffer.";
    }

    @Override
    public String getName() {
        return "clear";
    }
}
