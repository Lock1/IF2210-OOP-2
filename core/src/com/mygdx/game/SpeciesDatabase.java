package com.mygdx.game;

import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.ItemNotFound;
import java.util.ArrayList;
import java.util.Random;

public class SpeciesDatabase implements Database<Species> {
    private ArrayList<Species> speciesDB;
    private Random speciesDBRandomizer;


    public SpeciesDatabase() {
        speciesDB = new ArrayList<Species>();
        speciesDBRandomizer = new Random();
    }


    // Interface methods
    public void addItem(Species e) {
        speciesDB.add(e);
    }

    public Species getItem(String name) throws ItemNotFound {
        for (Species sp : speciesDB)
            if (sp.speciesName() == name)
                return new Species(sp);

        throw new ItemNotFound();
    }

    public Species getRandomizedItem() {
        if (speciesDB.size() > 0)
            return new Species(speciesDB.get(speciesDBRandomizer.nextInt(speciesDB.size())));
        else
            return null;
    }

    // Non interface methods
    public Species getSpecificSpeciesWithElement(Element type1, Element type2) throws ItemNotFound {
        for (Species sp : speciesDB)
            if (sp.isElementCompatible(type1) && sp.isElementCompatible(type2))
                return new Species(sp);

        throw new ItemNotFound();
    }

    public Species getSpecificSpeciesWithElement(Element type1) throws ItemNotFound {
        for (Species sp : speciesDB)
            if (sp.isElementCompatible(type1))
                return new Species(sp);

        throw new ItemNotFound();
    }
}
