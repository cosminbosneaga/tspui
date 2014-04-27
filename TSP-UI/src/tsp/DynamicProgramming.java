package tsp;

import java.util.ArrayList;
import java.util.HashMap;

public class DynamicProgramming {
	
	private ArrayList<Double> weights = new ArrayList<Double>();
	private int numberNodes;
	
	private void createw(int numberNodes, DistanceMatrix distances){
		for(int i=0; i< numberNodes; i++){
			
			for(int j=0;j<numberNodes;j++){
				double[][] matrix = distances.getMatrix();
				weights.add( matrix[i][j] );
				//System.out.println(matrix[i][j]+" ");
			}
		}
	}
	
	//numberNode size
	public double findTour(Instance tsp, DistanceMatrix distances) {
		
		numberNodes = tsp.size();
		
		createw(numberNodes, distances);
		ArrayList<Double> solutions = new ArrayList<Double>(); 
		ArrayList<Integer> set = new ArrayList<Integer>();
		int size, i;
		double length, bestLength;

	    /* first we allocate the space for L[][] */
	    size=1;
	    for ( i=0; i<numberNodes; i++ ) {
	    	if ( 2*size>size )
	    		size *= 2;
	        else {
	        	System.out.println("Eroare ba!: Instance too large to solve.\n");
	            return 0;
	        }
	    }
	    
	    if ( size*numberNodes>size ) {
	    	size *= numberNodes;
	    }
	    else {
	    	System.out.println("Eroare ba!: Instance too large to solve.\n");
	        return 0;
	    }
	    
	        /*if ( ( solutions=(int *)malloc(sizeof(int)*size) ) == NULL ) {
	            System.out.println("Eroare ba!: Ceva in C ca nu poate aloca memorie.\n");
	            return;
	        }*/
	    /* We initialise all entries as -1; this signals `not computed yet'. */
	    for ( i=0; i<size; i++ ){
	            solutions.add((double) -1);
	    }

	    /* set X to set of all nodes except node 0 */
//	        set[0]=0;
	    set.add(0);
	    for ( i=1; i<numberNodes; i++ ){
	    	set.add(1);
//	            set[i]=1;
	    }

	    /* compute L(0, t, X) for all t and maximal X */
	    bestLength = -1;
	    for ( i=1; i<numberNodes; i++ ) {
	        set.set(i, 0);
	        
	        length = computeLength(i, set, solutions) + weights.get(i);
	        
	        if ( (bestLength<0) || (length<bestLength) ) {
	            bestLength=length;
	        }
	        set.set(i, 1);
	    }

	    System.out.println("Best tour has length " + bestLength);
	    //reconstructTour(set, solutions, numberNodes, weights);
	    return bestLength;
	}

	/* This is the function that computes L(t, X). The implementation is actually recursive. */
	/* We store all results in L[][] and look up if the result is already there first. */
	/* In comparison to a proper implementation this wastes a factor or n/2 in space. */
	private double computeLength(int t, ArrayList<Integer> set, ArrayList<Double> solutions) {
		int i, emptySet, ind;
		double length;
	    double bestLength;
	    //X = set, L = solutions
	    /* respond to requests for already computed results */
	    ind=resultIndex(t, set);
	    if ( solutions.get(ind)>=0 ) {
	    	// solutio
	    	return solutions.get(ind);
	    }

	    /* respond to base case empty set */
	    emptySet=1;
	    i=0;
	    while ( (emptySet == 1) && (i<numberNodes) ) {
	    	if (set.get(i++) == 1){
	    		emptySet = 0;
	        }
	    }
	    
	    if ( emptySet == 1 ) {
	    	//solutions.set(ind, weights.get(t*numberNodes));
	        return weights.get(t*numberNodes);
	    }

	    /* compute remaining case recursively */
	    bestLength=-1;
	    for ( i=0; i<numberNodes; i++) {
	    	if ( set.get(i)==1 ) {
	        	set.set(i, 0); /* take node i out */
	        	
	        	length = weights.get(i+numberNodes*t)+computeLength(i, set, solutions);
	            /* maximal depth of recursion is n-1; since we use memory >n*2^n this is probably okay */
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
	    
}
