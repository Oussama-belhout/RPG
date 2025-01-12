package com.behaviours.state.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.models.creatures.Player;

public class JumpingState extends State{

    public JumpingState(Player player ){
        super(player);

    }
    public JumpingState(Player player ,Animation animation , Sound sound){
        super(player ,animation , sound );
    }

    public JumpingState(Animation animation){
        super(animation);
    }
    public JumpingState(Player player ,Animation animation ){
        super(player , animation);
    }
    public JumpingState(){
        super();
    }


    @Override
    public void firstStateAction() {
        Player player = stateManager.getWorld().getPlayer();
        float delta = Gdx.graphics.getDeltaTime();
        float oldX = player.getX(), oldY = player.getY();
        boolean jumbed = false;

        

        if (stateManager.onStablePlatform() && !jumbed) {
            stateManager.velocity.y = stateManager.speed / 1.8f;
            //velocity.y = speed / 1.8f;
            stateManager.updatePosition(player.getX(), player.getY() + stateManager.velocity.y * delta);
            //player.setX(player.getX() + stateManager.velocity.x * delta);
            jumbed = true;
        }
        // he can only move right left up down
        //if(stateManager.surroundingNature)
  

        // then texture region
    }

    @Override
    public void finalStateAction() {
        
    }
    @Override
    public void coreStateAction(float delta) {
        
    }


    
}
