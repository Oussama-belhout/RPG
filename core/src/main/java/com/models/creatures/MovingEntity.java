package com.models.creatures;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.behaviours.state.StateManager;

public abstract class MovingEntity extends Sprite {

	// identified by a hash code
	protected StateManager stateManager ;

	protected float speed = 60 * 2, gravity = 60 * 2f, animationTime = 0, increment;
    protected Vector2 velocity = new Vector2();
	protected boolean canJump;  // so the player can't jump in mid-air
	public float oldX , oldY ;

	protected TiledMapTileLayer collisionLayer;
	protected String blockedKey = "block"; // the property key for the cells that block movement 

	
    public MovingEntity (Sprite sprite) {
		super(sprite);
	}

	    //here is the frame loop
    //this is where we will update the frame of the sprite after a certain amount of time
	public void update(float delta) {
		applyPhisics(delta); // this is STRONGLY COUPLED TO WORLD
        this.stateManager.updateCurrState(delta); // update animation is in here
        //detectCollision(delta);

    }
    private void applyPhisics(float delta){
        velocity.y -= gravity * delta;

		// clamp(tweek) velocity
		if(velocity.y > speed)
			velocity.y = speed;
		else if(velocity.y < -speed)
			velocity.y = -speed;

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
	
	
    public void updatePosition(int x , int y) {
        Player player = stateManager.getWorld().getPlayer();
        player.oldX = player.getX();
        player.oldY = player.getY();
        player.setX(x);
        player.setY(y);
    }

}
