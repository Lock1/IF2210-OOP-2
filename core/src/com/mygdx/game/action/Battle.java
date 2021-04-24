package com.mygdx.game.action;

import com.mygdx.game.entity.Engimon;
import com.mygdx.game.action.EngimonInteraction;

public class Battle extends EngimonInteraction {
    public Battle(Engimon e1, Engimon e2) {
        super(e1, e2);
    }

    // Fire 1 0 1 0.5 2
    // Water 2 1 0 1 1
    // Electric 1 2 1 0 1.5
    // Ground 1.5 1 2 1 0
    // Ice 0 1 0.5 2 1
}
