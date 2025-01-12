package com.models.nature.entities;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.behaviours.event.Event;
import com.behaviours.state.StateManager;
import com.models.creatures.MovingEntity;
import com.models.creatures.Player;
import com.models.nature.properties.Blocking;
import com.models.nature.properties.InteractableListener;

public class Rock extends NatureEntity implements InteractableListener , Blocking {

    public Rock(Cell cell){
        super(cell);
    }
    public Rock(Cell containedCell, float x , float y){
        super(containedCell , x , y);

    }


    @Override
    public void impact(StateManager stateManager) {
        detectCollision(stateManager, this);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Rock at ("+x+","+y+")";
    }
}
