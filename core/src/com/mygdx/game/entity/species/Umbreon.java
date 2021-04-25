package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Umbreon extends Species {
    public Umbreon() {
        super("Umbreon", new NightSlash(), Element.GROUND, Element.NOELEMENT, "Not umbrella");
    }
}
