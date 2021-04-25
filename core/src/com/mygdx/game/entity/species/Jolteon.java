package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Jolteon extends Species {
    public Jolteon() {
        super("Jolteon", new ShockWave(), Element.ELECTRIC, Element.NOELEMENT, "Loves to jolt you");
    }
}
