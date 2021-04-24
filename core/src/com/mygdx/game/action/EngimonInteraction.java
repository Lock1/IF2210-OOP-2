package com.mygdx.game.action;

import com.mygdx.game.entity.Engimon;

public abstract class EngimonInteraction {
    protected Engimon e1;
    protected Engimon e2;

    protected EngimonInteraction(Engimon e1, Engimon e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}
