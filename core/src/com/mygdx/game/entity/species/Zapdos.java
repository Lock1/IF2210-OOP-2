package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Zapdos extends Species {
    public Zapdos() {
        super("Zapdos", new ThunderFang(), Element.ELECTRIC, Element.NOELEMENT, "The flying PLN");
    }
}
