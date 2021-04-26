package com.mygdx.game.entity;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.Entity;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.EngimonInventory;
import com.mygdx.game.entity.SkillInventory;
import com.mygdx.game.entity.attributes.*;
import java.util.ArrayList;

public class Player extends Entity {
    private EngimonInventory playerEngimons;
    private SkillInventory playerItems;
    private Engimon currentEngimon;
    private int maxTotalInventory;
    // TODO : Last pos




    public Player(int maxInv) {
        playerEngimons = new EngimonInventory(maxInv);
        playerItems = new SkillInventory(maxInv);
        currentEngimon = null;
        maxTotalInventory = maxInv;
    }


    public void changeEngimon(Engimon e) {
        currentEngimon = e;
    }

    public Engimon getCurrentEngimon() {
        return currentEngimon;
    }

    // @over valid move Tile Check TODO
    // TODO : Get last pos

    public boolean addItem(Skill s) {
        if (playerItems.getSize() + playerEngimons.getSize() < maxTotalInventory)
            return playerItems.addItem(s);
        else
            return false;
    }

    public boolean addItem(Engimon eRef) {
        if (playerItems.getSize() + playerEngimons.getSize() < maxTotalInventory)
            return playerEngimons.addItem(eRef);
        else
            return false;
    }

    public boolean deleteItem(Skill s) {
        return playerItems.deleteItem(s);
    }

    public boolean deleteItem(Engimon e) {
        return playerEngimons.deleteItem(e);
    }

    public ArrayList<Skill> getSkillItem() {
        return playerItems.getItemList();
    }

    public ArrayList<Skill> getSkillByElement(Element e) { return playerItems.getListByElement(e); }

    public ArrayList<Engimon> getEngimonItem() {
        return playerEngimons.getItemList();
    }

    public ArrayList<Engimon> getEngimonByMinLevel(int minLevel) { return playerEngimons.getEngimonByMinLevelConstraint(minLevel); }

}
