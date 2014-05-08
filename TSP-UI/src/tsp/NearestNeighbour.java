package tsp;

// nearest neighbour first
public class NearestNeighbour {
	
	
	public void findTour(Tour heuristic,  Instance tsp){
		
		int size = tsp.size();
		// add first node		
		heuristic.addNode(0);
		
		// minimum distance between nodes
		double min = 9999, distance;
		int nextNode = 0, tobeNode=0;
		for( int i = 0 ; i < size ; i++ ){
			
			min = 9999;
			for( int j = 0 ; j < size ; j++ ){
				distance = tsp.edge(nextNode, j);
				
				if( distance != 0 && distance < min && !heuristic.exists(j)){
					min = distance;
					tobeNode = j;
				}
			}
			
			if( !heuristic.exists(tobeNode) && heuristic.size() != tsp.size()){
				nextNode = tobeNode;
				heuristic.addNode(nextNode);
			}
			
		}
		
		// return to first node
		heuristic.addNode(0);
	}
	

	
}
