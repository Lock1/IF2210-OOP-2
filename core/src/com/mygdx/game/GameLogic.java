package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.entity.*;

import com.mygdx.game.entity.engimon.*;
import java.util.ArrayList;
import com.mygdx.game.maps.OrthogonalTiledMapRendererWithSprites;
import java.util.Random;
import com.mygdx.game.*;

public class GameLogic {
    private Player currentPlayer;
    private ArrayList<Entity> entityContainer;
    private OrthogonalTiledMapRendererWithSprites rendererReference;
    private Random logicRandom;
    private SpeciesDatabase speciesDB;
    private SkillDatabase skillDB;

    public GameLogic(Player playerRef, OrthogonalTiledMapRendererWithSprites renderer) {
        speciesDB = new SpeciesDatabase();
        skillDB = new SkillDatabase();

        logicRandom = new Random();
        currentPlayer = playerRef;
        entityContainer = new ArrayList<Entity>();
        entityContainer.add(playerRef);
        rendererReference = renderer;
    }

    public ArrayList<Entity> getEntities() {
        return entityContainer;
    }

    public void playerInput(String inputString) {
        System.out.println(inputString);
        switch (inputString) {
            case "Up":
                currentPlayer.setPosition(new Position(currentPlayer.getPosition().x, currentPlayer.getPosition().y + 1));
                break;
            case "Down":
                currentPlayer.setPosition(new Position(currentPlayer.getPosition().x, currentPlayer.getPosition().y - 1));
                break;
            case "Left":
                currentPlayer.setPosition(new Position(currentPlayer.getPosition().x - 1, currentPlayer.getPosition().y));
                break;
            case "Right":
                currentPlayer.setPosition(new Position(currentPlayer.getPosition().x + 1, currentPlayer.getPosition().y));
                break;
        }
        tickUpdate();
    }

    public void tickUpdate() {
        if (logicRandom.nextInt() % 100 < 3 && entityContainer.size() < 30) {
            // TODO : add to renderer
            Engimon spawnedEngimon = generateEngimon();
            System.out.println(spawnedEngimon.engimonName());
            entityContainer.add(spawnedEngimon);
        }
    }

    public Engimon generateEngimon() {
        // TODO : Set sprite
        return new Engimon(speciesDB.getRandomizedItem(), true, logicRandom.nextInt() % 40, logicRandom.nextInt() % 40);
    }
}
