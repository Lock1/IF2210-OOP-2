package com.mygdx.game;

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
