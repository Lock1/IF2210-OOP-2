package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Tyranitar extends Species {
    public Tyranitar() {
        super("Tyranitar", new Smackdown(), Element.GROUND, Element.NOELEMENT, "Not a tyranosaurus");
    }
}
