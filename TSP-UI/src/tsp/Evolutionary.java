package tsp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Evolutionary {
	
	private double sigma;
	private DecimalFormat df = new DecimalFormat("#.##");
	private int success, step, size;
	private ArrayList<Double> newPopulation = new ArrayList<Double>();
	private ArrayList<Double> currentPopulation = new ArrayList<Double>();
	//private ArrayList<Double> deviation = new ArrayList<Double>();
	private Instance instance;
	private Tour heuristicTour;
	
	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public Tour getHeuristicTour() {
		return heuristicTour;
	}

	public void setHeuristicTour(Tour heuristicTour) {
		this.heuristicTour = heuristicTour;
	}

	private Tour optimalTour;
	
	/**
	 * @return the optimalTour
	 */
	public Tour getOptimalTour() {
		return optimalTour;
	}

	/**
	 * @param optimalTour the optimalTour to set
	 */
	public void setOptimalTour(Tour optimalTour) {
		this.optimalTour = optimalTour;
	}

	private void printArr(ArrayList<Double> arr){
		System.out.print("[");
		for(int i=0;i<arr.size();i++)
			if(i!=arr.size()-1){
				System.out.print(df.format(arr.get(i))+", ");
			}else{
				System.out.print(df.format(arr.get(i)));
			}
		System.out.print("]\n");
	}

	public void evolveInstance(int size, int mutations){
		
		// step 1, generate random population
		generateCurrentPopulation(size,0,500);
		
		initialize();
		do{
			// step 2+3
			generateNewPopulation();
			
			// step 4
			successful();
			
			//step 5 
			adaptStepSize();
			
			mutations--;
		}while(mutations>0);
		
		
	}
	
	private void initialize(){
		sigma = 1;
		step = 0;
		success = 0;
		size = currentPopulation.size();
	}
	
	private void generateCurrentPopulation(int n,int min, int max){
		currentPopulation = new ArrayList<Double>();
		for(int i=0;i<n*2;i++){
			Random rnd = new Random();
			double var = min + (max-min)*rnd.nextDouble();
			currentPopulation.add(var);
		}
		System.out.println("Generated:");
		printArr(currentPopulation);
	}
	
	/*private void generateDeviations(int n){
		for(int i=0;i<size;i++){
			Random rnd = new Random();
			deviation.add(rnd.nextGaussian());
		}
	}*/
	
	private void generateNewPopulation(){
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
	
	private void successful(){
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
	
	private void adaptStepSize(){
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
	
	private double fitness(ArrayList<Double> population){
		double heuristicLength=0, optimalLength =0;
		
		instance = new Instance();
		for(int i=0;i<size;i+=2){
			//System.out.println(population.get(i)+","+population.get(i+1));
			Node city = new Node(population.get(i), population.get(i+1));
	        instance.addNode(city);
		}
		
		heuristicTour = new Tour();
		setOptimalTour(new Tour());
		
		// Create distance matrix
		DistanceMatrix distances = new DistanceMatrix();
		distances.createMatrix(instance);
		
		// Calculate path using nearest neighbour first method
		NearestNeighbour heuristic = new NearestNeighbour();
		heuristic.findTour(heuristicTour,instance);
		System.out.println(heuristicTour.getTour().toString());
		System.out.println("before:"+heuristicTour.tourTotal(instance));
		LocalSearch ls = new LocalSearch();
		ls.twoOpt(heuristicTour,instance);
		
		System.out.println(heuristicTour.getTour().toString());
		System.out.println("after:"+heuristicTour.tourTotal(instance));
		heuristicLength = heuristicTour.tourTotal(instance);
		
		ArrayList<Integer> set = new ArrayList<Integer>(); //Create an ArrayList
        for(int i=0;i<instance.size();i++){
        	set.add(i);
        }
        set.remove(0);
		//optimal = new Min(1, set).getMin();
        
        DynamicProgramming optimal = new DynamicProgramming();
        
        optimalLength = optimal.findTour(instance,distances);
		//calculate heuristic
		//calculate optimal
		System.out.println("=========FITNESS==========");
		System.out.println("Heuristic:"+df.format(heuristicLength)+
				"\nMinimum:"+df.format(optimalLength)+
				"\nDifference:"+df.format((heuristicLength-optimalLength)));
		return heuristicLength-optimalLength;
	}
	
	private void validateCoordinates(){
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
