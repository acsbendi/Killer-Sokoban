package skeleton;

import model.Direction;
import model.GoalTile;
import model.Tile;
import model.Wall;
import model.Worker;

public class TestCase10 implements TestCase {

	@Override
	public void Test() {
		Logger.Init();
		Direction dir = Direction.Up;
		Logger.Register(dir);
		Worker worker = new Worker();
		Logger.Register(worker);
		Tile wTile = new Tile();
		Logger.Register(wTile);
		GoalTile goalTile = new GoalTile();
		Logger.Register(goalTile);
		Wall wall=new Wall();
		Logger.Register(wall);
		wTile.InitializeObject(worker);
		wTile.SetNeighbour(dir, goalTile);
		goalTile.SetNeighbour(dir.Opposite(), wTile);
		goalTile.SetNeighbour(dir, wall);
		worker.Move(dir);
	}

	@Override
	public String GetName() {
		return "Munkas celmezore lep";
	}

}
