package tsp;

public class TwoOpt {

	public static void optimize(Tour heuristic){
		
		for(int i=1;i<heuristic.size()-2;i++){
			for(int j=i+1;j<heuristic.size()-1;j++){
				Tour testTour = new Tour();
				testTour.setTour(heuristic.getTour());
				testTour.interchange(i,j);
				if( heuristic.tourTotal() < testTour.tourTotal() ){
					heuristic.setTour(testTour.getTour());
				}
			}
		}
	}
	
}
