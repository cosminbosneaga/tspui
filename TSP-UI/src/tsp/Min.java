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
            return Node.edge(NodeList.findNode(from), NodeList.findNode(0));
        }
        ArrayList<Double> distances = new ArrayList<Double>();
        for (Integer node: through){
            ArrayList<Integer> throughCopy = new ArrayList<Integer>(through);
            throughCopy.remove(node);
            double current = Node.edge(NodeList.findNode(from), NodeList.findNode(node)) + new Min(node, throughCopy).getMin();
            distances.add(current);
        }
        return Collections.min(distances);
    }


}