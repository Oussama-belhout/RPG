package com.models.nature.entities;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.behaviours.state.StateManager;

public class Sky extends NatureEntity {

    public Sky(Cell cell){
        super(cell);
    }
    public Sky(Cell containedCell, float x , float y){
        super(containedCell , x , y);

    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Sky at ("+x+","+y+")";
    }
}
