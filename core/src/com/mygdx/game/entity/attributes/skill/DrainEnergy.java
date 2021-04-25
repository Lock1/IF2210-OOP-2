package com.mygdx.game.entity.attributes.skill;

import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.entity.attributes.Element;

public class DrainEnergy extends Skill {
    public DrainEnergy() {
        super("Drain Energy", 40, Element.ELECTRIC, 1);
    }
}
