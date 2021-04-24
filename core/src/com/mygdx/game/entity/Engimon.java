package com.mygdx.game.entity;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.Entity;

public class Engimon extends Entity {
    public Species type;// TODO : Private

    public Engimon(Species e) {
        type = e;
    }
}
