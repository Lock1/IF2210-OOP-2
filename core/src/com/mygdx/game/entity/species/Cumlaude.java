package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Cumlaude extends Species {
    public Cumlaude() {
        super("Cumlaude", new DrainEnergy(), Element.ELECTRIC, Element.NOELEMENT, "WOW");
    }
}
