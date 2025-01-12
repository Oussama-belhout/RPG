package com.models.nature;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.utils.Array;
import com.behaviours.state.StateManager;
import com.exceptions.CoordinatesOutOfBounds;
import com.exceptions.NonHandledTileType;
import com.models.creatures.MovingEntity;
import com.models.nature.entities.NatureEntity;
import com.models.nature.properties.InteractableListener;

public class EntityBubble {
    // for performance sake
    // from index 0 to 4 , from direction UP to LEFT clockwise
    // BUT ... make it later as ArrayList (InteractableListener[]) to cover the case
    // the case of having multiple impacting object on the same (x,y)
    private NatureEntity[] surroundingCells ;
    // TODO determine isBlocking()
    // TODO do the same for the frog entity
    // TODO find out a way to access blockedKey gloabally in a static way
    StateManager stateManager ;
    // this should be a singelton ---------
  public EntityBubble(StateManager stateManager){
    this.stateManager = stateManager ;
    stateManager.setEntityBubble(this);
    surroundingCells = new NatureEntity[5];
    
    
  }

  public void updateHighSurroundingtCell(NatureEntity highSurroundingtCell){
    this.surroundingCells[1] = highSurroundingtCell;
  }
  public void updateLowSurroundingtCell(NatureEntity lowSurroundingtCell){
    this.surroundingCells[3] = lowSurroundingtCell;
  }
  public void updateLeftSurroundingtCell(NatureEntity leftSurroundingtCell){
    this.surroundingCells[4] = leftSurroundingtCell;
  }
  public void updateRighSurroundingtCell(NatureEntity rightSurroundingtCell){
    this.surroundingCells[2] = rightSurroundingtCell;
  }
  public void updateCenteredCell(NatureEntity centeredCell){
    this.surroundingCells[0] = centeredCell;
  }

  public NatureEntity getLowSurroundingtCell(){
    return surroundingCells[0];
  }

  public void notifyAllCells(){
    for(NatureEntity cell : surroundingCells){
      try {
        if (cell instanceof InteractableListener) {
          ((InteractableListener) cell).impact(stateManager);
        }
      } catch (NonHandledTileType e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void update(){
    updateSurroundingCells();
    notifyAllCells();
  }
    
  private void updateSurroundingCells() {
        try {
          float x = stateManager.getWorld().getPlayer().getX();
          float y = stateManager.getWorld().getPlayer().getY();
          TiledMapTileLayer layer = stateManager.getWorld().getCollisionLayer();
          System.out.println("Player at ("+x+","+y+")");
          surroundingCells[4] =  NatureEntity.newInstance(x , y , layer);
          surroundingCells[0] =  NatureEntity.newInstance(x , y + 1 , layer);
          surroundingCells[1] =  NatureEntity.newInstance(x + 1 , y , layer);
          surroundingCells[2] =  NatureEntity.newInstance(x , y - 1 , layer);
          surroundingCells[3] =  NatureEntity.newInstance(x - 1 , y , layer);
          // PRINT THE SURROUNDING CELLS IN A SINGLE LINE
          for (NatureEntity natureObject : surroundingCells) {
            System.out.print(natureObject);
          }


        } catch (CoordinatesOutOfBounds e) {
          e.printStackTrace();
          // exit the game
          System.exit(1);
        }
  }

  public void setStateManager(StateManager stateManager2) {
    // TODO Auto-generated method stub
    this.stateManager = stateManager2 ;
  }

  public boolean isStable(){
    return surroundingCells[3].isBlocking();
  }

}


