package client.ui;
import client.controller.UserInputExecutor;

public abstract class UserInterface {
	public abstract void InitializeWarehouse(GridSquare[][] gridSquares);

	protected UserInputExecutor userInputExecutor;
	public void SetUserInputExecutor(UserInputExecutor userInputExecutor) {
		this.userInputExecutor = userInputExecutor;
	}
	public abstract void ConnectionResult(boolean res); // connection successful/failure, lasd: doksi
	public abstract void Disconnected(); //
	public abstract void RegistrationSuccess(); //
	public abstract void RegistrationFailure(String error); // error kiírása
	public abstract void LoginSuccess(); //
	public abstract void LoginFailure(String err); // error kiírása
	public abstract void LogoutSuccess(); //
	public abstract void LogoutFailure(String err); // error kiírása
	public abstract void Results(String msg); // msg kiírása
	public abstract void ResultFailure(String err); // error kiírása
	public abstract void EnterSuccess(); //
	public abstract void EnterFailure(String err); // error kiírása
	public abstract void LeaveSuccess(); //
	public abstract void LeaveFailure(String err); // error kiírása
	public abstract void GameStarted();
	public abstract void UpdateScreen();
	public abstract void GameFinished();
	public abstract void OfflineFailure(); // ha online parancsot offline használunk
	public abstract void OnlineFailure(); // ez meg fordítva
	public abstract void MakeTestFailure(); // ez meg fordítva
}
