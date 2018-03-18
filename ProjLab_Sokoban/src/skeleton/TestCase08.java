package skeleton;

import model.*;

public class TestCase08 implements TestCase{
	public void Test() {
		Box box = new Box();
		Logger.Register(box);
		Tile tile1 = new Tile();
		Logger.Register(tile1);
		Worker worker = new Worker();
		Logger.Register(worker);
		Tile tile2 = new Tile();
		Logger.Register(tile2);
		HoleTile holeTile = new HoleTile();
		Logger.Register(holeTile);
		Wall wall = new Wall();
        Logger.Register(wall);
        
        Direction direction1 = Direction.Right;
        Logger.Register(direction1);

        Direction direction2 = direction1.Opposite();
        Logger.Register(direction2);
        
        tile1.InitializeObject(box);
        tile1.SetNeighbour(direction1,tile2);
        
        tile2.InitializeObject(worker);
        tile2.SetNeighbour(direction2, tile1);
        tile2.SetNeighbour(direction1, holeTile);
        
        holeTile.SetNeighbour(direction2, tile2);
        
        tile2.Accept(box, direction1);
	}

}