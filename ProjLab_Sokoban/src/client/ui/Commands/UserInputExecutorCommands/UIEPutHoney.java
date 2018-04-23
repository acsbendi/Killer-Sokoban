package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
/**puthoney parancs implementációja*/
public class UIEPutHoney implements UIECommand {
	/**Ha az argumentumok száma megfelelő visszahívja a végrehajtó megfelelő tagfüggvényét, egyébként InvalidArgumentExceptiont dob*/
	@Override
	public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
		if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.PutHoney();

	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "puts honey";
	}

	@Override
	public String getName() {
		return "puthoney";
	}

}
