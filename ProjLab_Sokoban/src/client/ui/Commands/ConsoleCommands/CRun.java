package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;

import java.io.FileNotFoundException;

public class CRun implements CCommand {
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        if(args.length < 2)
            throw new InvalidArgumentException(1);
        try {
            executor.runSketch(args[1]);
        }catch (FileNotFoundException fnfe){
            throw new InvalidArgumentException(1);
        }
    }

    @Override
    public String getHelp() {
        return "Executes the sketch in the argument file";
    }

    @Override
    public String getName() {
        return "run";
    }
}
