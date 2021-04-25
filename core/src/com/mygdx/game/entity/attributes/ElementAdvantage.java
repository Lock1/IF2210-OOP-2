package com.mygdx.game.entity.attributes;

import com.mygdx.game.entity.attributes.Element;

public class ElementAdvantage {
    public static final double advantageMatrix[][] =
    { {1,0,1,0.5,2},
      {2,1,0,1,1},
      {1,2,1,0,1.5},
      {1.5,1,2,1,0},
      {0,1,0.5,2,1} };
    // Fire     1   0 1   0.5 2
    // Water    2   1 0   1   1
    // Electric 1   2 1   0   1.5
    // Ground   1.5 1 2   1   0
    // Ice      0   1 0.5 2   1
    public static double getAdvantage(Element e1, Element e2) {
        return advantageMatrix[getElementIndex(e1)][getElementIndex(e2)];
    }

    private static int getElementIndex(Element e) {
        switch (e) {
            case FIRE:
                return 0;
            case WATER:
                return 1;
            case ELECTRIC:
                return 2;
            case GROUND:
                return 3;
            case ICE:
                return 4;
            default:
                return -1;
        }
    }
}
