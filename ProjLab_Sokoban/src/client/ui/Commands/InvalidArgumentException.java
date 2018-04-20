package client.ui.Commands;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(int argumentNo){
        super("Invalid argument " + argumentNo);
    }
}
