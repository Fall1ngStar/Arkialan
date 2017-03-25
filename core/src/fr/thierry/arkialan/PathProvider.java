package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;

/**
 * PathProvider class
 * Created by Thierry
 * 24/03/2017
 */
public class PathProvider {

    private World world;
    private IndexedAStarPathFinder<Building> finder;
    private SimpleHeuristic heuristic;


    public PathProvider(World world){
        this.world = world;
        heuristic = new SimpleHeuristic();
    }

    public PlateformRoadPath getRandomPath(Building fromNode){
        int i = 0;
        finder = new IndexedAStarPathFinder<>(world);
        PlateformRoadPath result = new PlateformRoadPath();
        do{
            finder.searchConnectionPath(world.getNode((int)(Math.random() * world.getNodeCount())),fromNode, heuristic,result);
        }while(result.getCount() == 0 && ++i < 20);
        return result;
    }
}
