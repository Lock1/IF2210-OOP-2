package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Charmander extends Species {
    public Charmander() {
        super("Charmander", new Incinerate(), Element.FIRE, Element.NOELEMENT, "Mini dragon");
    }
}
