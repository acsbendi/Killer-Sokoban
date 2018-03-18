package skeleton;

import model.Direction;
import model.Tile;
import model.Wall;
import model.Worker;

public class TestCase02 implements TestCase{
    @Override
    public void Test() {
        Logger.Init();

        Worker worker = new Worker();
        Logger.Register(worker);
        Tile from = new Tile();
        Logger.Register(from);
        Wall to = new Wall();
        Logger.Register(to);

        Direction dir = Direction.Right;
        Logger.Register(dir);

        from.InitializeObject(worker);

        from.SetNeighbour(dir, to);

        worker.Move(dir);
    }
}
