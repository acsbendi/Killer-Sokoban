package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**placehoney paranc implementációja*/
public class UIEPlaceHoney implements UIECommand {
    /**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.PlaceHoney();
    }

    @Override
    public String getHelp() {
        return "Places honey on the current Tile";
    }

    @Override
    public String getName() {
        return "placehoney";
    }
}
