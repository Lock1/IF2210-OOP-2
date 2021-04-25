package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Tornadus extends Species {
    public Tornadus() {
        super("Tornadus", new SkyDrop(), Element.WATER, Element.NOELEMENT, "Green genie");
    }
}
