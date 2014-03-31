package tsp;

import java.util.ArrayList;
import java.util.Collections;

public class TwoOpt {

	public static void optimize(){
		
		for(int i=0;i<Tour.size()-1;i++){
			for(int j=i+1;j<Tour.size();j++){
				ArrayList<Integer> testTour = new ArrayList<Integer>(Tour.getTour());
				testTour = interchange(testTour,i,j);
				if( Tour.tourDistance(testTour) < Tour.tourDistance(Tour.getTour())){
					Tour.setTour(testTour);
				}
			}
		}
	}
	
	public static ArrayList<Integer> interchange(ArrayList<Integer> arr, int i, int j){
		int temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
		
		return arr;
	}
	
	
}
