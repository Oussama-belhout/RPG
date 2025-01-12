package com.models.nature.properties;

import com.behaviours.state.StateManager;
import com.models.nature.EntityBubble;
import com.models.nature.entities.NatureEntity;

public interface Blocking {


    public default boolean detectCollision(StateManager stateManager , NatureEntity natureObject){
        System.out.println("detecting collision in "+natureObject+"and player is in "+stateManager.getWorld().getPlayer());
        if(stateManager.collidesEntity(natureObject.getX(), natureObject.getY())){
            System.out.println("Collision detected at ("+natureObject.getX()+","+natureObject.getY()+")");
            //System.out.println("Befor rest : Player is at ("+stateManager.getWorld().getPlayer().getX()+","+stateManager.getWorld().getPlayer().getY()+")");
            stateManager.resetOldPosition();
            //System.out.println("After rest : Player is at ("+stateManager.getWorld().getPlayer().getX()+","+stateManager.getWorld().getPlayer().getY()+")");
            stateManager.changeState("standingState");
            return true;
        }
        return false ;
    }

}
