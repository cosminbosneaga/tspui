package tsp;

public class LocalSearch {

	public void twoOpt(Tour heuristic,Instance tsp){
		
		for(int i=1;i<heuristic.size()-2;i++){
			for(int j=i+1;j<heuristic.size()-1;j++){
				Tour testTour = new Tour();
				testTour.setTour(heuristic.getTour());
				testTour.interchange(i,j);
				if( heuristic.tourTotal(tsp) < testTour.tourTotal(tsp) ){
					heuristic.setTour(testTour.getTour());
				}
			}
		}
	}
	
}
