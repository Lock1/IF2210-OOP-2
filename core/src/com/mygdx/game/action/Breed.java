package com.mygdx.game.action;

import com.mygdx.game.SpeciesDatabase;
import com.mygdx.game.ItemNotFound;
import com.mygdx.game.entity.Engimon;
import com.mygdx.game.entity.Species;
import com.mygdx.game.entity.attributes.Skill;
import com.mygdx.game.entity.attributes.Element;
import com.mygdx.game.entity.attributes.ElementAdvantage;
import com.mygdx.game.action.EngimonInteraction;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

public class Breed extends EngimonInteraction {
    public Breed(Engimon e1, Engimon e2) {
        super(e1, e2);
    }

    public Engimon startBreeding(SpeciesDatabase speciesDB) {
        if (e1.level() >= 30 && e2.level() >= 30) {

            ArrayList<Skill> skillP1 = e1.getSkillArray();
            ArrayList<Skill> skillP2 = e2.getSkillArray();
            ArrayList<Skill> skillChild = new ArrayList<Skill>();

            int p1Index = -1, p2Index = -1;
            Skill skillTemp = new Skill("", -1, Element.NOELEMENT, -1);
            boolean fromP1 = false, fromP2 = false;
            // Skill maker
            while ((!skillP1.isEmpty() || !skillP2.isEmpty()) && skillChild.size() < 4) {
                fromP1 = false;
                fromP2 = false;

                for (int i = 0; i < skillP1.size(); i++) {
                    if (skillTemp.masteryLevel() < skillP1.get(i).masteryLevel()) {
                        skillTemp = skillP1.get(i);
                        fromP1 = true;
                        p1Index = i;
                    }
                }

                for (int i = 0; i < skillP2.size(); i++) {
                    if (fromP1 && !fromP2 && skillTemp.skillName() == skillP2.get(i).skillName()) {
                        if (skillTemp.masteryLevel() == skillP2.get(i).masteryLevel()) {
                            skillTemp.levelUpMastery();
                            fromP2 = true;
                            p2Index = i;
                        }
                    }
                    else if (skillTemp.masteryLevel() < skillP2.get(i).masteryLevel()) {
                        skillTemp = skillP2.get(i);
                        fromP1 = false;
                        fromP2 = true;
                        p2Index = i;
                    }
                }

                if (!skillP1.isEmpty() && fromP1) {
                    skillP1.remove(p1Index);
                }

                if (!skillP2.isEmpty() && fromP2) {
                    skillP2.remove(p2Index);
                }


                skillChild.add(skillTemp);
            }


            // Element maker
            Iterator p1ElementIter = e1.getSpecies().getElementSet().iterator();
            Iterator p2ElementIter = e2.getSpecies().getElementSet().iterator();
            Element childElement1 = Element.NOELEMENT;
            Element childElement2 = Element.NOELEMENT;

            Element p1FirstElement = (Element) p1ElementIter.next();
            Element p2FirstElement = (Element) p2ElementIter.next();

            boolean advantageBranch = false;
            fromP1 = false;
            fromP2 = false;

            if (p1FirstElement == p2FirstElement) {
                childElement1 = p1FirstElement;
                fromP1 = true;
            }
            else {
                advantageBranch = true;
                if (ElementAdvantage.getAdvantage(p1FirstElement, p2FirstElement)
                    > ElementAdvantage.getAdvantage(p2FirstElement, p1FirstElement)) {
                    childElement1 = p1FirstElement;
                    fromP1 = true;
                }
                else if (ElementAdvantage.getAdvantage(p1FirstElement, p2FirstElement)
                    < ElementAdvantage.getAdvantage(p2FirstElement, p1FirstElement)) {
                    childElement1 = p1FirstElement;
                    fromP1 = true;
                }
                else {
                    childElement1 = p1FirstElement;
                    childElement2 = p2FirstElement;
                }
            }

            // Species decider
            Species childSpecies;
            try {
                if (advantageBranch) {
                        if (fromP1)
                            childSpecies = speciesDB.getItem(e1.getSpecies().speciesName());
                        else if (fromP2)
                            childSpecies = speciesDB.getItem(e2.getSpecies().speciesName());
                        else
                            childSpecies = speciesDB.getSpecificSpeciesWithElement(
                            childElement1, childElement2);

                }
                else
                    childSpecies = speciesDB.getItem(e1.getSpecies().speciesName());
            }
            catch (ItemNotFound e) {
                System.out.println("Internal error: Breeding failed, Element not found");
                System.out.println("Continuing...");
                childSpecies = null;
            }

            if (childSpecies != null) {
                Engimon childEngimon = new Engimon(childSpecies, false);
                for (Skill s : skillChild)
                    childEngimon.addSkill(new Skill(s));

                e1.breedingLevelDown();
                e2.breedingLevelDown();

                childEngimon.addParent(e1.getSpecies(), e1.engimonName());
                childEngimon.addParent(e2.getSpecies(), e2.engimonName());
                return childEngimon;
            }
        }


        return null;
    }

}
