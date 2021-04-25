package com.mygdx.game.entity;

import com.mygdx.game.entity.Inventory;
import com.mygdx.game.entity.attributes.Skill;
import java.util.ArrayList;

public class SkillInventory extends Inventory<Skill> {
    public SkillInventory(int cap) {
        super(cap);
    }

    public boolean addItem(Skill newItem) {
        if (itemList.size() < maxCapacity) {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).skillName().equals(newItem.skillName())) {
                    if (itemList.get(i).itemCount() >= 1)
                        itemList.get(i).addItemCount();
                    else
                        itemList.add(newItem);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean deleteItem(Skill e) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).skillName().equals(e.skillName())) {
                if (itemList.get(i).itemCount() > 1)
                    itemList.get(i).reduceItemCount();
                else
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
