package client.ui.Commands;

import client.controller.UserInputExecutor;

public class CTopResults implements ICommand{

	@Override
	public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
		if(args.length > 1)
            throw new InvalidArgumentException(1);
        executor.TopResults();
		
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return "topresults";
	}

}
