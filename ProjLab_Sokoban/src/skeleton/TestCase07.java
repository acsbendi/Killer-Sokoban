package skeleton;

import model.*;

public class TestCase07 implements TestCase{
	public void Test() {
		Logger.Init();
		Box box = new Box();
		Logger.Register(box);
		Tile tile1 = new Tile();
		Logger.Register(tile1);
		Worker worker = new Worker();
		Logger.Register(worker);
		Tile tile2 = new Tile();
		Logger.Register(tile2);
		Tile tile3 = new Tile();
		Logger.Register(tile3);
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
        tile2.SetNeighbour(direction1, tile3);
        
        tile3.SetNeighbour(direction2, tile2);
        
        tile2.Accept(box, direction1);
	}

    @Override
    public String GetName() {
        return "Doboz munkást tol";
    }

}
