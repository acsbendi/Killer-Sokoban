package client.ui;

import java.util.ArrayList;
import java.util.List;

public abstract class UserInterface {
	protected List<FieldView> fields=new ArrayList<>();
	protected SokobanClient userInputExecutor;
	public abstract void ConnectionResult(boolean res);
	public abstract void Disconnected();
	public abstract void RegistrationSuccess();
	public abstract void RegistrationFailure(String error);
	public abstract void LoginSuccess();
	public abstract void LoginFailure(String err);
	public abstract void Results(String msg);
	public abstract void ResultFailure(String err);
	public abstract void EnterSuccess();
	public abstract void EnterFailure(String err);
	public abstract void LeaveSuccess();
	public abstract void LeaveFailure(String err);
	public abstract void GameStarted();
	public abstract void UpdateScreen();
	public abstract void GameFinished();

}
