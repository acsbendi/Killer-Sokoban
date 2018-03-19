package skeleton;

import model.*;

public class TestCase06 implements TestCase{
	public void Test() {
		Box box1 = new Box();
		Logger.Register(box1);
		Tile tile1 = new Tile();
		Logger.Register(tile1);
		Box box2 = new Box();
		Logger.Register(box2);
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
        
        tile1.InitializeObject(box1);
        tile1.SetNeighbour(direction1,tile2);
        
        tile2.InitializeObject(box2);
        tile2.SetNeighbour(direction2, tile1);
        tile2.SetNeighbour(direction1, tile3);
        
        tile3.SetNeighbour(direction2, tile2);
        
        tile2.Accept(box1, direction1);
	}

	@Override
	public String GetName() {
		return "Doboz dobozt tol";
	}

}
