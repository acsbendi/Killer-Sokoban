package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CEnter implements ICommand{
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2)
            throw new InvalidArgumentException(2);
        int arg;
        try{arg = Integer.parseInt(args[1]);}
        catch (NumberFormatException nfe){throw new InvalidArgumentException(1);}
        executor.Enter(arg);

    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getName() {
        return "enter";
    }
}
