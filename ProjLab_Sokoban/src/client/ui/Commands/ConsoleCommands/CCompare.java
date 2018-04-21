package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;

public class CCompare implements CCommand {
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
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
