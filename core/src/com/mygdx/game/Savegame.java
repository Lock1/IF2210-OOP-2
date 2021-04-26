package com.mygdx.game;

import com.mygdx.game.CSVReader;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.Player;
import com.mygdx.game.entity.attributes.Skill;
import java.util.ArrayList;

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
        CSVReader csvRead = new CSVReader();
        ArrayList<Entity> entityData = new ArrayList<Entity>();


        return entityData;
    }
}
