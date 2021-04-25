package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Mewtwo extends Species {
    public Mewtwo() {
        super("Mewtwo", new FirePunch(), Element.FIRE, Element.NOELEMENT, "Haha");
    }
}
