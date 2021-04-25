package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Vaporeon extends Species {
    public Vaporeon() {
        super("Vaporeon", new HydroCannon(), Element.WATER, Element.NOELEMENT, "The fox goes brr");
    }
}
