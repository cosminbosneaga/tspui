package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class LocalSearch {

	public void twoOpt(Tour heuristic,Instance tsp){
		ArrayList<Integer> reverseTour;
		Tour testTour;
		for(int i=1;i<heuristic.size()-2;i++){
			for(int j=i+1;j<heuristic.size()-1;j++){
				testTour = new Tour(heuristic.getTour());
				//testTour.setTour(heuristic.getTour());
				testTour.interchange(i,j);
				reverseTour = new ArrayList<Integer>(testTour.getTour());
				Collections.reverse(reverseTour);
				if( (testTour.tourTotal(tsp) < heuristic.tourTotal(tsp)) && ( compare(heuristic.getTour(),testTour.getTour(),reverseTour) ) ){
					heuristic.setTour(testTour.getTour());i=1;j=i+1;
					System.out.println("2o");
				} 
			}
		}
	}
	
	// compare array 1 to be different by array 2 and 3
	private boolean compare(ArrayList<Integer> array1,ArrayList<Integer> array2, ArrayList<Integer> array3){
		for(int i=0;i<array1.size();i++){
			if(array1.get(i) == array2.get(i) || array1.get(i) == array3.get(i)){
				return false;
			}
		}
		return true;
	}
	
}
