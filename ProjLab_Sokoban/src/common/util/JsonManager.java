package common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.TreeMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import common.model.Box;
import common.model.Field;
import common.model.LeverTile;
import common.model.Liquid;
import common.model.Placeholder;
import common.model.Tile;
import common.model.TrapTile;
import common.model.Worker;

public class JsonManager {

	public static File ResolveFileId(int level_id){
		try {
			return new File( JsonManager.class.getResource("/" + level_id + ".json").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void EnforceConfigFile(File config, TreeMap<Position, Field> pitch, List<Box> boxes, List<Worker> workers)
			throws FileNotFoundException, ClassCastException { // egyel�re m�k�dik negat�v koordin�t�kkal is
		JsonReader jr = Json.createReader(new FileReader(config));
		JsonObject map = jr.readObject();
		jr.close();
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
				Field neighbourField = pitch.get(currentPos.dirOffset(dir));
				if (neighbourField != null) {
					neighbourField.SetNeighbour(dir.Opposite(), createdField);
					createdField.SetNeighbour(dir, neighbourField);
				}
			}
			pitch.put(currentPos, createdField);
		}

		for (JsonValue prePlaceholder : map.getJsonArray("placeholders")) {
			JsonObject placeholder = (JsonObject) prePlaceholder;
			String type = placeholder.getString("type");
			Placeholder createdPlaceholder = Placeholder.create(type);
			if (createdPlaceholder instanceof Worker)
				workers.add((Worker) createdPlaceholder);
			else if (createdPlaceholder instanceof Box)
				boxes.add((Box) createdPlaceholder);
			Position currentPos = new Position(placeholder.getJsonObject("on"));
			((Tile) pitch.get(currentPos)).InitializeObject(createdPlaceholder);
		}
		for (JsonValue preWire : map.getJsonArray("wires")) {
			JsonObject wire = (JsonObject) preWire;
			LeverTile currentLever = (LeverTile) pitch.get(new Position(wire.getJsonObject("from")));
			for (JsonValue prePosition : wire.getJsonArray("to")) {
				JsonObject position = (JsonObject) prePosition;
				TrapTile currentTrapTile = (TrapTile) pitch.get(new Position(position));
				currentLever.AddTrapdoor(currentTrapTile);
			}
		}
	}
}
