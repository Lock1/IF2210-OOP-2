package com.mygdx.game;

import com.mygdx.game.entity.*;
import com.mygdx.game.entity.species.Luxio;

public class GameLogic {
    // TODO : Wait response
    public GameLogic() {
        Engimon test = new Engimon(new Luxio(), false);
        System.out.println(test.engimonInteract());
    }


}
