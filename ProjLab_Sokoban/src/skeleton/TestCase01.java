package skeleton;

import model.Direction;
import model.Tile;
import model.Wall;
import model.Worker;

public class TestCase01 implements TestCase{


    @Override
    public void Test() {
        Logger.Init();

        Worker worker = new Worker();
        Logger.Register(worker);
        Tile from = new Tile();
        Logger.Register(from);
        Tile to = new Tile();
        Logger.Register(to);
        Wall wall = new Wall();
        Logger.Register(wall);

        Direction dir = Direction.Right;
        Logger.Register(dir);

        from.InitializeObject(worker);

        from.SetNeighbour(dir, to);
        to.SetNeighbour(dir.Opposite(), from);

        to.SetNeighbour(dir, wall);

        worker.Move(dir);
    }

    @Override
    public String GetName() {
        return "Munkás üres padlómezőre lép";
    }
}
