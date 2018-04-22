package client.ui.Commands.UserInputExecutorCommands;

import client.controller.UserInputExecutor;
import client.ui.Commands.InvalidArgumentException;
import common.util.Direction;

public class UIEMove implements UIECommand {
    @Override
    public void Execute(UserInputExecutor executor, String[] args) throws InvalidArgumentException {
        if(args.length > 2){
            throw new InvalidArgumentException(2);
        } else if(args.length < 2){
            throw new InvalidArgumentException(1);
        }else if("w".equals(args[1])){
            executor.Move(Direction.Up);
        }else if("a".equals(args[1])){
            executor.Move(Direction.Left);
        }else if("s".equals(args[1])){
            executor.Move(Direction.Down);
        }else if("d".equals(args[1])){
            executor.Move(Direction.Right);
        } else{
            throw new InvalidArgumentException(1);
        }
    }

    @Override
    public String getHelp() {
        return "Moves the player to the specified direction";
    }

    @Override
    public String getName() {
        return "move";
    }
}
