package com.mygdx.game.action;

import com.badlogic.gdx.Input;

public class TextInput implements Input.TextInputListener {
    public static String rename = "";
    @Override
    public void input (String newName) {
        rename = newName;
    }

    @Override
    public void canceled () {
    }
}
