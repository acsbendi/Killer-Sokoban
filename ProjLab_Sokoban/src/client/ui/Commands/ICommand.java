package client.ui.Commands;

public interface ICommand {
    public void Ececute(String[] args);
    public String getHelp();
    public String getName();

}
