package com.mygdx.game.entity;

import com.mygdx.game.entity.Inventory;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.Skill;
import java.util.ArrayList;

public class SkillInventory extends Inventory<Skill> {
    public SkillInventory(int cap) {
        super(cap);
    }

    public boolean addItem(Skill newItem) {
        boolean addFlag = true;
        if (itemList.size() < maxCapacity) {
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).skillName().equals(newItem.skillName())) {
                    if (itemList.get(i).itemCount() >= 1)
                        itemList.get(i).addItemCount();
                        addFlag = false;
                    return true;
                }
            }
            if(addFlag) {
                itemList.add(newItem);
                return true;
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

    public ArrayList<Skill> getListByElement(Element e) {
        ArrayList<Skill> temp = new ArrayList<Skill>();
        ArrayList<Skill> allSkills = new ArrayList<Skill>(itemList);
        for(Skill skill : itemList) {
            if(skill.skillElement().equals(e)) {
                temp.add(skill);
            }
        }
        return temp;
    }
}
