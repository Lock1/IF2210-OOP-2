package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Articuno extends Species {
    public Articuno() {
        super("Articuno", new IceBeam(), Element.ICE, Element.WATER, "The flying refrigerator");
    }
}
