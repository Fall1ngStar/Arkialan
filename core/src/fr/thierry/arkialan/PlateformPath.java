package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.GraphPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thierry on 23/03/2017.
 */
public class PlateformPath implements GraphPath<Building>{

    private List<Building> path;

    public PlateformPath(){
        path = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return path.size();
    }

    @Override
    public Building get(int index) {
        return path.get(index);
    }

    @Override
    public void add(Building node) {
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
    public Iterator<Building> iterator() {
        return path.iterator();
    }
}
