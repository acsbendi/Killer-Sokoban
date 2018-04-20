package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
import common.util.Direction;

public class UIEStep implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2){
            throw new InvalidArgumentException(2);
        } else if("w".equals(args[1])){
            executor.Step(Direction.Up);
        }else if("a".equals(args[1])){
            executor.Step(Direction.Left);
        }else if("s".equals(args[1])){
            executor.Step(Direction.Down);
        }else if("d".equals(args[1])){
            executor.Step(Direction.Right);
        } else{
            throw new InvalidArgumentException(1);
        }
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public String getName() {
        return "step";
    }
}
