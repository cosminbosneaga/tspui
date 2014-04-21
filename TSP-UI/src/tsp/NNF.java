package tsp;

// nearest neighbour first
public class NNF {
	
	static int size = Instance.size();
	
	public static void findPath(Tour heuristic){
		
		// add first node		
		heuristic.addNode(0);
		
		// minimum distance between nodes
		double min = 9999, distance;
		int nextNode = 0;
		for( int i = 0 ; i < size ; i++ ){
			
			min = 9999;
			for( int j = 0 ; j < size ; j++ ){
				distance = Instance.edge(i, j);
				
				if( distance != 0 && distance < min && heuristic.exists(j)){
					min = distance;
					nextNode = j;
				}
			}
			
			if( heuristic.exists(nextNode) && heuristic.size() != Instance.size()){
				heuristic.addNode(nextNode);
			}
			
		}
		
		// return to first node
		heuristic.addNode(0);
	}
	

	
}
