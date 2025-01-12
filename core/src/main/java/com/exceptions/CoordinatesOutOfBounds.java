package com.exceptions;

public class CoordinatesOutOfBounds extends Exception {

    public CoordinatesOutOfBounds(){
        super("No existing cell at indicated x and y .");
    }

}
