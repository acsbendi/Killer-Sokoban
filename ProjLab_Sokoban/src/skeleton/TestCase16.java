package skeleton;

import model.*;

public class TestCase16 implements TestCase {

    @Override
    public void Test() {
        Logger.Init();

        Worker worker1 = new Worker();
        Tile tile1 = new Tile();
        Worker worker2 = new Worker();
        Tile tile2 = new Tile();
        Box box = new Box();
        Tile boxField = new Tile();
        Wall wall = new Wall();
        Direction dir = Direction.Right;

        Logger.Register(worker1);
        Logger.Register(tile1);
        Logger.Register(worker2);
        Logger.Register(tile2);
        Logger.Register(box);
        Logger.Register(boxField);
        Logger.Register(wall);
        Logger.Register(dir);

        tile1.InitializeObject(worker1);
        tile2.InitializeObject(worker2);
        boxField.InitializeObject(box);

        tile1.SetNeighbour(dir, tile2);
        tile2.SetNeighbour(dir.Opposite(), tile1);
        tile2.SetNeighbour(dir, boxField);
        boxField.SetNeighbour(dir.Opposite(), tile2);
        boxField.SetNeighbour(dir, wall);

        worker1.PushedBy(box, dir);
    }
}
