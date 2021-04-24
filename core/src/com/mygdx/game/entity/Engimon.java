package com.mygdx.game.entity;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.attributes.*;
import java.util.ArrayList;

public class Engimon extends Entity {
    private Species speciesType;
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
        speciesType = e;
    }

    public Species getSpecies() {
        return speciesType;
    }

    public int xpGain(int gainedXP) {
        int totalLevelUp = 0;
        return totalLevelUp;
    }

    public int level() {
        return level;
    }

    public ArrayList<Skill> getSkillArray() {
        return learnedSkill;
    }

    // @over valid move

}
