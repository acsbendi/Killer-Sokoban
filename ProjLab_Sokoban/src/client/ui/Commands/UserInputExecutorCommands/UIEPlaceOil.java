package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**placeoil parancs implementációja*/
public class UIEPlaceOil implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.PlaceOil();
    }

    @Override
    public String getHelp() {
        return "Places oil on current tile";
    }

    @Override
    public String getName() {
        return "placeoil";
    }
}
