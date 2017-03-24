package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thierry on 23/03/2017.
 */
public class PlateformRoadPath implements GraphPath<Connection<Building>>, Cloneable {

    private List<Connection<Building>> path;

    public PlateformRoadPath() {
        path = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return path.size();
    }

    @Override
    public Connection<Building> get(int index) {
        return path.get(index);
    }

    @Override
    public void add(Connection<Building> node) {
        path.add(node);
    }

    @Override
    public void clear() {
        path.clear();
    }

    @Override
    public void reverse() {

    }

    @Override
    public Iterator<Connection<Building>> iterator() {
        return path.iterator();
    }

    public PlateformRoadPath clone(){
        PlateformRoadPath clone = new PlateformRoadPath();
        clone.path = new ArrayList<>(path);
        return clone;
    }
}
