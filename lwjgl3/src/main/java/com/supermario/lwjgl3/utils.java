package com.supermario.lwjgl3;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class utils {

        private void flipAllFrames(Animation<TextureRegion> animation, boolean flipX, boolean flipY) {
        Object[] frames = animation.getKeyFrames();
        for (int i = 0; i < frames.length; i++) {
            if(frames[i] instanceof TextureRegion)
                ((TextureRegion) frames[i]).flip(flipX, flipY);
            else
                ((Sprite) frames[i]).flip(flipX, flipY);
            
        }
    }

    // Create an animation from a sequence of images located in a folder at the given path
    protected Animation createAnimation(String path, float frameDuration) {
        Array<TextureRegion> frames = new Array<TextureRegion>();
        Texture texture ;

        int i = 1 ;

        try {
            while ((texture = new Texture(path + i + ".png")) != null) {
                System.out.println("we are here");
                frames.add(new TextureRegion(texture));
                System.out.println(texture.getTextureData());
                i++;
            }
        } catch (com.badlogic.gdx.utils.GdxRuntimeException e) {
            System.out.println("No more frames");
        }
        return new Animation<>(frameDuration, frames);
    }
}
