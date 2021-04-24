package com.mygdx.game.entity;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.attributes.*;
import java.util.ArrayList;

public class Engimon extends Entity {
    private Species type;
    private String engimonName;
    private ArrayList<Skill> learnedSkill;

    private Species parent1Species;
    private Species parent2Species;

    private String parent1Name;
    private String parent2Name;

    private int level;
    private int experience;
    private int cumulativeExperience;
    private boolean isWild;

    public Engimon(Species e, boolean wild) { // TODO : Pos
        type = e;
    }

    public int xpGain(int gainedXP) {
        int totalLevelUp = 0;
        return totalLevelUp;
    }

    // @over valid move

}
