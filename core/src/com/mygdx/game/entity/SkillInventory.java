package com.mygdx.game.entity;

import com.mygdx.game.entity.Inventory;
import com.mygdx.game.entity.attributes.Skill;
import java.util.ArrayList;

public class SkillInventory extends Inventory<Skill> {
    // TODO : Completion
    public SkillInventory(int cap) {
        super(cap);
    }

    public boolean addItem(Skill newItem) {
        if (itemList.size() < maxCapacity) {
            itemList.add(newItem);
            return true;
        }
        else
            return false;
    }

    public boolean deleteItem(Skill e) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) == e) {
                itemList.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return itemList.size();
    }
}
