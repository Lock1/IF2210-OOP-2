package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Luxio extends Species {
    public Luxio() {
        super("Luxio", new Thunderbolt(), Element.ELECTRIC, Element.NOELEMENT, "The brave cat");
    }

}
