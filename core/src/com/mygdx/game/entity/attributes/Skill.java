package com.mygdx.game.entity.attributes;

import com.mygdx.game.entity.attributes.Element;

public class Skill {
    private String skillName;
    private int basePower;
    private Element skillElement;
    private int masteryLevel;
    private int itemCount;

    public Skill(String name, int power, Element e, int level) {
        skillName = name;
        basePower = power;
        skillElement = e;
        masteryLevel = level;
        itemCount = 1;
    }

    public Skill(Skill skillref) {
        skillName    = skillref.skillName;
        basePower    = skillref.basePower;
        skillElement = skillref.skillElement;
        masteryLevel = skillref.masteryLevel;
        itemCount = 1;
    }





    public String skillName() {
        return skillName;
    }

    public int basePower() {
        return basePower;
    }

    public Element skillElement() {
        return skillElement;
    }

    public int masteryLevel() {
        return masteryLevel;
    }

    public int itemCount() {
        return itemCount;
    }

    public void addItemCount() {
        itemCount++;
    }

    public void reduceItemCount() {
        itemCount--;
    }

    public boolean levelUpMastery() {
        if (masteryLevel < 3) {
            masteryLevel++;
            return true;
        }
        else
            return false;
    }
}
