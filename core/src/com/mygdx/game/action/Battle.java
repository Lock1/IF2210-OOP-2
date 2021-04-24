package com.mygdx.game.action;

import com.mygdx.game.action.EngimonInteraction;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.attributes.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

public class Battle extends EngimonInteraction {
    private double power1;
    private double power2;

    public Battle(Engimon e1, Engimon e2) {
        super(e1, e2);
    }

    public void calculatePower() {
        double sumE1 = 0;
        double sumE2 = 0;

        ArrayList<Skill> skillE1 = e1.getSkillArray();
        ArrayList<Skill> skillE2 = e2.getSkillArray();

        Set<Element> elementE1 = e1.getSpecies().getElementSet();
        Set<Element> elementE2 = e2.getSpecies().getElementSet();

        for (Skill s1 : skillE1)
            sumE1 += s1.basePower() * s1.masteryLevel();

        for (Skill s2 : skillE2)
            sumE2 += s2.basePower() * s2.masteryLevel();

        double advantage1, advantage2;

        Iterator iterE1 = elementE1.iterator();
        Iterator iterE2 = elementE2.iterator();

        Element firstElementE1 = (Element) iterE1.next();
        Element firstElementE2 = (Element) iterE2.next();
        if (elementE1.size() == 1 && elementE2.size() == 1) {
            advantage1 = ElementAdvantage.getAdvantage(firstElementE1, firstElementE2);
            advantage2 = ElementAdvantage.getAdvantage(firstElementE2, firstElementE1);
        }
        else if (elementE1.size() == 1 && elementE2.size() == 2) {
            Element secondElementE2 = (Element) iterE2.next();

            advantage1 = getMax(ElementAdvantage.getAdvantage(firstElementE1, firstElementE2),
                            ElementAdvantage.getAdvantage(firstElementE1, secondElementE2));

            advantage2 = getMax(ElementAdvantage.getAdvantage(firstElementE2, firstElementE1),
                            ElementAdvantage.getAdvantage(secondElementE2, firstElementE1));
        }
        else if (elementE1.size() == 2 && elementE2.size() == 1) {
            Element secondElementE1 = (Element) iterE1.next();

            advantage1 = getMax(ElementAdvantage.getAdvantage(firstElementE1, firstElementE2),
                            ElementAdvantage.getAdvantage(secondElementE1, firstElementE2));

            advantage2 = getMax(ElementAdvantage.getAdvantage(firstElementE2, firstElementE1),
                            ElementAdvantage.getAdvantage(firstElementE2, secondElementE1));
        }
        else {
            Element secondElementE1 = (Element) iterE1.next();
            Element secondElementE2 = (Element) iterE2.next();

            advantage1 = getMax(ElementAdvantage.getAdvantage(firstElementE1, firstElementE2),
                            ElementAdvantage.getAdvantage(secondElementE1, firstElementE2));

            advantage2 = getMax(ElementAdvantage.getAdvantage(firstElementE2, firstElementE1),
                            ElementAdvantage.getAdvantage(secondElementE2, firstElementE1));


            advantage1 = getMax(advantage1, ElementAdvantage.getAdvantage(firstElementE1, secondElementE2));
            advantage1 = getMax(advantage1, ElementAdvantage.getAdvantage(secondElementE1, secondElementE2));

            advantage2 = getMax(advantage2, ElementAdvantage.getAdvantage(firstElementE2, secondElementE1));
            advantage2 = getMax(advantage2, ElementAdvantage.getAdvantage(secondElementE2, secondElementE1));
        }

        power1 = sumE1 + e1.level() * advantage1;
        power2 = sumE2 + e2.level() * advantage2;
    }

    public int getWinner() {
        if (power1 >= power2)
            return 1;
        else
            return 2;
    }

    public double getCalculatedPower1() {
        return power1;
    }

    public double getCalculatedPower2() {
        return power2;
    }

    private double getMax(double a, double b) {
        return (a > b) ? a : b;
    }
}
