package com.mygdx.game;

import com.mygdx.game.CSVReader;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.attributes.Skill;
import com.badlogic.gdx.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Savegame {
    public static void saveGame(ArrayList<Entity> eCtr) {
        CSVReader csvWriter = new CSVReader();
        ArrayList<String[]> wildEngimonData = new ArrayList<String[]>();
        ArrayList<String[]> itemEngimon = new ArrayList<String[]>();
        ArrayList<String[]> itemSkill = new ArrayList<String[]>();
        ArrayList<String[]> playerData = new ArrayList<String[]>();
        for (Entity e : eCtr) {
            if (e instanceof Player) {
                Player targetPlayer = (Player) e;
                String[] playerPosition = new String[2];
                playerPosition[0] = Integer.toString(targetPlayer.getPosition().x);
                playerPosition[1] = Integer.toString(targetPlayer.getPosition().y);
                playerData.add(playerPosition);

                ArrayList<Skill> playerSkill = targetPlayer.getSkillItem();
                for (Skill s : playerSkill) {
                    String[] rowEntry = { s.skillName(), Integer.toString(s.itemCount()) };
                    itemSkill.add(rowEntry);
                }

                ArrayList<Engimon> playerEngimon = targetPlayer.getEngimonItem();
                for (Engimon eng : playerEngimon)
                     itemEngimon.add(convertEngimonToString(eng));
            }
            else if (e instanceof Engimon)
                wildEngimonData.add(convertEngimonToString((Engimon) e));
        }
        csvWriter.write(wildEngimonData, "wildengimon.csv");
        csvWriter.write(playerData, "playerdata.csv");
        csvWriter.write(itemEngimon, "playerengimon.csv");
        csvWriter.write(itemSkill, "playerskill.csv");
        // Player engimon inventory -> data, etc etc
        // Player item -> count and type
    }

    private static String[] convertEngimonToString(Engimon targetEngimon) {
        String[] stringData = new String[18];

        stringData[0] = targetEngimon.getSpecies().speciesName();
        stringData[1] = targetEngimon.engimonName();
        stringData[2] = Integer.toString(targetEngimon.level());
        stringData[3] = Integer.toString(targetEngimon.experience());
        stringData[4] = Integer.toString(targetEngimon.getPosition().x);
        stringData[5] = Integer.toString(targetEngimon.getPosition().y);

        if (targetEngimon.getParentName() != null) {
            stringData[6] = targetEngimon.getParentName()[0];
            stringData[7] = targetEngimon.getParentName()[1];
            stringData[8] = targetEngimon.getParentSpecies()[0].speciesName();
            stringData[9] = targetEngimon.getParentSpecies()[1].speciesName();
        }
        else {
            for (int i = 0; i < 4; i++)
                stringData[6+i] = "null";
        }

        ArrayList<Skill> engimonSkill = targetEngimon.getSkillArray();
        for (int i = 0; i < engimonSkill.size(); i++) {
            stringData[10+2*i] = engimonSkill.get(i).skillName();
            stringData[11+2*i] = Integer.toString(engimonSkill.get(i).masteryLevel());
        }

        return stringData;
    }

    public static ArrayList<Entity> loadGame() {
        CSVReader csvRead = new CSVReader("wildengimon.csv" , ",");
        ArrayList<Entity> entityData = new ArrayList<Entity>();
        SpeciesDatabase speciesDB = new SpeciesDatabase();
        SkillDatabase skillDB = new SkillDatabase();


        List<String[]> wildEngimonStrings = null;
        List<String[]> playerEngimonStrings = null;
        List<String[]> playerLocationStrings = null;
        List<String[]> playerItemStrings = null;
        try {
            wildEngimonStrings = csvRead.read();

            csvRead.setNewTargetRead("playerengimon.csv");
            playerEngimonStrings = csvRead.read();

            csvRead.setNewTargetRead("playerdata.csv");
            playerLocationStrings = csvRead.read();

            csvRead.setNewTargetRead("playerskill.csv");
            playerItemStrings = csvRead.read();



            TmxMapLoader loader = new TmxMapLoader();
            TiledMap map = loader.load("Map.tmx");
            int tileWidth = map.getProperties().get("tilewidth", Integer.class);
            int tileHeight = map.getProperties().get("tileheight", Integer.class);

            Player newPlayer = new Player(30, Integer.parseInt(playerLocationStrings.get(0)[0]), Integer.parseInt(playerLocationStrings.get(0)[1]));
            newPlayer.setEntityTileSize(tileWidth, tileHeight);
            newPlayer.setTexture(new Texture(Gdx.files.internal("./sprites/player/idle_right.png")));
            newPlayer.setSprite(new Sprite(newPlayer.getTexture()));




            for (String[] strs : wildEngimonStrings) {
                Species targetSpecies = null;
                try {
                    targetSpecies = speciesDB.getItem(strs[0]);
                }
                catch (ItemNotFound e) {

                }
                Engimon loadedWild = new Engimon(targetSpecies, true, Integer.parseInt(strs[2]),
                        Integer.parseInt(strs[4]), Integer.parseInt(strs[5]));

                loadedWild.deleteSkill(targetSpecies.baseSkill().skillName());
                for (int i = 0; i < 4; i++) {
                    if (!strs[10+2*i].equals("null")) {
                        try {
                            Skill newSkill = skillDB.getItem(strs[10+2*i]);
                            while (newSkill.masteryLevel() < Integer.parseInt(strs[11+2*i]))
                            newSkill.levelUpMastery();
                            loadedWild.addSkill(newSkill);
                        }
                        catch (ItemNotFound e) {

                        }
                    }
                }


                loadedWild.autoSetSprite();
                entityData.add(loadedWild);
            }

            for (String[] strs : playerItemStrings) {
                Skill targetSkill = null;
                try {
                    targetSkill = skillDB.getItem(strs[0]);
                }
                catch (ItemNotFound e) {

                }

                while (targetSkill.itemCount() < Integer.parseInt(strs[1]))
                    targetSkill.addItemCount();

                newPlayer.addItem(targetSkill);
            }

            for (String[] strs : playerEngimonStrings) {
                Species targetSpecies = null;
                try {
                    targetSpecies = speciesDB.getItem(strs[0]);
                }
                catch (ItemNotFound e) {

                }
                Engimon loadedEngimon = new Engimon(targetSpecies, false, Integer.parseInt(strs[2]),
                        Integer.parseInt(strs[4]), Integer.parseInt(strs[5]));

                loadedEngimon.deleteSkill(targetSpecies.baseSkill().skillName());
                for (int i = 0; i < 4; i++) {
                    if (!strs[10+2*i].equals("null")) {
                        try {
                            Skill newSkill = skillDB.getItem(strs[10+2*i]);
                            while (newSkill.masteryLevel() < Integer.parseInt(strs[11+2*i]))
                            newSkill.levelUpMastery();
                            loadedEngimon.addSkill(newSkill);
                        }
                        catch (ItemNotFound e) {

                        }
                    }
                }


                loadedEngimon.autoSetSprite();
                newPlayer.addItem(loadedEngimon);
            }
            newPlayer.changeEngimon(newPlayer.getEngimonItem().get(0));



            entityData.add(newPlayer);

            return entityData;
        }
        catch (IOException e) {
            // Failed load
            return null;
        }


    }
}
