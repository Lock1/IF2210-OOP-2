package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Squirtle extends Species {
    public Squirtle() {
        super("Squirtle", new Splash(), Element.WATER, Element.NOELEMENT, "Ordinary tortoise");
    }
}
