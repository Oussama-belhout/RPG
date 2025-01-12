package com.behaviours.state.states;

import java.util.HashMap;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.models.creatures.Player;

public class StandingState extends State{


    
    public StandingState(Player player){
        super(player);
    }
    public StandingState(Player player,Animation animation , Sound sound){
        super(player ,animation , sound );
    }
    public StandingState(Animation animation){
        super(animation);
    }
    public StandingState(Player player ,Animation animation ){
        super(player , animation);
    }
    public StandingState( ){
        super();
    }


    @Override
    public void finalStateAction() {
        
    }
    @Override
    public void firstStateAction() {

    }
    @Override
    public void coreStateAction(float delta) {
        Player player = stateManager.getWorld().getPlayer();
        //player.setY(player.getY() + player.getVelocity().y * delta * 5f);
        stateManager.updatePosition(player.getX(), player.getY() + player.getVelocity().y * delta * 5f);
    }
}
