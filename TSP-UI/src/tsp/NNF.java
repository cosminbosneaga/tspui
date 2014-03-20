package tsp;

// nearest neighbour first
public class NNF {
	
	static int size = NodeList.size();
	
	public static void findPath(){
		
		// add first node
		
		Tour.addNode(0);
		
		// minimum distance between nodes
		double min = 9999, distance;
		int nextNode = 0;
		for( int i = 0 ; i < size ; i++ ){
			
			min = 9999;
			for( int j = 0 ; j < size ; j++ ){
				distance = Node.edge(NodeList.findNode(i), NodeList.findNode(j));
				
				if( distance != 0 && distance < min && Tour.exists(j)){
					min = distance;
					nextNode = j;
				}
			}
			
			if( Tour.exists(nextNode) && Tour.size() != NodeList.size()){
				Tour.addNode(nextNode);
			}
			
		}
		
	}
	

	
}
