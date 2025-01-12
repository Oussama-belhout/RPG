package com.behaviours.state;

import com.badlogic.gdx.Input.Keys;

import java.lang.reflect.Field;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.behaviours.event.KeyEvent;
import com.behaviours.state.states.JumpingState;
import com.behaviours.state.states.StandingState;
import com.behaviours.state.states.State;
import com.behaviours.state.states.WalkingRightState;
import com.models.creatures.Player;
import com.models.nature.EntityBubble;
import com.thegame.environment.World;

public class StateManager extends InputAdapter{
    // i gotta load the states in here Using propLoader
    private State walkingState ;
    private State standingState ;
    private State jumpingState ;
    private World world;
    // states should be loaded and stored in a list here 
    // the states will compare their VALID received event (from (Event , next state)) with the events 
    // in the (Event , state) in order to get the next STATE REFERENCE


    // --------- mechanics manager
    public float speed = 60 * 2, gravity = 60 * 2f, animationTime = 0, increment;
    public Vector2 velocity = new Vector2() ;

    private float stateTimer = 0;

    // where Steve is looking
    public boolean lookingRight = true;
	public boolean canJump;  // so the player can't jump in mid-air
    public boolean shouldFlipSide ; // if true, flip the direction of steve's looking

    // ---------- ressources manager
    public static State currState ;
    public static State prevState ;

    public EntityBubble surroundingNature ;

    public StateManager(World world){
        this.world = world ;
        Player player = world.getPlayer();
        standingState = new StandingState(player);
        walkingState = new WalkingRightState(player);
        jumpingState = new JumpingState(player);
        //player = new Steve(new Sprite(new Texture("steve_still_right.png")), (TiledMapTileLayer) map.getLayers().get("Layer"));
        //steve = new Steve();
        player.setStateManager(this);
        surroundingNature = new EntityBubble(this);
        surroundingNature.setStateManager(this);

    }
    //public StateManager(State iniState){}
    public StateManager(){
        surroundingNature = new EntityBubble(this);

    }

    public void changeState(State state){

        state.adoptTransition();
        
        prevState = currState ;
        currState = state ;

        //setRegion((TextureRegion) currAnimation.getKeyFrame(stateTimer , true) ); // Use TextureRegion directly

    }
    public void changeState(String state){
        State stateObj = null ;

        // retreive the corresponding state from the string , the state with the same attribute name
        try {
            changeState((State) this.getClass().getDeclaredField(state).get(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (state) {
            case "standingState":
                stateObj = this.standingState ;

                break;
            case "walkingState":
                stateObj = this.walkingState ;
                System.out.println(stateObj);
                break;
            case "jumpingState":    
                stateObj = this.jumpingState ;
                System.out.println(stateObj);
                break;  
            
            default:
                stateObj = this.standingState ;
                break;
        }
        changeState(stateObj);
    }




	@Override
	public boolean keyDown(int keycode) {
        KeyEvent ke = new KeyEvent(keycode, true);
        //System.out.println("sent "+ke+" to "+currState);
        currState.makeTranisition(ke);
		return true;
	}
    @Override
    public boolean keyUp(int keycode) {
        KeyEvent ke = new KeyEvent(keycode, false);

        System.out.println("sent "+ke+" to "+currState);
        currState.makeTranisition(new KeyEvent(keycode, false));
		return true;
    }


    // it has no meaning !! these states are setted once at instantiation
    public StateManager setStandingState(State standingState) {
        this.standingState = standingState;
        this.standingState.setStateManager(this);
        return this ;
    }
    public StateManager setWalkingState(State walkingState) {
        this.walkingState = walkingState;
        this.walkingState.setStateManager(this);
        return  this;
    }
    public StateManager setJumpingState(State jumpingState) {
        this.jumpingState = jumpingState;
        this.jumpingState.setStateManager(this);
        return this ;
    }


	public void updateCurrState(float delta) {
        currState.coreStateAction(delta);
        surroundingNature.update();
    }

    
    public void updateAnimation(float delta){
        stateTimer += delta; // Accumulate elapsed animation time so we can update and move to the next frame
        world.getPlayer().setRegion((TextureRegion)currState.stateAnimation.getKeyFrame(stateTimer, true)); // Update the region based on stateTime
    }


    public void updatePosition( float x , float y){
        world.getPlayer().oldX = world.getPlayer().getX();
        world.getPlayer().oldY = world.getPlayer().getY();
        world.getPlayer().setX(x);
        world.getPlayer().setY(y);
    }

    public void resetOldPosition(){
        // MAKE THE PLAYER GO BACK TO THE OLD POSITION
        // without impacting the logic
        world.getPlayer().setX(world.getPlayer().oldX );
        world.getPlayer().setY(world.getPlayer().oldY );

    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public World getWorld() {
        return world;
    }
    public boolean collidesEntity(float x , float y){
        return x == getWorld().getPlayer().getX() && y == getWorld().getPlayer().getY(); 
    }
    public void setEntityBubble(EntityBubble entityBubble) {
        surroundingNature = entityBubble ;
    }
    public boolean onStablePlatform(){
        return surroundingNature.isStable();
    }
}
