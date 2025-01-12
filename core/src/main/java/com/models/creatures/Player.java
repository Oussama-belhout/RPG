package com.models.creatures;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.behaviours.state.StateManager;

public class Player extends MovingEntity  {


	public Player(Sprite sprite, TiledMapTileLayer collisionLayer ) {

		super(sprite);
        super.collisionLayer = collisionLayer;

	}
    public Player(){
        super(new Sprite(new Texture("steve_still_right.png")));
    }
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer, StateManager stateManager ) {
        this(sprite , collisionLayer);
        this.stateManager = stateManager ;
    
    }

    
    
    @Override
    public void draw(Batch batch) {
        float delta = Gdx.graphics.getDeltaTime();
        update(delta); // objective is remove completely this 
        super.draw(batch);
    }


	private boolean isCellBlocked(float x, float y) {
		Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
	}

	public boolean collidesRight() {
		for(float step = 0; step <= getHeight(); step += increment)
			if(isCellBlocked(getX() + getWidth(), getY() + step))
				return true;
		return false;
	}

	public boolean collidesLeft() {
		for(float step = 0; step <= getHeight(); step += increment)
			if(isCellBlocked(getX(), getY() + step))
				return true;
		return false;
	}

	public boolean collidesTop() {
		for(float step = 0; step <= getWidth(); step += increment)
			if(isCellBlocked(getX() + step, getY() + getHeight()))
				return true;
		return false;

	}

	public boolean collidesBottom() {
		for(float step = 0; step <= getWidth(); step += increment)
			if(isCellBlocked(getX() + step, getY()))
				return true;
		return false;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}

    public void resetAnimationTime(){
        this.animationTime = 0 ;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Player at ("+getX()+","+getY()+")";
    }


    public StateManager getStateManager() {
        return stateManager;
    }
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }
    public void setXVelocity(float x ){
        this.velocity.x = x ;
    }
    public void setYVelocity(float y){
        this.velocity.y = y ;
    }

}