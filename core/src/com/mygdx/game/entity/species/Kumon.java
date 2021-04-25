package com.mygdx.game.entity.engimon;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.skill.*;

public class Kumon extends Species {
    public Kumon() {
        super("Kumon", new StudyHard(), Element.WATER, Element.ICE, "Kidsalways hate them");
    }
}
