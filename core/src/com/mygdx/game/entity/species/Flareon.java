package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Flareon extends Species {
    public Flareon() {
        super("Flareon", new FlameBurst(), Element.FIRE, Element.NOELEMENT, "Ready to ignite your bone");
    }
}
