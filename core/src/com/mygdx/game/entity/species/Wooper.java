package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Wooper extends Species {
    public Wooper() {
        super("Wooper", new Sandstorm(), Element.WATER, Element.GROUND, "Where's my hand?");
    }
}
