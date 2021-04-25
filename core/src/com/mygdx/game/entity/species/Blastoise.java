package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Blastoise extends Species {
    public Blastoise() {
        super("Blastoise", new HydroPump(), Element.WATER, Element.NOELEMENT, "Giant tortoise");
    }
}
