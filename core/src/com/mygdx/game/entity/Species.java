package com.mygdx.game.entity;

import java.util.*;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.Skill;

public class Species {
    private String speciesName;
    private Skill baseSkill;
    private Set<Element> speciesType;
    private String interactionDescription;

    public Species(String name, Skill skill, Element e1, Element e2, String interact) {
        speciesName = name;
        baseSkill = skill;
        speciesType = new HashSet<Element>();
        speciesType.add(e1);
        if (e2 != Element.NOELEMENT)
            speciesType.add(e2);
        interactionDescription = interact;
    }

    public String speciesName() {
        return speciesName;
    }

    public Skill baseSkill() {
        return baseSkill;
    }

    public Set<Element> getElementSet() {
        return speciesType;
    }

    public boolean isElementCompatible(Element e) {
        return speciesType.contains(e);
    }

    public String interact() {
        return interactionDescription;
    }
}
