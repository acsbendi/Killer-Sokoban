package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;

public class CExit implements CCommand {
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.exit();
    }

    @Override
    public String getHelp() {
        return "Exits the application.";
    }

    @Override
    public String getName() {
        return "exit";
    }
}
