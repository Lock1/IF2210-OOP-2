package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Bloon extends Species {
    public Bloon() {
        super("Bloon", new LazyBite(), Element.WATER, Element.NOELEMENT, "Not too smart");
    }
}
