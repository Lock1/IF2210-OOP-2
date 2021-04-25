package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Beedrill extends Species {
    public Beedrill() {
        super("Beedrill", new FellStinger(), Element.GROUND, Element.FIRE, "Poisonous  Ground");
    }
}
