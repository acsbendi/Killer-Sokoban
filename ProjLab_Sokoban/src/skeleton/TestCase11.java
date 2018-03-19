package skeleton;

import model.Box;
import model.Direction;
import model.GoalTile;
import model.Tile;
import model.Wall;
import model.Worker;

public class TestCase11 implements TestCase {

	@Override
	public void Test() {
		Logger.Init();
		Direction dir = Direction.Up;
		Logger.Register(dir);
		Worker worker = new Worker();
		Logger.Register(worker);
		Tile wTile = new Tile();
		Logger.Register(wTile);
		Box box=new Box();
		Logger.Register(box);
		Tile bTile=new Tile();
		Logger.Register(bTile);
		GoalTile goalTile = new GoalTile();
		Logger.Register(goalTile);
		Wall wall=new Wall();
		Logger.Register(wall);
		wTile.InitializeObject(worker);
		bTile.InitializeObject(box);
		wTile.SetNeighbour(dir, bTile);
		bTile.SetNeighbour(dir.Opposite(), wTile);
		bTile.SetNeighbour(dir, goalTile);
		goalTile.SetNeighbour(dir.Opposite(), bTile);
		goalTile.SetNeighbour(dir, wall);
		worker.Move(dir);
	}

	@Override
	public String GetName() {
		return "Munkas dobozt celmezore tol";
	}

}
