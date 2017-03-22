package fr.thierry.arkialan;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Road class
 * Created by Thierry
 * 22/03/2017
 */
public class Road {

    private static ShapeRenderer sr = GameScreen.sr;

    private Building one;
    private Building two;

    public Road(Building one, Building two){
        this.one = one;
        this.two = two;
    }

    public void render(){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.2f,0.2f,0.2f,1);
        sr.rectLine(one.pos.x, one.pos.y, two.pos.x, two.pos.y,5);
        sr.end();
    }
}
