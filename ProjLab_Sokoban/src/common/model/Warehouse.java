package common.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import common.util.Direction;
import common.util.Position;

public class Warehouse {
	private static List<Field> fields = new ArrayList<>();
	private static List<Box> boxesInPlay = new ArrayList<>(); // �sszes l�da alapvet�en (?)

	public static void Load(int id) { //egyel�re m�k�dik negat�v koordin�t�kkal is
		try {
			JsonReader jr = Json.createReader(new FileReader("maps/" + id + ".json"));
			JsonObject map = jr.readObject();
			jr.close();
			Map<Position, Field> fieldsTemp = new HashMap<>();
			for (JsonValue preField : map.getJsonArray("fields")) {
				JsonObject field = (JsonObject) preField;
				String type = field.getString("type");
				Field createdField = Field.create(type);
				JsonString liquid = field.getJsonString("liquid");
				if (liquid != null) {
					Liquid createdLiquid = Liquid.create(liquid.getString());
					((Tile) createdField).Place(createdLiquid);
				}
				Position currentPos = new Position(field.getJsonObject("position"));
				for (Direction dir : Direction.values()) {
					Field neighbourField = fieldsTemp.get(currentPos.dirOffset(dir));
					if (neighbourField != null) {
						neighbourField.SetNeighbour(dir.Opposite(), createdField);
						createdField.SetNeighbour(dir, neighbourField);
					}
				}
				fieldsTemp.put(currentPos, createdField);
			}
			for (JsonValue prePlaceholder : map.getJsonArray("placeholders")) {
				JsonObject placeholder = (JsonObject) prePlaceholder;
				String type = placeholder.getString("type");
				Placeholder createdPlaceholder = Placeholder.create(type);
				Position currentPos = new Position(placeholder.getJsonObject("on"));
				((Tile) fieldsTemp.get(currentPos)).InitializeObject(createdPlaceholder);
			}
			for (JsonValue preWire : (JsonArray) map.get("wires")) {
				JsonObject wire = (JsonObject) preWire;
				LeverTile currentLever = (LeverTile) fieldsTemp.get(new Position(wire.getJsonObject("from")));
				for (JsonValue prePosition : wire.getJsonArray("to")) {
					JsonObject position = (JsonObject) prePosition;
					TrapTile currentTrapTile = (TrapTile) fieldsTemp.get(new Position(position));
					currentLever.AddTrapdoor(currentTrapTile);
				}
			}
			fields.addAll(fieldsTemp.values());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassCastException e) {
			//TODO
			//broken maga a f�jl...
		}
	}

	public static boolean IsAllBoxStuck() {
		for (Box box : boxesInPlay)
			if (!box.IsStuck())
				return false;
		return true;
	}
}
