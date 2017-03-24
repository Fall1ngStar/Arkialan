package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

/**
 * World class
 * Created by Thierry
 * 21/03/2017
 */
public class World implements Graph<Building>, IndexedGraph<Building> {

    private List<Building> buildings;
    private List<Road> roads;

    private Plateform roadOne;
    private Plateform roadTwo;

    public World() {
        buildings = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void render() {
        roads.forEach(Road::render);
        buildings.forEach(Building::render);
    }

    public void addBuilding(Building b) {
        /*buildings.forEach((building) -> {
            if (Math.random() > 0.8) {
                roads.add(new Road(building, b));
            }
        });*/
        buildings.add(b);
    }

    public void clear() {
        roads.clear();
        buildings.clear();
        roadTwo = null;
        roadOne = null;
    }

    public void selectForRoad(float x, float y) {
        if (roadOne == null) {
            int i = -1;
            while (++i < buildings.size() && roadOne == null) {
                Plateform p = (Plateform) buildings.get(i);
                if (Vector2.dst(p.getPos().x, p.getPos().y, x, y) < p.getRadius()) {
                    roadOne = p;
                    roadOne.setSelected(true);
                }
            }
        } else {
            int i = -1;
            while (++i < buildings.size() && roadTwo == null) {
                Plateform p = (Plateform) buildings.get(i);
                if (Vector2.dst(p.getPos().x, p.getPos().y, x, y) < p.getRadius()) {
                    roadTwo = p;
                }
            }
        }

        if (roadOne != null && roadTwo != null) {
            if (roadOne != roadTwo && !roads.contains(new Road(roadOne, roadTwo))) {
                roads.add(new Road(roadOne, roadTwo));
                roads.add(new Road(roadTwo, roadOne));
            }
            roadOne.setSelected(false);
            roadOne = null;
            roadTwo = null;
        }
    }

    public Plateform selectForPath(float x, float y) {
        for(Building b : buildings){
            Plateform p = (Plateform) b;
            if (Vector2.dst(p.getPos().x, p.getPos().y, x, y) < p.getRadius()) {
                return p;
            }
        }
        return null;
    }

    public int getRoadsNumber() {
        return roads.size();
    }

    public int getBuildingsNumber() {
        return buildings.size();
    }

    @Override
    public Array<Connection<Building>> getConnections(Building fromNode) {
        Array<Connection<Building>> array = new Array<>();
        roads.forEach((e) -> {
            if (e.getFromNode() == fromNode) {
                array.add(e);
            }
        });
        return array;
    }

    @Override
    public int getIndex(Building node) {
        return buildings.indexOf(node);
    }

    @Override
    public int getNodeCount() {
        return buildings.size();
    }

    public Building getNode(int index) {
        return buildings.get(index);
    }
}
