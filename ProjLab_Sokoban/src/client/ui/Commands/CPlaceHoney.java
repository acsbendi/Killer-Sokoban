package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CPlaceHoney implements ICommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.PlaceHoney();
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getName() {
        return "placehoney";
    }
}
