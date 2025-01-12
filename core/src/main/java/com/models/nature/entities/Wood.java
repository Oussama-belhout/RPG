package com.models.nature.entities;

import java.security.Key;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.behaviours.event.Event;
import com.behaviours.event.KeyEvent;
import com.behaviours.state.StateManager;
import com.models.creatures.MovingEntity;
import com.models.nature.properties.Blocking;
import com.models.nature.properties.InteractableListener;

public class Wood extends NatureEntity implements InteractableListener , Blocking {

    public Wood(Cell cell){
        super(cell);
    }
    public Wood(Cell containedCell, float x , float y){
        super(containedCell);
        super.x = x ;
        super.y = y ;
    }

    public void detectCollision(){

    }
    @Override
    public void impact(StateManager stateManager ) {
/* 
        if (event instanceof KeyEvent) {
            event = ((KeyEvent)event);

            // i want to compare with this cell's coordinates
            if (lifeEntity.getX() == this.) {
                
            }
        }
*/

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Wood at ("+x+","+y+")";
    }
}
