package com.behaviours.event;

import com.badlogic.gdx.Input.Keys;

public class KeyEvent extends Event {

    public Boolean keyPressed ;
    public Integer keyCode ;

    public KeyEvent(Integer keyCode , Boolean keyPressed){
        this.keyCode = keyCode ;
        this.keyPressed = keyPressed ;
    }

    public boolean isNaK() {
        return keyCode == -1;
    }

    public void setKeyCode(Integer keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public String toString() {
        return "KEY "+Keys.toString(keyCode)+((keyPressed == true)?" Pressed ": " Released");
    }

    @Override
    public int hashCode() {
        int result = keyCode != null ? keyCode.hashCode() : 0;
        result = 31 * result + (keyPressed != null ? keyPressed.hashCode() : 0);
        return result;
    }

  
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        KeyEvent keyEvent = (KeyEvent) obj;

        if (!keyCode.equals(keyEvent.keyCode)) return false;
        return keyPressed.equals(keyEvent.keyPressed);
    }
    public boolean equals(String keyEvent) {
        return this.toString() == keyEvent ;
    }

}
