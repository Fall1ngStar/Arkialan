package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Road class
 * Created by Thierry
 * 22/03/2017
 */
public class Road implements Connection<Building>{

    private static ShapeRenderer sr = GameScreen.sr;

    private Building one;
    private Building two;
    private float distance;

    public Road(Building one, Building two){
        this.one = one;
        this.two = two;
        distance = Vector2.dst(one.getPos().x, one.getPos().y, two.getPos().x, two.getPos().y);
    }

    public void render(){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.2f,0.2f,0.2f,1);
        sr.rectLine(one.pos.x, one.pos.y, two.pos.x, two.pos.y,5);
        sr.end();
    }

    public boolean equals(Object o){
        if(!(o instanceof Road)){
            return false;
        }
        if(o == null){
            return false;
        }
        Road other = (Road)o;
        return (other.one == this.one && other.two == this.two)||(other.one == this.two && other.two == this.one);
    }

    @Override
    public float getCost() {
        return distance;
    }

    @Override
    public Building getFromNode() {
        return one;
    }

    @Override
    public Building getToNode() {
        return two;
    }
}
