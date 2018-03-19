package skeleton;

import model.Box;
import model.Direction;
import model.LeverTile;
import model.Tile;
import model.TrapTile;
import model.Wall;
import model.Worker;

public class TestCase12 implements TestCase {

	@Override
	public void Test() {
		Logger.Init();
		Direction dir = Direction.Down;
		Logger.Register(dir);
		TrapTile trapTile=new TrapTile();
		Logger.Register(trapTile);
		Worker worker = new Worker();
		Logger.Register(worker);
		Tile wTile = new Tile();
		Logger.Register(wTile);
		Box box=new Box();
		Logger.Register(box);
		Tile bTile=new Tile();
		Logger.Register(bTile);
		LeverTile leverTile = new LeverTile();
		Logger.Register(leverTile);
		Wall wall=new Wall();
		Logger.Register(wall);
		wTile.InitializeObject(worker);
		bTile.InitializeObject(box);
		wTile.SetNeighbour(dir, bTile);
		bTile.SetNeighbour(dir.Opposite(), wTile);
		bTile.SetNeighbour(dir, leverTile);
		leverTile.SetNeighbour(dir.Opposite(), bTile);
		leverTile.SetNeighbour(dir, wall);
		worker.Move(dir);
	}

	@Override
	public String GetName() {
		return "Munkas dobozt kapcsolomezore tol";
	}

}
