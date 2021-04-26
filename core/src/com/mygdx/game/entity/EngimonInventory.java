package com.mygdx.game.entity;

import com.mygdx.game.entity.Inventory;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.attributes.Skill;

import java.util.ArrayList;

public class EngimonInventory extends Inventory<Engimon> {
    public EngimonInventory(int cap) {
        super(cap);
    }

    public boolean addItem(Engimon newItem) {
        if (itemList.size() < maxCapacity) {
            itemList.add(newItem);
            return true;
        }
        else
            return false;
    }

    public boolean deleteItem(Engimon e) {
        return itemList.remove(e);
    }

    public int getSize() {
        return itemList.size();
    }

    public ArrayList<Engimon> getEngimonByMinLevelConstraint(int minLevel) {
        ArrayList<Engimon> temp = new ArrayList<Engimon>();
        for(Engimon engimon : itemList) {
            if(engimon.level() >= minLevel) {
                temp.add(engimon);
            }
        }
        return temp;
    }
}
