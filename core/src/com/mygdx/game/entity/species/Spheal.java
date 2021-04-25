package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Spheal extends Species {
    public Spheal() {
        super("Spheal", new Surf(), Element.WATER, Element.ICE, "Cute seal");
    }
}
