package com.mygdx.game;

import com.mygdx.game.entity.engimon.*;
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
        addItem(new Articuno());
        addItem(new Beartic());
        addItem(new Beedrill());
        addItem(new Blastoise());
        addItem(new Bloon());
        addItem(new Charmander());
        addItem(new Cumlaude());
        addItem(new Eevee());
        addItem(new Espeon());
        addItem(new Flareon());
        addItem(new HeatRotom());
        addItem(new HoOh());
        addItem(new Jolteon());
        addItem(new Kumon());
        addItem(new Landorus());
        addItem(new Lugia());
        addItem(new Luxio());
        addItem(new Mewtwo());
        addItem(new Moltres());
        addItem(new Pinsir());
        addItem(new Sableye());
        addItem(new Sandslash());
        addItem(new Spheal());
        addItem(new Squirtle());
        addItem(new Thundurus());
        addItem(new Tornadus());
        addItem(new Tyranitar());
        addItem(new Umbreon());
        addItem(new Vaniluxe());
        addItem(new Vaporeon());
        addItem(new Wooper());
        addItem(new Zapdos());
    }


    // Interface methods
    public void addItem(Species e) {
        speciesDB.add(e);
    }

    public Species getItem(String name) throws ItemNotFound {
        for (Species sp : speciesDB)
            if (sp.speciesName().equals(name))
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
