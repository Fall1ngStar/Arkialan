package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thierry on 23/03/2017.
 */
public class Unit {

    private static ShapeRenderer sr = GameScreen.sr;
    private static PathProvider provider;

    private Vector2 pos;
    private float speed;

    private PlateformRoadPath path;
    private List<Vector2> vectorPath;
    private Iterator<Vector2> iteratorPath;
    private Vector2 nextDestination;
    private Building lastDestination;

    public Unit(PlateformRoadPath path){
        initialisePath(path);
    }

    private void generateVectorPath(){
        Iterator<Connection<Building>> iterator = path.iterator();
        Road r = null;
        while(iterator.hasNext()){
            r = (Road)(iterator.next());
            vectorPath.add(r.getToNode().getPos());
        }
        vectorPath.add(r.getFromNode().getPos());
        lastDestination =  r.getFromNode();
    }


    public void update(){
        if(isNear(nextDestination)){
            nextDestination = iteratorPath.hasNext() ? iteratorPath.next() : null;
        }
        if(nextDestination != null){
            moveToward(nextDestination);
        } else {
            initialisePath(provider.getRandomPath(lastDestination));
        }


    }

    public void render() {
        update();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0,1,0,1);
        sr.circle(pos.x, pos.y, 10);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(0,0,0,1);
        sr.circle(pos.x, pos.y, 10);
        sr.end();
    }

    public void moveToward(Vector2 destination){
        if (destination != null) {
            pos.add(destination.cpy().sub(pos.cpy()).nor().scl(speed));
        }
    }

    private boolean isNear(Vector2 targetDestination){
        if(targetDestination != null){
            return Vector2.dst(pos.x, pos.y, targetDestination.x, targetDestination.y) < speed;
        }
        return false;
    }


    private void initialisePath(PlateformRoadPath path){
        this.path = path;
        vectorPath = new ArrayList<>();
        generateVectorPath();
        speed = 4f;
        iteratorPath = vectorPath.iterator();
        nextDestination = iteratorPath.next();
        this.pos = nextDestination.cpy();
    }

    public static void setPathProvider(PathProvider p){
        provider = p;
    }
}
