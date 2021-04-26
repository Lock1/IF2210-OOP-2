package com.mygdx.game.entity;

import com.mygdx.game.entity.Species;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;

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

    public boolean isTileMoveable() { // TODO : Add
        return true;
    }
}
