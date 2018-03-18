package model;

import skeleton.Logger;

public class TrapTile extends Tile {
	private int openCounter=0;

	public void SignOn() {
		Logger.BeginMethod(this,"SignOn");

		openCounter++;

		Logger.EndMethod(this,"SignOn");
	}

	public void SignOff() {
		Logger.BeginMethod(this,"SignOff");

		openCounter--;

		Logger.EndMethod(this,"SignOff");
	}

	@Override
	public void Check(Direction dir) {
		Logger.BeginMethod(this,"Check", dir);

		Check();
		neighbours.get(dir).Check(dir);

		Logger.EndMethod(this,"Check", dir);
	}

	private boolean IsClosed() {
		Logger.BeginMethod(this,"IsClosed");

		Logger.EndMethod(this,"IsClosed");
		return openCounter==0;
	}

	public void Check() {
		Logger.BeginMethod(this,"Check");

		if (!IsClosed() && placeholder != null)
			placeholder.Destroy();

		Logger.EndMethod(this,"Check");
	}
	

}
