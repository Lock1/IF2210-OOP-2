package com.mygdx.game.entity;

import com.mygdx.game.entity.Inventory;
import com.mygdx.game.entity.Engimon;
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
