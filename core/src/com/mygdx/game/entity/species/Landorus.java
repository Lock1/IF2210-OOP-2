package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Landorus extends Species {
    public Landorus() {
        super("Landorus", new EarthPower(), Element.GROUND, Element.WATER, "Brown genie");
    }
}
