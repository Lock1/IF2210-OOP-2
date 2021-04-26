package com.mygdx.game;

import com.mygdx.game.entity.*;
import com.mygdx.game.entity.species.*;


public class GameLogic {
    // private Player currentPlayer 
    public GameLogic() {
        Engimon test = new Engimon(new Luxio(), false);
        System.out.println(test.engimonInteract());
    }


}

