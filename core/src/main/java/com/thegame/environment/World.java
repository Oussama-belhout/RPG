package com.thegame.environment;

import javax.swing.text.html.parser.Entity;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.behaviours.state.StateManager;
import com.models.creatures.Player;


public class World {

    private float speed = 60 * 2, gravity = 60 * 2f, increment;
    private Vector2 velocity = new Vector2();


    private TmxMapLoader mapLoader; // this should be put in a separate loader

    private TiledMap map;

    private Player player ;
    private Array<Sprite> entities = new Array<>(5);
    
    StateManager stateManager ;

    public World(StateManager stateManager){
        

        this.stateManager = stateManager ;
        this.stateManager.setWorld(this);
        // Load the tiledMap

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("mc.tmx");

        // Create the player
        player = new Player(new Sprite(new Texture("steve_still_right.png")), (TiledMapTileLayer) map.getLayers().get("Layer") , stateManager);
        player.setPosition(300, 240);
        

        entities.add(player);
    }

    public TiledMap getMap() {
        return map;
    }
    public Array<Sprite> getEntities(){
        return entities ;
    }
    public Player getPlayer(){
        return this.player ;
    }

    public StateManager getStateManager() {
        return this.stateManager ;
    }
    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return (TiledMapTileLayer) map.getLayers().get("Layer");
    }

    public Vector2 getVelocity() {
        return velocity;
    }

}
