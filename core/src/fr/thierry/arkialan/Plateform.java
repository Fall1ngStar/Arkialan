package fr.thierry.arkialan;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Plateform class
 * Created by Thierry
 * 21/03/2017
 */
public class Plateform extends Building {

    private float radius;

    public Plateform(Vector2 pos){
        this.pos = pos;
        radius = 50f;
    }

    @Override
    public void render() {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0,0,0,1);
        sr.circle(pos.x, pos.y, radius+3);
        sr.setColor(1,1,0,1);
        sr.circle(pos.x, pos.y, radius);
        sr.end();
    }
}
