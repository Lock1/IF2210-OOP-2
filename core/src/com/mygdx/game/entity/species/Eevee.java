package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Eevee extends Species {
    public Eevee() {
        super("Eevee", new DoubleSlap(), Element.WATER, Element.NOELEMENT, "What does the fox say?");
    }
}
