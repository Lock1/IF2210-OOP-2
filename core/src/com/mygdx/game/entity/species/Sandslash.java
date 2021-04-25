package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Sandslash extends Species {
    public Sandslash() {
        super("Sandslash", new Dig(), Element.GROUND, Element.NOELEMENT, "The mouse engimon");
    }
}
