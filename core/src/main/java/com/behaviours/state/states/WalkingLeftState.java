package com.behaviours.state.states;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.models.creatures.Player;

public class WalkingLeftState extends WalkingRightState{

    public WalkingLeftState(){
        super();
    }
    public WalkingLeftState(Player player){
        super(player);
    }


    public WalkingLeftState(Player player,Animation animation , Sound sound){
        super(player ,animation , sound );
    }
    public WalkingLeftState(Animation animation){
        super(animation);
    }
    public WalkingLeftState(Player player ,Animation animation ){
        super(player , animation);
    }

    @Override
    public void coreStateAction(float delta) {
        stateManager.velocity.x = stateManager.speed;
        stateManager.updatePosition( stateManager.getWorld().getPlayer().getX() - stateManager.velocity.x * delta ,
                                                    stateManager.getWorld().getPlayer().getY() );
        stateManager.updateAnimation(delta);
    }
    @Override
    public void firstStateAction() {
        // TODO Auto-generated method stub
        flipAllFrames(stateAnimation, true, false);
        Player player = stateManager.getWorld().getPlayer();
        player.setVelocity(new Vector2(-1,0));
    }
    @Override
    public void finalStateAction() {
        // TODO Auto-generated method stub
        Player player = stateManager.getWorld().getPlayer();
        player.setVelocity(new Vector2(0,0));
    }
}
