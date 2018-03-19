package skeleton;

import model.Box;
import model.Direction;
import model.HoleTile;
import model.Tile;
import model.Worker;

public class TestCase09 implements TestCase {

	@Override
	public void Test() {
		Logger.Init();
		Direction dir = Direction.Left;
		Logger.Register(dir);
		Worker worker = new Worker();
		Logger.Register(worker);
		Tile wTile = new Tile();
		Logger.Register(wTile);
		Tile bTile = new Tile();
		Logger.Register(bTile);
		Box box = new Box();
		Logger.Register(box);
		HoleTile holeTile = new HoleTile();
		Logger.Register(holeTile);
		wTile.InitializeObject(worker);
		bTile.InitializeObject(box);
		wTile.SetNeighbour(dir, wTile);
		bTile.SetNeighbour(dir.Opposite(), wTile);
		bTile.SetNeighbour(dir, holeTile);
		worker.Move(dir);
	}

	@Override
	public String GetName() {
		return "Munkas lyukba tol dobozt";
	}

}
