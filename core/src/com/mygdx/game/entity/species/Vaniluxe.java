package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Vaniluxe extends Species {
    public Vaniluxe() {
        super("Vaniluxe", new Blizzard(), Element.ICE, Element.NOELEMENT, "Two sheaded ice cream");
    }
}
