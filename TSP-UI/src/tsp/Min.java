package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class Min {

    private int from;
    private ArrayList<Integer> through;


    public Min (int from, ArrayList<Integer> through){
        this.from = from;
        this.through = through;
    }

    public double getMin (){

        if (through.isEmpty()){
            return Instance.edge(from, 0);
        }
        ArrayList<Double> distances = new ArrayList<Double>();
        for (Integer node: through){
            ArrayList<Integer> throughCopy = new ArrayList<Integer>(through);
            throughCopy.remove(node);
            double current = Instance.edge(from, node) + new Min(node, throughCopy).getMin();
            distances.add(current);
        }
        return Collections.min(distances);
    }


}