package com.models.nature.properties;

import com.behaviours.event.Event;
import com.behaviours.state.StateManager;
import com.exceptions.NonHandledTileType;
import com.models.nature.EntityBubble;

public interface InteractableListener {

    public void impact(StateManager stateManager) throws NonHandledTileType;

}
