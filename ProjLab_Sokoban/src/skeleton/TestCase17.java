package skeleton;

import model.*;

public class TestCase17 implements TestCase {

    @Override
    public void Test() {
        Logger.Init();

        Worker worker = new Worker();
        Tile from = new Tile();
        Box box = new Box();
        LeverTile to = new LeverTile();
        Tile to2 = new Tile();
        Wall wall = new Wall();
        TrapTile trap = new TrapTile();
        Direction dir = Direction.Right;

        Logger.Register(worker);
        Logger.Register(from);
        Logger.Register(box);
        Logger.Register(to);
        Logger.Register(to2);
        Logger.Register(trap);
        Logger.Register(dir);

        from.InitializeObject(worker);
        to.InitializeObject(box);

        from.SetNeighbour(dir, to);
        to.SetNeighbour(dir.Opposite(), from);
        to.SetNeighbour(dir, to2);
        to2.SetNeighbour(dir.Opposite(), to);
        to2.SetNeighbour(dir, wall);

        to.AddTrapdoor(trap);
        to.Press();

        worker.Move(dir);
    }
}
