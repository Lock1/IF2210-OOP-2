package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Espeon extends Species {
    public Espeon() {
        super("Espeon", new Confusion(), Element.FIRE, Element.NOELEMENT, "Pinky fox");
    }
}
