package client.ui.Commands;
/**Argumentum hiba jelzésére szolgáló kivétel, paraméterben az első hibás argumentum sorszámámt várja*/
public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(int argumentNo){
        super("Invalid argument " + argumentNo);
    }
}
