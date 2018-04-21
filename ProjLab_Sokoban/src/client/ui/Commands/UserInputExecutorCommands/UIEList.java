package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;

public class UIEList implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length < 2)
            throw new InvalidArgumentException(2);
        if(args.length > 2)
            throw new InvalidArgumentException(3);
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
