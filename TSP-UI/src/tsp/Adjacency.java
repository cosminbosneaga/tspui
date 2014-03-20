package tsp;

public class Adjacency {
	
	static int size = NodeList.size();
	static double[][] matrix = new double[size][size];
	
	public static void createMatrix(){
		
		for(int i=0; i< size; i++){
			
			for(int j=0;j<size;j++){
				
				matrix[i][j] = Node.edge(NodeList.findNode(i),NodeList.findNode(j));
			}
		}
			
	}
	
	public static void printMatrix(){
		for(int i=0; i< size; i++){
			System.out.println("\n");
			for(int j=0;j<size;j++){
				
				System.out.format("%.2f%n", matrix[i][j]);
				System.out.print(" ");
			}
		}
			
	}
	
	
}
