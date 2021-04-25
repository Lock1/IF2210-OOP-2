package com.mygdx.game;

public interface Database<T> {
    // Database interface
    public void addItem(T item);
    public T getItem(String name) throws ItemNotFound;
    public T getRandomizedItem();

    /** Will throw ItemNotFound if no item found on getItem()
    * getRandomizedItem() will return null instead if database is empty
    */
}
