package com.thegame.environment;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.behaviours.event.KeyEvent;
import com.behaviours.state.StateManager;
import com.behaviours.state.states.JumpingState;
import com.behaviours.state.states.StandingState;
import com.behaviours.state.states.State;
import com.behaviours.state.states.WalkingLeftState;
import com.behaviours.state.states.WalkingRightState;
import com.models.creatures.Player;
import com.badlogic.gdx.Input.Keys;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Platform extends Game {
    //public SpriteBatch batch;
    public World world ;
    public StateManager stateManager ;
    public State walkingRightState ;
    public State walkingLeftState ;
    public State standingState ;
    public State jumpingState ;

    public Platform(){
    }


   // public Minecraft(DynamicConfigs dynamicConfigs){}

    @Override
    public void create() {
        //world = new World(stateManager);
        Map<KeyEvent ,State > transitions = new HashMap<KeyEvent,State>() ;
        stateManager = new StateManager();
        walkingRightState = new WalkingRightState();
        walkingLeftState = new WalkingLeftState();

        standingState = new StandingState();
        jumpingState = new JumpingState();
        

        walkingRightState.setStateAnimation(createAnimation("steve/running/" , 0.1f));
        transitions.put(new KeyEvent(Keys.UP , true), jumpingState);
        transitions.put(new KeyEvent(Keys.LEFT , false), standingState);
        transitions.put(new KeyEvent(Keys.RIGHT , false), standingState);
        walkingRightState.setNextState(transitions);
        walkingRightState.setStateManager(stateManager);
        transitions.clear();

        walkingLeftState.setStateAnimation(createAnimation("steve/running/" , 0.1f));
        transitions.put(new KeyEvent(Keys.UP , true), jumpingState);
        transitions.put(new KeyEvent(Keys.LEFT , false), standingState);
        transitions.put(new KeyEvent(Keys.RIGHT , false), standingState);
        walkingLeftState.setNextState(transitions);
        walkingLeftState.setStateManager(stateManager);
        transitions.clear();

        standingState.setStateAnimation(createAnimation("steve/standing/" , 0.1f) );
        transitions.put(new KeyEvent(Keys.UP , true), jumpingState);
        transitions.put(new KeyEvent(Keys.RIGHT , true), walkingRightState);
        transitions.put(new KeyEvent(Keys.LEFT , true), walkingLeftState);
        standingState.setNextState(transitions);
        standingState.setStateManager(stateManager);
        transitions.clear();

        jumpingState.setStateAnimation(createAnimation("steve/jumping/" , 0.1f));
        jumpingState.setStateManager(stateManager);

        
        //  this line seems pretty nasty
        this.world = new World(stateManager);

        stateManager.setJumpingState(jumpingState).setStandingState(standingState).setStandingState(standingState);

        stateManager.changeState(standingState);
        // here we're letting the states know which entity they are responsible of 
        // after creating the world

        this.setScreen(new MainScreen(world));
        
    }

    @Override
    public void render() {
        super.render(); //important!
    }

    protected Animation createAnimation(String path, float frameDuration) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        Texture texture ;

        int i = 1 ;

        try {
            while ((texture = new Texture(path + i + ".png")) != null) {
                System.out.println("we are here");
                frames.add(new TextureRegion(texture));
                System.out.println(texture.getTextureData());
                i++;
            }
        } catch (com.badlogic.gdx.utils.GdxRuntimeException e) {
            System.out.println("No more frames");
        }
        return new Animation<>(frameDuration, frames);
    }
 
}
