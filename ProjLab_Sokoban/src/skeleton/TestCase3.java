package skeleton;

import model.*;

public class TestCase3 implements TestCase {
    @Override
    public void Test() {
        Logger.Init();

        Worker worker = new Worker();
        Logger.Register(worker);
        Tile from = new Tile();
        Logger.Register(from);
        Tile to = new Tile();
        Logger.Register(to);
        Box box = new Box();
        Logger.Register(box);
        Tile boxNeighbour = new Tile();
        Logger.Register(boxNeighbour);
        Wall wall = new Wall();
        Logger.Register(wall);

        Direction dir = Direction.Right;
        Logger.Register(dir);

        from.InitializeObject(worker);
        to.InitializeObject(box);

        from.SetNeighbour(dir, to);
        to.SetNeighbour(dir.Opposite(), from);

        to.SetNeighbour(dir, boxNeighbour);
        boxNeighbour.SetNeighbour(dir.Opposite(), to);

        boxNeighbour.SetNeighbour(dir, wall);

        worker.Move(dir);
    }
}
