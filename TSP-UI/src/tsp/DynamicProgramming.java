package tsp;

import java.util.ArrayList;

public class DynamicProgramming {
	
	private ArrayList<Double> weights = new ArrayList<Double>();
	private int numberNodes;
	
	private void createWeights(int numberNodes, DistanceMatrix distances){
		for(int i=0; i< numberNodes; i++){
			
			for(int j=0;j<numberNodes;j++){
				double[][] matrix = distances.getMatrix();
				weights.add( matrix[i][j] );
				//System.out.println(matrix[i][j]+" ");
			}
		}
	}
	
	//numberNode size
	public void findTour(Instance tsp, DistanceMatrix distances, Tour optimal) {
		
		numberNodes = tsp.size();
		
		createWeights(numberNodes, distances);
		ArrayList<Double> solutions = new ArrayList<Double>(); 
		ArrayList<Integer> set = new ArrayList<Integer>();
		int size, i;
		double length, bestLength;

	    size=1;
	    for ( i=0; i<numberNodes; i++ ) {
	    	if ( 2*size>size )
	    		size *= 2;
	        else {
	        	System.out.println("Eroare ba!: Instance too large to solve.\n");
	    
	        }
	    }
	    
	    if ( size*numberNodes>size ) {
	    	size *= numberNodes;
	    }
	    else {
	    	System.out.println("Eroare ba!: Instance too large to solve.\n");
	        
	    }
	    
	    for ( i=0; i<size; i++ ){
	            solutions.add((double) -1);
	    }

	    set.add(0);
	    for ( i=1; i<numberNodes; i++ ){
	    	set.add(1);

	    }


	    bestLength = -1;
	    for ( i=1; i<numberNodes; i++ ) {
	        set.set(i, 0);
	        
	        length = calculateDistance(i, set, solutions) + weights.get(i);
	        
	        if ( (bestLength<0) || (length<bestLength) ) {
	            bestLength=length;
	        }
	        set.set(i, 1);
	    }

	    reconstructTour(set,solutions,optimal);
	
	
	}

	private double calculateDistance(int t, ArrayList<Integer> set, ArrayList<Double> solutions) {
		int i, emptySet, ind;
		double length;
	    double bestLength;
	    
	    ind=resultIndex(t, set);
	    if ( solutions.get(ind)>=0 ) {
	    	// solutio
	    	return solutions.get(ind);
	    }

	    
	    emptySet=1;
	    i=0;
	    while ( (emptySet == 1) && (i<numberNodes) ) {
	    	if (set.get(i++) == 1){
	    		emptySet = 0;
	        }
	    }
	    
	    if ( emptySet == 1 ) {
	    	
	        return weights.get(t*numberNodes);
	    }

	    
	    bestLength=-1;
	    for ( i=0; i<numberNodes; i++) {
	    	if ( set.get(i)==1 ) {
	        	set.set(i, 0); 
	        	
	        	length = weights.get(i+numberNodes*t)+calculateDistance(i, set, solutions);
	            
	        	if ( (bestLength<0) || (length<bestLength) ) {
	        		bestLength = length;
	        	}
	                
	        	set.set(i, 1);
	    	}
	    }

	    solutions.set(ind, bestLength);
	    return bestLength;
	}

	private int resultIndex(int t, ArrayList<Integer> set) {
		int setValue, factor, i;

		for ( i=0, setValue=0, factor=1; i<numberNodes; i++ ) {
			if ( set.get(i) != 0 ) {
	        	setValue += factor;
			}
	        factor *= 2;
	    }
		
	    return (t+setValue*numberNodes);
	}
	    
	private void reconstructTour(ArrayList<Integer> set, ArrayList<Double> solutions, Tour optimal){
		int i;
		Integer[] tour = new Integer[numberNodes];
		tour[0]=0;
		
		set.set(0, 0);
	    
	    
	    for ( i=1; i<numberNodes; i++ )
	        set.set(i,1);
	    
	    // find best last node 
	    tour[numberNodes-1] = findPreviousNode(0, set, solutions);
	    
	    // find predecessors one by one
	    set.set(tour[numberNodes-1],0); 
	    for ( i=numberNodes-2; i>0; i-- ) {
	        tour[i] = findPreviousNode(tour[i+1], set, solutions);
	        set.set(tour[i],0);
	    }

	    for( i = 0;i<numberNodes;i++){
	    	optimal.addNode(tour[i]);
	    }
	    optimal.addNode(0);
	}

	private Integer findPreviousNode(int t, ArrayList<Integer> set,
			ArrayList<Double> solutions) {
		int i, node = 0;
		double bestLength;
		double length;
	    
	    set.set(t,0);
	    bestLength=-1;
	    for ( i=0; i<numberNodes; i++) {
	        if ( set.get(i)==1 ) {
	            set.set(i,0); 
	            length = weights.get(t+numberNodes*i)+calculateDistance(i, set, solutions);
	            if ( (bestLength<0) || (length<bestLength) ) {
	                bestLength=length;
	                node=i;
	            }
	            set.set(i,1);
	        }
	    }
	    return node;
	}
}
