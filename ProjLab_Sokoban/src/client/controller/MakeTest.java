package client.controller;

import client.ui.FieldView;
import common.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by bendi1 on 4/22/2018.
 */
public class MakeTest {
    private final String[] data;
    private int dataIndex;
    private int width;
    private int height;
    private int i;
    private int j;

    public List<Field> GetFields() {
        List<Field> list = new ArrayList<Field>();
        for (Field[] array : fields) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public FieldView[][] GetFieldViews() {
        return fieldViews;
    }

    public List<Box> GetBoxes() {
        return boxes;
    }

    private Field[][] fields;
    private FieldView[][] fieldViews;
    private List<Box> boxes;

    private List<List<Integer>> connections;
    private List<LeverTile> leverTiles;
    private List<TrapTile> trapTiles;

    public MakeTest(String[] args){
        this.data = args;
        dataIndex = 1;
    }

    private void Initialize(){
        height = Integer.parseInt(data[dataIndex++]);
        width = Integer.parseInt(data[dataIndex++]);

        fields = new Field[height+2][width+2];
        fieldViews = new FieldView[height+2][width+2];
        boxes = new ArrayList<>();

        connections = new ArrayList<>();
        leverTiles = new ArrayList<>();
        trapTiles = new ArrayList<>();
    }

    private void ParseLiquid(Tile tile){
        switch (data[dataIndex]){
            case "oil":
                tile.Place(new Oil());
                dataIndex++;
                break;
            case "honey":
                tile.Place(new Honey());
                dataIndex++;
                break;
            default:
                break;
        }
    }

    private void ParsePlaceholder(Tile tile) throws Exception{
        switch (data[dataIndex]){
            case "worker":
                tile.InitializeObject(new Worker());
                dataIndex++;
                break;
            case "player":
                Worker worker = new Worker();
                Worker.localWorker = worker;
                tile.InitializeObject(worker);
                dataIndex++;
                break;
            case "box":
                Box box = new Box();
                boxes.add(box);
                tile.InitializeObject(box);
                dataIndex++;
                break;
            default:
                break;
        }
    }

    private void ParseConnections() throws Exception{
        List<Integer> currentConnections = new ArrayList<>();

        while(true){
            try {
                currentConnections.add(Integer.parseInt(data[dataIndex]) - 1);
                dataIndex++;
            } catch (Exception e){
                connections.add(currentConnections);
                return;
            }
        }
    }

    private void CreateConnections() throws Exception{
        for (int k = 0; k < leverTiles.size(); k++) {
            for (int l = 0; l < connections.get(k).size(); l++) {
                leverTiles.get(k).AddTrapdoor(trapTiles.get(connections.get(k).get(l)));
            }
        }
    }

    private void ParseTile(Tile tile) throws Exception{
        if(dataIndex != data.length) {
            ParseLiquid(tile);
            ParsePlaceholder(tile);
        }
        fields[i][j] = tile;
    }

    private void ParseField() throws Exception{
        switch (data[dataIndex++]){
            case "tile":
                Tile tile = new Tile();
                ParseTile(tile);
                break;
            case "trap":
                TrapTile trapTile = new TrapTile();
                ParseTile(trapTile);
                trapTiles.add(trapTile);
                break;
            case "lever":
                LeverTile leverTile = new LeverTile();
                ParseTile(leverTile);
                ParseConnections();
                leverTiles.add(leverTile);
                break;
            case "goal":
                GoalTile goalTile = new GoalTile();
                ParseTile(goalTile);
                break;
            case "hole":
                fields[i][j] = new Hole();
                break;
            case "wall":
                fields[i][j] = new Wall();
                break;
            default:
                //TODO invalid argument
                break;
        }
    }

    public boolean Parse(){
        try {
            Initialize();
            for (i = 0; i < fieldViews.length; i++) {
                for (j = 0; j < fieldViews[i].length; j++) {
                    if(i == 0 || i == fieldViews.length - 1  || j == fieldViews[i].length - 1|| j == 0) {
                        fields[i][j] = new Wall();
                        fieldViews[i][j] = new FieldView(fields[i][j]);
                    }
                    else{
                        ParseField();
                        fieldViews[i][j] = new FieldView(fields[i][j]);
                    }
                }
            }
            CreateConnections();
        } catch (Exception e){
            return false;
        }
        return true;
    }
}