package skeleton;

import model.Direction;
import model.Tile;
import model.Worker;

public class TestCase4 implements TestCase {
    @Override
    public void Test() {
        Logger.Init();

        Worker worker1 = new Worker();
        Logger.Register(worker1);
        Tile from = new Tile();
        Logger.Register(from);
        Worker worker2 = new Worker();
        Logger.Register(worker2);
        Tile to = new Tile();
        Logger.Register(to);

        Direction dir = Direction.Right;
        Logger.Register(dir);

        from.InitializeObject(worker1);
        to.InitializeObject(worker2);

        from.SetNeighbour(dir, to);
        to.SetNeighbour(dir.Opposite(), from);

        worker1.Move(dir);
    }
}
