package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * World class
 * Created by Thierry
 * 21/03/2017
 */
public class World {

    private List<Building> buildings;
    private List<Road> roads;

    public World() {
        buildings = new ArrayList<>();
        roads = new ArrayList<>();
    }

    public void render() {
        roads.forEach(Road::render);
        buildings.forEach(Building::render);
    }

    public void addBuilding(Building b) {
        buildings.forEach((building) -> {
            if (Math.random() > 0.8) {
                roads.add(new Road(building, b));
            }
        });
        buildings.add(b);
    }

    public void clear(){
        roads.clear();
        buildings.clear();
    }

}
