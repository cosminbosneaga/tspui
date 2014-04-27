package tsp;

public class DistanceMatrix {
	
	
	public double[][] matrix;
	
	public void createMatrix(Instance tsp){
				
		int size = tsp.size();
		matrix = new double[size][size];
		
		for(int i=0; i< size; i++){
			
			for(int j=0;j<size;j++){
				
				matrix[i][j] = tsp.edge(i,j);
				//System.out.println(matrix[i][j]+" ");
			}
		}
			
	}
	/*
	public static void printMatrix(){
		for(int i=0; i< size; i++){
			System.out.println("\n");
			for(int j=0;j<size;j++){
				
				System.out.format("%.2f%n", matrix[i][j]);
				System.out.print(" ");
			}
		}
			
	}*/

	public double[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	
}
