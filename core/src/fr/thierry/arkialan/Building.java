package fr.thierry.arkialan;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Building class
 * Created by Thierry
 * 21/03/2017
 */
public abstract class Building {

    protected ShapeRenderer sr = GameScreen.sr;
    protected Vector2 pos;

    public abstract void render();
}
