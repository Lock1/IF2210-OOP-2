package com.mygdx.game;

import com.mygdx.game.entity.attributes.skill.*;
import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.ItemNotFound;
import java.util.ArrayList;
import java.util.Random;

public class SkillDatabase implements Database<Skill> {
    private ArrayList<Skill> skillDB;
    private Random skillDBRandomizer;


    public SkillDatabase() {
        skillDB = new ArrayList<Skill>();
        skillDBRandomizer = new Random();
        addItem(new Blizzard());
        addItem(new BraveBird());
        addItem(new BugBite());
        addItem(new Confusion());
        addItem(new Dig());
        addItem(new DoubleSlap());
        addItem(new DrainEnergy());
        addItem(new EarthPower());
        addItem(new Ember());
        addItem(new FellStinger());
        addItem(new FireFang());
        addItem(new FirePunch());
        addItem(new FlameBurst());
        addItem(new HydroCannon());
        addItem(new HydroPump());
        addItem(new IceBeam());
        addItem(new IceFang());
        addItem(new Incinerate());
        addItem(new LazyBite());
        addItem(new Nightmare());
        addItem(new NightSlash());
        addItem(new Sandstorm());
        addItem(new ShockWave());
        addItem(new SkyDrop());
        addItem(new Smackdown());
        addItem(new Spark());
        addItem(new Splash());
        addItem(new StudyHard());
        addItem(new Surf());
        addItem(new Synchronoise());
        addItem(new Thunderbolt());
        addItem(new ThunderFang());
    }


    // Interface methods
    public void addItem(Skill e) {
        skillDB.add(e);
    }

    public Skill getItem(String name) throws ItemNotFound {
        for (Skill sp : skillDB)
            if (sp.skillName().equals(name))
                return new Skill(sp);

        throw new ItemNotFound();
    }

    public Skill getRandomizedItem() {
        if (skillDB.size() > 0)
            return new Skill(skillDB.get(skillDBRandomizer.nextInt(skillDB.size())));
        else
            return null;
    }
}
