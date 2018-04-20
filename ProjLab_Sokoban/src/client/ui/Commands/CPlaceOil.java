package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CPlaceOil implements ICommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.PlaceOil();
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getName() {
        return "placeoil";
    }
}
