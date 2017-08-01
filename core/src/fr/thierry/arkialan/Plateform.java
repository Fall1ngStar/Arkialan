package fr.thierry.arkialan;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Plateform class
 * Created by Thierry
 * 21/03/2017
 */
public class Plateform extends SelectableBuilding{

    public Plateform(Vector2 pos) {
        this.pos = pos;
        radius = 40f;
    }

    @Override
    public void render(float delta) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 0, 0, 1);
        sr.circle(pos.x, pos.y, radius + 3 + (selected ? 4 : 0));
        sr.setColor(1, 1, 0, 1);
        sr.circle(pos.x, pos.y, radius + (selected ? 4 : 0));
        sr.end();
    }
}
