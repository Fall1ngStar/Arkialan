package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.*;

/**
 * Created by Thierry on 23/03/2017.
 */
public class SimplePathFinder implements PathFinder<Building> {

    private World world;

    public SimplePathFinder(World world){
        this.world = world;
    }

    @Override
    public boolean searchConnectionPath(Building startNode, Building endNode, Heuristic<Building> heuristic, GraphPath<Connection<Building>> outPath) {
        return false;
    }

    @Override
    public boolean searchNodePath(Building startNode, Building endNode, Heuristic<Building> heuristic, GraphPath<Building> outPath) {
        world.getConnections(startNode);


        return false;
    }

    @Override
    public boolean search(PathFinderRequest<Building> request, long timeToRun) {
        return false;
    }
}
