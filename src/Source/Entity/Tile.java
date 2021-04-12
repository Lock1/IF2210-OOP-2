package Entity;

public class Tile {
    // Enum TileType declaration
    public enum TileType {
        GRASS,
        WATER,
        WALL
    }

    // Attributes
    private Entity currentEntity;
    private TileType tileType;

    // Constructor
    public Tile(TileType type) {
        currentEntity = null;
        tileType = type;
    }

    // Method
    public TileType getTileType() {
        return tileType;
    }

    public void setEntity(Entity newEntity) {
        currentEntity = newEntity;
    }

    public Entity getEntity() {
        return currentEntity;
    }
}
