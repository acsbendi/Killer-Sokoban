package skeleton;

import model.*;

import java.util.HashMap;
import java.util.Map;

public class Logger {
    static private Map<Object,String> objects = new HashMap<>();
    static private int boxCounter = 0;
    static private int directionCounter = 0;
    static private int goalTileCounter = 0;
    static private int holeTileCounter = 0;
    static private int leverTileCounter = 0;
    static private int tileCounter = 0;
    static private int trapTileCounter = 0;
    static private int wallCounter = 0;
    static private int workerCounter = 0;

    static private int indentLevel = 0;

    static public void Register(Box box) {
        objects.put(box,"box" + Integer.toString(boxCounter+1));
        boxCounter++;
    }

    static public void Register(Direction direction) {
        objects.put(direction,"direction" + Integer.toString(directionCounter+1));
        directionCounter++;
    }

    static public void Register(GoalTile goalTile) {
        objects.put(goalTile,"goalTile" + Integer.toString(goalTileCounter+1));
        goalTileCounter++;
    }

    static public void Register(HoleTile holeTile) {
        objects.put(holeTile,"holeTile" + Integer.toString(holeTileCounter+1));
        holeTileCounter++;
    }

    static public void Register(LeverTile leverTile) {
        objects.put(leverTile,"leverTile" + Integer.toString(leverTileCounter+1));
        leverTileCounter++;
    }

    static public void Register(Tile tile) {
        objects.put(tile,"tile" + Integer.toString(tileCounter+1));
        tileCounter++;
    }

    static public void Register(TrapTile trapTile) {
        objects.put(trapTile,"trapTile" + Integer.toString(trapTileCounter+1));
        trapTileCounter++;
    }

    static public void Register(Wall wall) {
        objects.put(wall,"wall" + Integer.toString(wallCounter+1));
        wallCounter++;
    }

    static public void Register(Worker worker) {
        objects.put(worker,"worker" + Integer.toString(workerCounter+1));
        workerCounter++;
    }

    static public void BeginMethod(Object callee, String methodName){
        for(int i = 0; i < indentLevel; i++) {
            System.out.println("   ");
        }
        System.out.println("->" + objects.get(callee) + "." + methodName + "()");
        indentLevel++;
    }

    static public void BeginMethod(Object callee, String methodName, Object parameter){
        for(int i = 0; i < indentLevel; i++) {
            System.out.println("   ");
        }
        System.out.println("->" + objects.get(callee) + "." + methodName + "(" + objects.get(parameter) + ")");
        indentLevel++;
    }

    static public void BeginMethod(Object callee, String methodName, Object parameter1, Object parameter2){
        for(int i = 0; i < indentLevel; i++) {
            System.out.println("   ");
        }
        System.out.println("->" + objects.get(callee) + "." + methodName + "(" + objects.get(parameter1) + "," + objects.get(parameter2) + ")");
        indentLevel++;
    }

    static public void EndMethod(Object callee, String methodName){
        for(int i = 0; i < indentLevel; i++) {
            System.out.println("   ");
        }
        System.out.println("<-" + objects.get(callee) + "." + methodName + "()");
        indentLevel--;
    }

    static public void EndMethod(Object callee, String methodName, Object parameter){
        for(int i = 0; i < indentLevel; i++) {
            System.out.println("   ");
        }
        System.out.println("<-" + objects.get(callee) + "." + methodName + "(" + objects.get(parameter) + ")");
        indentLevel--;
    }

    static public void EndMethod(Object callee, String methodName, Object parameter1, Object parameter2){
        for(int i = 0; i < indentLevel; i++) {
            System.out.println("   ");
        }
        System.out.println("<-" + objects.get(callee) + "." + methodName + "(" + objects.get(parameter1) + "," + objects.get(parameter2) + ")");
        indentLevel--;
    }

    static public void Init(){
        boxCounter = 0;
        directionCounter = 0;
        goalTileCounter = 0;
        holeTileCounter = 0;
        leverTileCounter = 0;
        tileCounter = 0;
        trapTileCounter = 0;
        wallCounter = 0;
        workerCounter = 0;
        indentLevel = 0;
        objects.clear();
    }

}
