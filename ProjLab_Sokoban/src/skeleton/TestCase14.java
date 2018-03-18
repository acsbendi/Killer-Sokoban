package skeleton;

import model.*;

public class TestCase14 implements TestCase {
    @Override
    public void Test() {
        Logger.Init();

        Tile tile1 = new Tile();
        Logger.Register(tile1);
        Tile tile2 = new Tile();
        Logger.Register(tile2);
        Tile tile3 = new Tile();
        Logger.Register(tile3);
        Wall wall = new Wall();
        Logger.Register(wall);
        Worker worker1 = new Worker();
        Logger.Register(worker1);
        Worker worker2 = new Worker();
        Logger.Register(worker2);
        Box box = new Box();
        Logger.Register(box);

        Direction direction1 = Direction.Right;
        Logger.Register(direction1);

        Direction direction2 = direction1.Opposite();
        Logger.Register(direction2);

        tile1.InitializeObject(worker1);
        tile1.SetNeighbour(direction1,tile2);

        tile2.InitializeObject(box);
        tile2.SetNeighbour(direction2,tile1);
        tile2.SetNeighbour(direction1,tile3);

        tile3.InitializeObject(worker2);
        tile3.SetNeighbour(direction2,tile2);
        tile3.SetNeighbour(direction1,wall);

        worker1.Move(direction1);
    }
}
