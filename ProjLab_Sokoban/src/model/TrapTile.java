package model;

public class TrapTile extends Tile {
	private int openCounter=0;
	public void SignOn() {
		openCounter++;
		//TODO
	}
	public void SignOff() {
		openCounter--;
		//TODO
	}
	@Override
	public void Check(Direction dir) {
		// TODO Auto-generated method stub
		super.Check(dir);
	}
	private boolean IsClosed() {
		return openCounter==0;
	}
	public void Check() {
		if (!IsClosed())
			placeholder.Destroy();
	}
	

}
