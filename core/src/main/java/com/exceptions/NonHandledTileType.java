package com.exceptions;

public class NonHandledTileType extends Exception {

    public NonHandledTileType() {
        super("No declared handlers for this type of tile");
    }

}
