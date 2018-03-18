package model;

import skeleton.Logger;

public class Wall implements Field {

	@Override
	public void Check(Direction dir) {
		Logger.BeginMethod(this,"Check", dir);

		Logger.EndMethod(this,"Check", dir);
	}

	@Override
	public void Accept(Placeholder obj, Direction dir) {
		Logger.BeginMethod(this,"Accept", dir);

		Logger.EndMethod(this,"Accept", dir);
	}

}
