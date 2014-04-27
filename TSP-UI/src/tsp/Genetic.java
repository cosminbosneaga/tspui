package tsp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Genetic {
	
	private static double sigma;
	private static DecimalFormat df = new DecimalFormat("#.##");
	private static int success, step, size;
	private static ArrayList<Double> newPopulation = new ArrayList<Double>();
	private static ArrayList<Double> currentPopulation = new ArrayList<Double>();
	private static ArrayList<Double> deviation = new ArrayList<Double>();
	
	public static void printArr(ArrayList<Double> arr){
		System.out.print("[");
		for(int i=0;i<arr.size();i++)
			if(i!=arr.size()-1){
				System.out.print(df.format(arr.get(i))+", ");
			}else{
				System.out.print(df.format(arr.get(i)));
			}
		System.out.print("]\n");
	}

	public static void evolutionary(int size, int mutations){
		
		// step 1, generate random population
		Genetic.generateCurrentPopulation(size,0,500);
		
		initialize();
		do{
			// step 2+3
			Genetic.generateNewPopulation();
			
			// step 4
			successful();
			
			//step 5 
			adaptStepSize();
			
			mutations--;
		}while(mutations>0);
		
		
	}
	
	public static void initialize(){
		sigma = 1;
		step = 0;
		success = 0;
		size = currentPopulation.size();
	}
	
	public static void generateCurrentPopulation(int n,int min, int max){
		currentPopulation = new ArrayList<Double>();
		for(int i=0;i<n*2;i++){
			Random rnd = new Random();
			double var = min + (max-min)*rnd.nextDouble();
			currentPopulation.add(var);
		}
		System.out.println("Generated:");
		printArr(currentPopulation);
	}
	
	public static void generateDeviations(int n){
		for(int i=0;i<size;i++){
			Random rnd = new Random();
			deviation.add(rnd.nextGaussian());
		}
	}
	
	public static void generateNewPopulation(){
		for(int i=0;i<size;i++){
			Random rnd = new Random();
			double deviation = rnd.nextGaussian();
			newPopulation.add(currentPopulation.get(i)+deviation*sigma);
		}
		step++;
		validateCoordinates();
		System.out.println("Deviated:");
		printArr(newPopulation);
	}
	
	public static void successful(){
		if( fitness(currentPopulation) <= fitness(newPopulation) ){
			//copy newPopulation as current
			currentPopulation = new ArrayList<Double>(newPopulation);
			newPopulation = new ArrayList<Double>();
			System.out.println("Fitness of new population is better!");
			success++;
		}else{
			newPopulation = new ArrayList<Double>();
		}
	}
	
	public static void adaptStepSize(){
		if( step == size ){
			step = 0;
			if( (double)success/size > 0.2 ){
				sigma*=2;
				System.out.println("Step size doubled!");
			}
			else{
				sigma/=2;
				System.out.println("Step size halved!");
			}
			success = 0;
		}
	}
	
	public static double fitness(ArrayList<Double> population){
		double heuristic=0, optimal =0;
		
		Instance.removeAll();
		for(int i=0;i<size;i+=2){
			//System.out.println(population.get(i)+","+population.get(i+1));
			Node city = new Node(population.get(i), population.get(i+1));
	        Instance.addNode(city);
		}
		Tour heuristicTour = new Tour();
		Tour exactTour = new Tour();
		// Create distance matrix
		Adjacency.createMatrix();
		// Calculate path using nearest neighbour first method
		NNF.findPath(heuristicTour);
		System.out.println(heuristicTour.getTour().toString());
		System.out.println("before:"+heuristicTour.tourTotal());
		TwoOpt.optimize(heuristicTour);
		System.out.println(heuristicTour.getTour().toString());
		System.out.println("after:"+heuristicTour.tourTotal());
		heuristic = heuristicTour.tourTotal();
		
		ArrayList<Integer> set = new ArrayList<Integer>(); //Create an ArrayList
        for(int i=0;i<Instance.size();i++){
        	set.add(i);
        }
        set.remove(0);
		//optimal = new Min(1, set).getMin();
        optimal = Dynamic.solveInstanceDP();
		//calculate heuristic
		//calculate optimal
		System.out.println("=========FITNESS==========");
		System.out.println("Heuristic:"+df.format(heuristic)+
				"\nMinimum:"+df.format(optimal)+
				"\nDifference:"+df.format((heuristic-optimal)));
		return heuristic-optimal;
	}
	
	public static void validateCoordinates(){
		// Fix negative coordinates
		// Assume all coordinates are valid
		boolean valid = true;
		double minx=0, miny=0;
		for(int i=0;i<size;i++){
			if( newPopulation.get(i) < 0 ){
				valid = false;
				if(i%2==0 && newPopulation.get(i)<minx){
					minx = newPopulation.get(i);
				}else if(i%2==1 && newPopulation.get(i)<miny){
					miny = newPopulation.get(i);
				}
			}
		}
		
		if(valid == false){
			for(int i=0;i<size;i++){
				if(i%2==0){
					newPopulation.set(i,newPopulation.get(i)-minx);
				}else{
					newPopulation.set(i,newPopulation.get(i)-miny);
				}
			}
			System.out.println("Fixed negative coords!");
		}
		
		// Fix large coordinates
		valid = true;
		double maxx=500, maxy=500;
		for(int i=0;i<size;i++){
			if( i%2==0 && newPopulation.get(i)>maxx){
				valid = false;
				maxx = newPopulation.get(i);
			}else if(i%2==1 && newPopulation.get(i)>maxy){
				valid = false;
				maxy = newPopulation.get(i);
			}
		}
		
		if(valid == false){
			for(int i=0;i<size;i++){
				if(i%2==0){
					newPopulation.set(i, (newPopulation.get(i)*(double)(500/maxx)));
				}else{
					newPopulation.set(i, (newPopulation.get(i)*(double)(500/maxy)));
				}
			}
			System.out.println("Repositioned!");
		}
		
	}
	

}
