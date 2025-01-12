package com.behaviours.state.states;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.behaviours.event.KeyEvent;
import com.behaviours.state.StateManager;
import com.models.creatures.Player;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public abstract class State {

    protected Player player ;
    public Animation<TextureRegion> stateAnimation ;
    protected Sound stateSound ;
    protected Map<KeyEvent ,State > nextState ;
    protected StateManager stateManager ;


    State(){
        nextState = new HashMap<KeyEvent , State>();
    }

    State(Player player ,Animation animation ,Sound sound){
        this(player,animation);
        this.stateAnimation = animation ;
        this.stateSound = sound ;
    }
    State(Animation animation ,Sound sound){
        this(animation);
        this.stateSound = sound ;
        nextState = new HashMap<KeyEvent , State>();
    }
    State(Animation animation){
        this();
        this.stateAnimation = animation;
    }
    State(Player player ,Animation animation ){
        this(player);
        this.stateAnimation = animation ;
        this.player = player ;
    }
    public State(Player player ){
        this();
        this.player = player ;
    }

    public void setNextState(Map<KeyEvent, State> nextState) {
        this.nextState.putAll(nextState);
    }

    public State setNextState(KeyEvent keyEvent, State state){
        nextState.put(keyEvent, state);
        return this;
    }

    public void adoptTransition(){
        stateManager.getWorld().getPlayer().setRegion((TextureRegion) stateAnimation.getKeyFrame(0 , true) ); // Use TextureRegion directly
        firstStateAction();
    }
    
    public void makeTranisition(KeyEvent keyEvent){


        if (this.nextState.containsKey(keyEvent)) {    
 
            finalStateAction();
            stateManager.changeState(this.getNextState(keyEvent));
        }
        

    }


    protected abstract void firstStateAction();
    protected abstract void finalStateAction();// should also aquire delta
    public abstract void coreStateAction(float delta);



    private boolean containsKeyEvent(KeyEvent keyEvent){

        for(KeyEvent ke : nextState.keySet()){
            if (ke.equals(keyEvent)) {
                return true ;
            }
        }
        return false ;
    }

    // Create an animation from a sequence of images located in a folder at the given path


    public StateManager getStateManager() {
        return stateManager;
    }

    public State getNextState(KeyEvent ke){

        for (Map.Entry<KeyEvent, State> entry : this.nextState.entrySet()) {
            //System.out.println("is "+entry.getKey()+" the event that we're looking for ? "+entry.getKey().equals(ke));

            if (entry.getKey().equals(ke)) {
                return entry.getValue();
            }
        }
        return null ;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName();
    }


    public void setStateAnimation(Animation<TextureRegion> stateAnimation) {
        this.stateAnimation = stateAnimation;
    }
    public void setStateSound(Sound stateSound) {
        this.stateSound = stateSound;
    }
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

}
