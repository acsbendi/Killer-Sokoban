package skeleton;

import model.*;

public class TestCase13 implements TestCase {
    @Override
    public void Test() {
        Tile tile = new Tile();
        Logger.Register(tile);
        TrapTile trapTile = new TrapTile();
        Logger.Register(trapTile);
        Wall wall = new Wall();
        Logger.Register(wall);
        Worker worker = new Worker();
        Logger.Register(worker);

        Direction direction1 = Direction.Right;
        Logger.Register(direction1);

        Direction direction2 = direction1.Opposite();
        Logger.Register(direction2);

        tile.InitializeObject(worker);
        tile.SetNeighbour(direction1,trapTile);

        trapTile.SetNeighbour(direction2,tile);
        trapTile.SetNeighbour(direction1,wall);

        worker.Move(direction1);
    }
}
