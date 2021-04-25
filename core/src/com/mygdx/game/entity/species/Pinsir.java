package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Pinsir extends Species {
    public Pinsir() {
        super("Pinsir", new BugBite(), Element.GROUND, Element.NOELEMENT, "Ruin your code");
    }
}
