package com.models.nature.entities;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.behaviours.event.Event;
import com.behaviours.state.StateManager;
import com.models.creatures.MovingEntity;
import com.models.nature.properties.Blocking;
import com.models.nature.properties.InteractableListener;

public class Soil extends NatureEntity implements InteractableListener , Blocking{

    public Soil(Cell cell){
        super(cell);
    }
    public Soil(Cell containedCell, float x , float y){
        super(containedCell);
        super.x = x ;
        super.y = y ;
    }
    @Override
    public void impact(StateManager stateManager) {
        
        detectCollision(stateManager, this);
    }
    @Override
    public String toString() {
        return "Soil at ("+x+","+y+")";
}



}
