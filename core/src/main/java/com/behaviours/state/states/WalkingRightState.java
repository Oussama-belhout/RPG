package com.behaviours.state.states;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.behaviours.state.action.mechanical;
import com.models.creatures.Player;
import com.thegame.environment.World;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class WalkingRightState extends State implements mechanical{

    public WalkingRightState(Player player ){
        super(player);

    }
    public WalkingRightState(Player player ,Animation animation , Sound sound){
        super(player ,animation , sound );
    }

    public WalkingRightState(Animation animation){
        super(animation);
    }
    public WalkingRightState(Player player ,Animation animation ){
        super(player , animation);
    }
    public WalkingRightState(){
        super();
    }


    @Override
    public void firstStateAction() {

        Player player = stateManager.getWorld().getPlayer();
        float delta = Gdx.graphics.getDeltaTime();
        float oldX = player.getX(), oldY = player.getY();
        boolean collisionX = false, collisionY = false;

        // he can only move right left up down
        //if(stateManager.surroundingNature)
        player.setX(player.getX() + stateManager.velocity.x * delta);



        System.out.println("im trying to walk, my current velocity is "+ stateManager.velocity.x);

        System.out.println("but now , my current velocity is "+ stateManager.velocity.x);
        stateManager.animationTime = 0;
        stateManager.shouldFlipSide = stateManager.lookingRight ? false : true;
        //flipAllFrames(stateAnimation, stateManager.shouldFlipSide, false);
        stateManager.lookingRight = stateManager.shouldFlipSide ? !stateManager.lookingRight : stateManager.lookingRight;
        //setRegion((TextureRegion) stateAnimation.getKeyFrame(stateTimer , true) ); // Use TextureRegion directly

    }

    @Override
    public void finalStateAction() {
        
    }
    @Override
    public void coreStateAction(float delta) {

        stateManager.velocity.x = stateManager.speed;
        stateManager.updatePosition( stateManager.getWorld().getPlayer().getX() + stateManager.velocity.x * delta ,
                                                    stateManager.getWorld().getPlayer().getY() );
        stateManager.updateAnimation(delta);
        
    }

    
    public void flipAllFrames(Animation<TextureRegion> animation, boolean flipX, boolean flipY) {
        Object[] frames = animation.getKeyFrames();
        for (int i = 0; i < frames.length; i++) {
            if(frames[i] instanceof TextureRegion)
                ((TextureRegion) frames[i]).flip(flipX, flipY);
            else
                ((Sprite) frames[i]).flip(flipX, flipY);
            
        }
    }
    @Override
    public void updatePosition(int x , int y) {
        Player player = stateManager.getWorld().getPlayer();
        player.oldX = player.getX();
        player.oldY = player.getY();
        player.setX(x);
        player.setY(y);
    }
    


    
}
