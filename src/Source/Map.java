package Source;

import Entity.*;
import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Tile>> mapMatrix;

    public Map() {
        mapMatrix = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < 30; i++) {
            ArrayList<Tile> mapRow = new ArrayList<Tile>();
            for (int j = 0; j < 30; j++) {
                // DEBUG
                if (i == 0)
                    mapRow.add(new Tile(Tile.TileType.WALL));
                else if (j > 20)
                    mapRow.add(new Tile(Tile.TileType.WATER));
                else
                    mapRow.add(new Tile(Tile.TileType.GRASS));
            }
            mapMatrix.add(mapRow);
        }
    }

    public Map(int x, int y) {

    }

    public ArrayList<ArrayList<Tile>> getCurrentMap() {
        return mapMatrix;
    }

    // TODO : Add file loading
    // public Map(string filename) {
    //
    // }
}
