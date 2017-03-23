package fr.thierry.arkialan;

import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Thierry on 23/03/2017.
 */
public class SimpleHeuristic implements Heuristic<Building> {
    @Override
    public float estimate(Building node, Building endNode) {

        return Vector2.dst(node.getPos().x, node.getPos().y, endNode.getPos().x, endNode.getPos().y);
    }
}
