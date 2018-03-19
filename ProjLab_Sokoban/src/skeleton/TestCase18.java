package skeleton;

import model.*;

public class TestCase18 implements TestCase {

    @Override
    public void Test() {
        Worker worker = new Worker();
        Tile from = new Tile();
        Box box = new Box();
        Tile to = new Tile();
        TrapTile trap = new TrapTile();
        Wall wall = new Wall();
        Direction dir = Direction.Right;

        Logger.Register(worker);
        Logger.Register(from);
        Logger.Register(box);
        Logger.Register(to);
        Logger.Register(trap);
        Logger.Register(wall);
        Logger.Register(dir);

        from.InitializeObject(worker);
        to.InitializeObject(box);

        from.SetNeighbour(dir, to);
        to.SetNeighbour(dir.Opposite(), from);
        to.SetNeighbour(dir, trap);
        trap.SetNeighbour(dir.Opposite(), to);
        trap.SetNeighbour(dir, wall);

        worker.Move(dir);
    }

    @Override
    public String GetName() {
        return "Játékos csapdamezőre tol ládát";
    }
}
