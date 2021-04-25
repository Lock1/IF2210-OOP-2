package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Lugia extends Species {
    public Lugia() {
        super("Lugia", new Synchronoise(), Element.FIRE, Element.ELECTRIC, "AAaaaarggh");
    }
}
