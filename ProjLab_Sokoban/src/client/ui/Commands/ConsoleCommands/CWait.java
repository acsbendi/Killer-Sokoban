package client.ui.Commands.ConsoleCommands;

import client.ui.Commands.InvalidArgumentException;
import client.ui.Console;
/**wait parancs implementálása*/
public class CWait implements CCommand{
    /**Ha az argumentumok száma és típusa megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(Console executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        if(args.length < 2)
            throw new InvalidArgumentException(1);
        int arg;
        try {
            arg = Integer.parseInt(args[1]);
        }catch (NumberFormatException nfe){
            throw new InvalidArgumentException(1);
        }
        executor.wait(arg);
    }

    @Override
    public String getHelp() {
        return "Wats for argument ms.";
    }

    @Override
    public String getName() {
        return "wait";
    }
}
