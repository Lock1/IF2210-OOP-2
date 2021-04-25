package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Moltres extends Species {
    public Moltres() {
        super("Moltres", new BraveBird(), Element.FIRE, Element.NOELEMENT, "The bonfire");
    }
}
