package com.mygdx.game.entity;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.entity.Species;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entity.attributes.Element;

public class Entity {
    private Position pos;
    private Sprite entitySprite;
    private Texture entityTexture;

    private static int tileWidth;
    private static int tileHeight;

    public static void setEntityTileSize(int tileW, int tileH) {
        tileWidth = tileW;
        tileHeight = tileH;
    }

    public Entity(int x, int y) {
        pos = new Position(x, y);
    }

    public Position getPosition() {
        return pos;
    }

    public void setPosition(Position tpos) {
        entitySprite.translate(tileWidth * (pos.x-tpos.x), tileHeight * (pos.y-tpos.y));
        pos = tpos;
        entitySprite.setPosition(tpos.x*tileWidth, tpos.y*tileHeight);
    }

    public void setTexture(Texture text) {
        entityTexture = text;
    }

    public Texture getTexture() {
        return entityTexture;
    }

    public void setSprite(Sprite sp) {
        entitySprite = sp;
    }

    public Sprite getSprite() {
        return entitySprite;
    }

    public boolean isTileMoveable(int x, int y, TiledMapTileLayer tiledMapLayer) { // TODO : Add
        if(this instanceof Engimon) {
            Engimon engimon = (Engimon) this;
            TiledMapTileLayer.Cell targetData = tiledMapLayer.getCell(x, y);
            if (targetData != null) {
                int targetCell = targetData.getTile().getId();
                if(targetCell >= 800 && targetCell <= 1279 && engimon.getSpecies().getElementSet().contains(Element.FIRE)) {
                    return true;
                }
                else if(targetCell >= 1360 && targetCell <= 1732 && engimon.getSpecies().getElementSet().contains(Element.WATER)) {
                    return true;
                }
                else if(targetCell >= 2304 && targetCell <= 3071 && engimon.getSpecies().getElementSet().contains(Element.ICE)) {
                    return true;
                }
                else if(((targetCell >= 80 && targetCell <= 455 ) || (targetCell >= 1800 && targetCell <= 2200)) && (engimon.getSpecies().getElementSet().contains(Element.GROUND)) || engimon.getSpecies().getElementSet().contains(Element.ELECTRIC)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;
    }
}
