package com.models.nature.entities;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.behaviours.state.StateManager;
import com.exceptions.CoordinatesOutOfBounds;
import com.exceptions.NonHandledTileType;
import com.models.creatures.MovingEntity;
import com.models.nature.EntityBubble;
import com.models.nature.properties.Blocking;
import com.models.nature.properties.InteractableListener;


public abstract class NatureEntity extends Cell {

    private static TiledMapTileLayer tileLayer ;
    protected Cell containedCell ;
    protected EntityBubble entityBubble ;
    protected float x , y ;
    // now this method should encapsulate all the impacts (collision , burning ...)

    // i should create a factory that creates cells based on their tile type (rock , wood ...)
    public NatureEntity(Cell containedCell){
        this.containedCell = containedCell;
        this.x = containedCell.getTile().getId();
        this.y = containedCell.getTile().getId();
    }

    public NatureEntity(Cell containedCell, float x , float y){
        this(containedCell);
        this.x = x ;
        this.y = y ;
    }
    public NatureEntity(float x , float y){

        this.x = x ;
        this.y = y ;
    }
    public NatureEntity(){
        super();
    }


    public float getX(){
        return this.x ;
    }

    public float getY() {
        return y;
    }

    public Cell getCell(){
        return containedCell;
    }

    public static NatureEntity newInstance(float x , float y , TiledMapTileLayer inputileLayer) throws CoordinatesOutOfBounds{
        tileLayer = inputileLayer;
        Cell cell = tileLayer.getCell((int) (x / tileLayer.getTileWidth()), (int) (y / tileLayer.getTileHeight()));
        if (cell == null) {
            throw new CoordinatesOutOfBounds();
        }

        // this line is not working fix it !!
        String cellType = (String) cell.getTile().getProperties().get("type");     
        switch (cellType) {
            case "rock":
                return new Rock(cell , x , y);
            case "wood":
                return new Wood(cell , x , y);
            case "soil":
                return new Soil(cell , x , y);
            case "sky":
                return new Sky(cell , x , y);
            default:
                return new NatureEntity(cell) {

                    public void impact(StateManager stateManager) throws NonHandledTileType{
                        throw new NonHandledTileType();
                    }
                    
                };
                
        }

    }
    public void setEntityBubble(EntityBubble entityBubble) {
        this.entityBubble = entityBubble;
    }
    public static void setTileLayer(TiledMapTileLayer tileLayer) {
        NatureEntity.tileLayer = tileLayer;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "NatureObject at ("+x+","+y+")";
    }
    public boolean isBlocking(){
        Class<?>[] interfaces = this.getClass().getInterfaces();
        for (Class<?> iface : interfaces) {
            if (iface.equals(Blocking.class)) {
                return true;
            }
        }
        return false;
    }
}
