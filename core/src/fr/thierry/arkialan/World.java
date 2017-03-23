package fr.thierry.arkialan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

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

    private Plateform selectedOne;
    private Plateform selectedTwo;

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

    public void clear(){
        roads.clear();
        buildings.clear();
        selectedTwo = null;
        selectedOne = null;
    }

    public void select(float x, float y){
        if(selectedOne == null){
            int i = -1;
            while(++i < buildings.size() && selectedOne == null){
                Plateform p = (Plateform) buildings.get(i);
                if(Vector2.dst(p.getPos().x, p.getPos().y, x, y) < p.getRadius()){
                    selectedOne = p;
                    selectedOne.setSelected(true);
                }
            }
        } else {
            int i = -1;
            while(++i < buildings.size() && selectedTwo == null){
                Plateform p = (Plateform) buildings.get(i);
                if(Vector2.dst(p.getPos().x, p.getPos().y, x, y) < p.getRadius()){
                    selectedTwo = p;
                }
            }
        }

        if(selectedOne != null && selectedTwo!= null){
            if(selectedOne != selectedTwo && !roads.contains(new Road(selectedOne, selectedTwo))){
                roads.add(new Road(selectedOne, selectedTwo));
            }
            selectedOne.setSelected(false);
            selectedOne = null;
            selectedTwo = null;
        }
    }
}
