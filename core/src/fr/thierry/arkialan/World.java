package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.Graph;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import fr.thierry.arkialan.utils.ArrayListNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * World class
 * Created by Thierry
 * 21/03/2017
 */
public class World implements Graph<Building>, IndexedGraph<Building> {

    private List<Building> buildings;
    private List<Road> roads;

    private SelectableBuilding roadOne;
    private SelectableBuilding roadTwo;

    public World() {
        buildings = new ArrayListNotNull<>();
        roads = new ArrayListNotNull<>();
    }

    public void render(float delta) {
        roads.forEach(Road::render);
        buildings.forEach(e -> e.render(delta));
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
            buildings.stream().filter(e -> e instanceof SelectableBuilding)
                    .filter(e -> Vector2.dst(e.getPos().x, e.getPos().y, x, y) < e.getRadius()).findFirst()
                    .ifPresent(e -> {
                        roadOne = (SelectableBuilding) e;
                        roadOne.setSelected(true);
                    });
        } else {

            buildings.stream().filter(e -> e instanceof Selectable)
                    .filter(e -> Vector2.dst(e.getPos().x, e.getPos().y, x, y) < e.getRadius()).findFirst()
                    .ifPresent(e -> {
                        roadTwo = (SelectableBuilding) e;
                    });
        }

        if (roadOne != null && roadTwo != null) {
            if (roadOne != roadTwo && !roads.contains(new Road(roadOne, roadTwo)) && canConstructRoad()) {
                roads.add(new Road(roadOne, roadTwo));
                roads.add(new Road(roadTwo, roadOne));
            } else if (roads.contains(new Road(roadOne, roadTwo))) {
                roads.removeIf((e) -> e.equals(new Road(roadOne, roadTwo)));
            }
            roadOne.setSelected(false);
            roadOne = null;
            roadTwo = null;
        }
    }

    public SelectableBuilding selectForPath(float x, float y) {
        return buildings.stream().filter(e -> e instanceof SelectableBuilding).map(e -> (SelectableBuilding) e)
                .filter(e -> Vector2.dst(e.getPos().x, e.getPos().y, x, y) < e.getRadius()).findFirst().orElse(null);
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

    public boolean canConstructRoad() {
        float m = (roadTwo.getPos().y - roadOne.getPos().y) / (roadTwo.getPos().x - roadOne.getPos().x);
        float p = roadOne.getPos().y - m * roadOne.getPos().x;

        return buildings.stream()
                .anyMatch(e -> Math.abs(m * e.getPos().x - e.getPos().y + p) / Math.sqrt(1 + m * m) > e.getRadius());
    }

    public void generateRandom() {
        Stream.generate(() -> new Vector2(MathUtils.random(-5000, 5000), MathUtils.random(-5000, 5000))).limit(200)
                .map(Plateform::new).forEach(buildings::add);
        buildings.stream()//.parallel()
                //.filter(e -> Math.random() > 0.2)
                .forEach(e -> {
                    buildings.stream()
                            .filter(f -> Vector2.dst(e.pos.x, e.pos.y, f.pos.x, f.pos.y) < 1000)
                            //.findFirst()
                            //.ifPresent(f -> {
                            .limit(10)
                            .forEach(f -> {
                                //System.out.println(e);
                                //System.out.println(f);
                                roads.add(new Road(e, f));
                                roads.add(new Road(f, e));
                            });
                });
    }
}
