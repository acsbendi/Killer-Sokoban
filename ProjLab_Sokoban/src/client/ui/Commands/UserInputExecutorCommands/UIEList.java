package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**List parancs implementálása*/
public class UIEList implements UIECommand {
    /**Ha az argumentumok száma megfelelő és az argumentumok értéke egyezik az elfogadhatókkal visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length < 2)
            throw new InvalidArgumentException(1);
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        if("own".equals(args[1]))
            executor.OwnResults();
        else if("top".equals(args[1]))
            executor.TopResults();
        else throw new InvalidArgumentException(2);
    }

    @Override
    public String getHelp() {
        return "No content here yet, check back later.";
    }

    @Override
    public String getName() {
        return "list";
    }
}
