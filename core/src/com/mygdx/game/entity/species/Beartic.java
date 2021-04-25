package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Beartic extends Species {
    public Beartic() {
        super("Beartic", new IceFang(), Element.ICE, Element.NOELEMENT, "Beartic, the bear from antartic");
    }
}
