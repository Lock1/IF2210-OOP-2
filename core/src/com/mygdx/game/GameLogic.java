package com.mygdx.game;

import com.mygdx.game.entity.*;
import com.mygdx.game.entity.engimon.*;
import com.mygdx.game.entity.attributes.*;
import com.mygdx.game.entity.Species;
import com.mygdx.game.*;
import com.mygdx.game.maps.OrthogonalTiledMapRendererWithSprites;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;
import java.util.Iterator;
import java.lang.Math;

public class GameLogic {
    private Player currentPlayer;
    private ArrayList<Entity> entityContainer;
    private Entity[][] entityMap;
    private OrthogonalTiledMapRendererWithSprites rendererReference;
    private Random logicRandom;
    private SpeciesDatabase speciesDB;
    private SkillDatabase skillDB;
    private TiledMap tiledMapReference;
    private TiledMapTileLayer tiledMapLayer;

    public GameLogic(Player playerRef, OrthogonalTiledMapRendererWithSprites renderer, TiledMap map) {
        speciesDB = new SpeciesDatabase();
        skillDB = new SkillDatabase();

        entityMap = new Entity[50][50];
        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 48; j++)
                entityMap[i][j] = null;
        }

        logicRandom = new Random();
        currentPlayer = playerRef;
        tiledMapReference = map;
        tiledMapLayer = (TiledMapTileLayer) map.getLayers().get(0);
        entityContainer = new ArrayList<Entity>();
        entityContainer.add(playerRef);
        int currentX = currentPlayer.getPosition().x;
        int currentY = currentPlayer.getPosition().y;
        entityMap[currentX][currentY] = currentPlayer;
        rendererReference = renderer;
    }

    public ArrayList<Entity> getEntities() {
        return entityContainer;
    }

    public Entity playerInput(String inputString) {
        Entity collidedEntity = entityMove(currentPlayer, inputString);
        tickUpdate();
        TiledMapTileLayer.Cell targetData = tiledMapLayer.getCell(currentPlayer.getPosition().x, currentPlayer.getPosition().y);
        if (targetData != null) {
            // TODO : Grouping by ID
            int targetCell = targetData.getTile().getId();
            System.out.println(targetCell);
        }
        else {
            System.out.println("in");
        }
        return collidedEntity;
    }

    private boolean isWithinMap(int x, int y) {
        return 0 <= x && x < 48 && 0 <= y && y < 48;
    }

    private Entity entityMove(Entity target, String moveString) {
        for (int i = 0; i < 48; i++) {
            for (int j = 0; j < 48; j++) {
                if (entityMap[i][j] == target) {
                    entityMap[i][j] = null;
                    switch (moveString) {
                        case "Up": // TODO : Add
                            if (target.isTileMoveable(i, j+1, tiledMapLayer) && isWithinMap(i, j+1)) {
                                if (entityMap[i][j+1] == null)
                                    target.setPosition(new Position(i, j + 1));
                                else {
                                    entityMap[i][j] = target;
                                    return entityMap[i][j+1];
                                }
                            }
                            break;
                        case "Down":
                            if (target.isTileMoveable(i, j-1, tiledMapLayer) && isWithinMap(i, j-1)) {
                                if (entityMap[i][j-1] == null)
                                    target.setPosition(new Position(i, j - 1));
                                else {
                                    entityMap[i][j] = target;
                                    return entityMap[i][j-1];
                                }
                            }
                            break;
                        case "Left":
                            if (target.isTileMoveable(i-1, j, tiledMapLayer) && isWithinMap(i-1, j)) {
                                if (entityMap[i-1][j] == null)
                                    target.setPosition(new Position(i - 1, j));
                                else {
                                    entityMap[i][j] = target;
                                    return entityMap[i-1][j];
                                }
                            }
                            break;
                        case "Right":
                            if (target.isTileMoveable(i+1, j, tiledMapLayer) && isWithinMap(i+1, j)) {
                                if (entityMap[i+1][j] == null)
                                    target.setPosition(new Position(i + 1, j));
                                else {
                                    entityMap[i][j] = target;
                                    return entityMap[i+1][j];
                                }
                            }
                            break;
                    }
                    entityMap[target.getPosition().x][target.getPosition().y] = target;
                    return null;
                }
            }
        }
        return null;
    }

    public void tickUpdate() {
        int generatedNumber = logicRandom.nextInt() % 100;
        if (0 <= generatedNumber && generatedNumber < 40 && entityContainer.size() < 30) {
            // TODO : Tile checking, collision
            Engimon spawnedEngimon = generateEngimon();
            rendererReference.addSprite(spawnedEngimon.getSprite());
            entityContainer.add(spawnedEngimon);
        }

        for (Entity ent : entityContainer) {
            if (ent instanceof Engimon) {
                int moveRNG = logicRandom.nextInt() % 100;
                if (0 <= moveRNG && moveRNG < 20) {
                    String moveStr = "Up";
                    switch (moveRNG % 4) {
                        case 0:
                            moveStr = "Up";
                            break;
                        case 1:
                            moveStr = "Down";
                            break;
                        case 2:
                            moveStr = "Left";
                            break;
                        case 3:
                            moveStr = "Right";
                            break;
                    }
                    entityMove(ent, moveStr);
                }
                else if (20 <= moveRNG && moveRNG < 40) {
                    ((Engimon) ent).xpGain(10);
                    if (((Engimon) ent).isOverLeveled()) {
                        for (int i = 0; i < entityContainer.size(); i++)
                            if (ent == entityContainer.get(i)) {
                                entityContainer.remove(i);
                                break;
                            }
                        entityMap[ent.getPosition().x][ent.getPosition().y] = null;
                    }
                }
            }
        }
    }

    public Engimon generateEngimon() {
        int posX = logicRandom.nextInt() % 40;
        int posY = logicRandom.nextInt() % 40;
        Engimon spawnedEngimon = new Engimon(speciesDB.getRandomizedItem(), true, Math.abs(posX), Math.abs(posY));
        entityMap[spawnedEngimon.getPosition().x][spawnedEngimon.getPosition().y] = spawnedEngimon;
        Texture engimonTexture = null;
        Sprite engimonSprite;
        Element engimonFirstElement = spawnedEngimon.getSpecies().getElementSet().iterator().next();
        switch (engimonFirstElement) {
            case ELECTRIC:
                engimonTexture = new Texture(Gdx.files.internal("./sprites/electric/left/move/1.png"));
                break;
            case WATER:
                engimonTexture = new Texture(Gdx.files.internal("./sprites/water/left/move/1.png"));
                break;
            case ICE:
                engimonTexture = new Texture(Gdx.files.internal("./sprites/ice/32bit-cuttlefish1.png"));
                break;
            case FIRE:
                engimonTexture = new Texture(Gdx.files.internal("./sprites/fire/left/move/1.png"));
                break;
            case GROUND:
                engimonTexture = new Texture(Gdx.files.internal("./sprites/ground/left/move/1.png"));
                break;
        }
        engimonSprite = new Sprite(engimonTexture);
        spawnedEngimon.setSprite(engimonSprite);
        assert engimonSprite != null;
        return spawnedEngimon;
    }
}
