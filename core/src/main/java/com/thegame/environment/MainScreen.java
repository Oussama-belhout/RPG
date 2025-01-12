package com.thegame.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.models.creatures.MovingEntity;
import com.models.creatures.Player;

public class MainScreen implements Screen {
    private World world ;
    
    private OrthographicCamera gamecam;
    private Viewport viewport ;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private Player player , player2;


    public MainScreen(World world) {

        this.world = world ;
        this.player = world.getPlayer();
        
        gamecam = new OrthographicCamera();
        viewport = new ScreenViewport(gamecam);

        // Render the map
        renderer = new OrthogonalTiledMapRenderer(world.getMap());
        gamecam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);

        // Create the player
        //this.player = player ;
        //player.setCollisionLayer((TiledMapTileLayer) map.getLayers().get("Layer"));

        

    }

    public void render(float delta) {
        updateView(delta); // update the game objects
        //reset the screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render the game map and set the camera
        renderer.render();
        
        //game.getBatch().setProjectionMatrix(gamecam.combined);
        renderer.getBatch().setProjectionMatrix(gamecam.combined);
        renderer.getBatch().begin();
        for(Sprite entity : world.getEntities()){
            if (entity instanceof MovingEntity) {
                entity.draw(renderer.getBatch());
            }
        }
        //player.draw(renderer.getBatch());
        renderer.getBatch().end();

    }

    private void updateView(float delta) {
        handleInput(delta);

        gamecam.update();   
        gamecam.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        gamecam.position.x = world.getPlayer().getX(); // Follow the player

        renderer.setView(gamecam);
    
        
    }
    private void handleInput(float delta) {
        if (Gdx.input.isTouched()) {
            gamecam.position.x += 100 * delta;
        }
    }


    public void show() {
        Gdx.input.setInputProcessor(world.getStateManager());
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void pause() {
        // TODO Auto-generated method stub
    }

    public void resume() {
        // TODO Auto-generated method stub
    }

    public void hide() {
        // TODO Auto-generated method stub
    }

    public void dispose() {
        // TODO Auto-generated method stub
    }

}
