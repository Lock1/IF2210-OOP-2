package com.mygdx.game.entity;

import java.util.ArrayList;

public abstract class Inventory<T> {
    protected ArrayList<T> itemList;
    protected final int maxCapacity;


    public Inventory(int cap) {
        maxCapacity = cap;
        itemList = new ArrayList<T>();
    }

    public ArrayList<T> getItemList() {
        return new ArrayList<T>(itemList);
    }

    public abstract boolean addItem(T newitem);
    public abstract boolean deleteItem(T targetitem);
}
