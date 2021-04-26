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
    private int lifeCount;

    public static final int maximumCumulativeXP = 10000;

    public Engimon(Species e, boolean wild) { // TODO : Pos
        speciesType = e;
        this.cumulativeExperience = 0;
        this.experience = 0;
        this.level = 1;
        learnedSkill = new ArrayList<Skill>();
        learnedSkill.add(new Skill(e.baseSkill()));
        parent1Species = null;
        parent2Species = null;
        engimonName = e.speciesName();

        if (wild)
            lifeCount = 1;
        else
            lifeCount = 3;
    }

    public Engimon(Species e, boolean wild, int baselevel) { // TODO : Pos
        speciesType = e;
        this.cumulativeExperience = 0;
        this.experience = 0;
        this.level = baselevel;
        learnedSkill = new ArrayList<Skill>();
        learnedSkill.add(new Skill(e.baseSkill()));
        parent1Species = null;
        parent2Species = null;
        engimonName = e.speciesName();

        if (wild)
            lifeCount = 1;
        else
            lifeCount = 3;
    }





    // ------- Getter -------
    public Species getSpecies() {
        return speciesType;
    }

    public int level() {
        return level;
    }

    public String engimonName() {
        return engimonName;
    }

    public int getExperience() { return experience; }

    public ArrayList<Skill> getSkillArray() {
        return new ArrayList<Skill>(learnedSkill);
    }

    public String engimonInteract() {
        if (!isWild)
            return speciesType.interact();
        else
            return "";
    }

    public boolean wild() {
        return isWild;
    }

    public String[] getParentName() {
        if (parent1Species != null) {
            String[] parentsName = { parent1Name, parent2Name };
            return parentsName;
        }
        else
            return null;
    }

    public Species[] getParentSpecies() {
        if (parent1Species != null) {
            Species[] parentsSpecies = { parent1Species, parent2Species };
            return parentsSpecies;
        }
        else
            return null;
    }

    public int lifeCount() {
        return lifeCount;
    }




    // ------- Methods -------
    public int xpGain(int gainedXP) {
        int totalLevelUp = (experience + gainedXP) / 100;
        this.level += totalLevelUp;
        this.cumulativeExperience += gainedXP;
        this.experience = (experience + gainedXP) % 100;
        return totalLevelUp;
    }

    public void breedingLevelDown() {
        level -= 3;
    }

    public void addParent(Species e, String name) {
        if (parent1Species == null) {
            parent1Species = e;
            parent1Name = name;
        }
        else {
            parent2Species = e;
            parent2Name = name;
        }
    }

    public void tameEngimon() {
        lifeCount = 3;
        isWild = false;
    }

    public boolean deleteSkill(String skillName) {
        int skillIndex = -1;
        for (int i = 0; i < learnedSkill.size(); i++)
            if (learnedSkill.get(i).skillName().equals(skillName))
                skillIndex = i;

        if (skillIndex != -1) {
            learnedSkill.remove(skillIndex);
            return true;
        }
        else
            return false;
    }

    public boolean addSkill(Skill newSkill) {
        int skillIndex = -1;
        for (int i = 0; i < learnedSkill.size(); i++)
            if (learnedSkill.get(i).skillName().equals(newSkill.skillName()))
                skillIndex = i;

        if (skillIndex != -1)
            return false;
        else {
            learnedSkill.add(newSkill);
            return true;
        }
    }

    public void renameEngimon(String newname) {
        engimonName = newname;
    }

    public boolean isWildEngimon() {
        return isWild;
    }

    public void reduceLife() {
        lifeCount--;
    }

    public boolean isOverLeveled() {
        return Engimon.maximumCumulativeXP < cumulativeExperience;
    }

    // @over valid move Tile Check TODO


}
