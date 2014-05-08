package tsp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	BufferedWriter file;
	BufferedWriter fit;
	
	private void preMade(Integer difficulty){
		Double[] n80 = new Double[]{88.92, 114.81, 102.11, 210.22, 197.88, 360.37, 296.51, 282.61, 423.98, 411.49, 293.00, 215.09, 407.83, 345.77, 333.28, 474.7};
		Double[] n8125 = new Double[]{153.59, 239.01, 278.08, 235.67, 223.86, 379.86, 382.31, 342.24, 358.03, 329.62, 351.22, 463.71, 499.19, 498.62, 470.17, 415.48};
		Double[] n8250 = new Double[]{495.63, 159.38, 436.89, 420.43, 497.73, 439.84, 95.77, 36.32, 22.38, 188.88, 169.52, 315.93, 428.27, 200.69, 268.14, 82.75};
		Double[] n8500 = new Double[]{40.22, 27.23, 469.35, 371.53, 426.02, 449.63, 395.18, 49.84, 70.48, 22.56, 20.05, 56.12, 335.16, 212.6, 500.00, 499.67};
		
		Double[] n120 = new Double[]{387.68, 327.14, 445.9, 435.09, 408.15, 54.64, 43.87, 491.77, 479.98, 404.79, 350.63, 478.24, 449.77, 94.68, 84.25, 58.51, 47.75, 167.87, 156.04, 53.83, 26.73, 481.77, 471.17, 143.15};
		Double[] n12125 = new Double[]{365.48, 430.83, 462.1, 500.00, 495.55, 408.15, 445.96, 115.64, 110.24, 110.04, 79.29, 128.46, 82.54, 90.18, 91.02, 47.64, 32.47, 201.14, 199.13, 242.74, 252.79, 163.09, 182.75, 178.38};
		Double[] n12250 = new Double[]{264.99, 117.13, 84.41, 194.88, 158.77, 192.04, 292.21, 263.94, 312.52, 279.83, 419.94, 342.71, 366.13, 259.46, 219.00, 242.28, 360.61, 354.74, 346.01, 499.2, 498.27, 469.51, 437.8, 412.06};
		Double[] n12500 = new Double[]{445.03, 93.57, 33.03, 54.81, 27.81, 191.63, 177.51, 165.87, 170.48, 139.75, 113.17, 147.77, 111.9, 298.88, 268.97, 244.89, 236.75, 245.78, 290.33, 249.66, 217.78, 407.97, 398.84, 356.63};
		
		Double[] n150 = new Double[]{81.58, 284.74, 256.03, 258.88, 272.08, 272.6, 246.02, 392.35, 392.92, 363.94, 380.35, 316.71, 319.46, 333.74, 480.83, 480.01, 453.17, 425.74, 426.42, 443.76, 413.83, 415.61, 86.36, 86.3, 59.74, 77.3, 76.03, 48.43, 496.62, 496.01};
		Double[] n15125 = new Double[]{127.23, 60.25, 466.74, 483.87, 481.48, 455.24, 456.07, 434.9, 425.25, 396.12, 399.22, 80.97, 84.77, 49.91, 58.58, 27.17, 30.72, 47.16, 499.27, 499.49, 499.33, 172.93, 173.01, 142.45, 146.56, 153.95, 132.86, 137.33, 108.37, 112.43};
		Double[] n15250 = new Double[]{500.00, 104.82, 84.66, 84.96, 89.45, 93.44, 64.37, 241.05, 234.09, 217.51, 228.24, 219.65, 223.33, 282.83, 211.43, 200.82, 306.92, 337.61, 293.09, 294.24, 349.99, 299.15, 333.73, 266.36, 285.1, 218.44, 238.45, 122.71, 122.74, 73.87};
		Double[] n15500 = new Double[]{449.49, 212.94, 157.51, 277.02, 255.91, 197.23, 314.94, 293.01, 222.71, 170.16, 160.8, 398.49, 405.31, 489.71, 102.26, 88.06, 470.66, 115.37, 91.76, 144.55, 293.85, 267.08, 402.91, 351.89, 271.61, 234.12, 351.47, 464.77, 107.68, 59.61};
		switch(difficulty){
		case 80: this.currentPopulation.addAll(Arrays.asList(n80)); break;
		case 8125: this.currentPopulation.addAll(Arrays.asList(n8125)); break;
		case 8250: this.currentPopulation.addAll(Arrays.asList(n8250)); break;
		case 8500: this.currentPopulation.addAll(Arrays.asList(n8500)); break;
		case 120: this.currentPopulation.addAll(Arrays.asList(n120)); break;
		case 12125: this.currentPopulation.addAll(Arrays.asList(n12125)); break;
		case 12250: this.currentPopulation.addAll(Arrays.asList(n12250)); break;
		case 12500: this.currentPopulation.addAll(Arrays.asList(n12500)); break;
		case 150: this.currentPopulation.addAll(Arrays.asList(n150)); break;
		case 15125: this.currentPopulation.addAll(Arrays.asList(n15125)); break;
		case 15250: this.currentPopulation.addAll(Arrays.asList(n15250)); break;
		case 15500: this.currentPopulation.addAll(Arrays.asList(n15500)); break;
		default: break;
		}
		
		//this.currentPopulation.addAll(Arrays.asList(n15500));
	
		instance = new Instance();
		for(int i=0;i<currentPopulation.size();i+=2){
			//System.out.println(population.get(i)+","+population.get(i+1));
			Node city = new Node(currentPopulation.get(i), currentPopulation.get(i+1));
	        instance.addNode(city);
		}
	}
		
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

	private void printArr(ArrayList<Double> arr) throws IOException{
		file.write("[" );
		System.out.print("[");
		for(int i=0;i<arr.size();i++)
			if(i!=arr.size()-1){
				file.write(df.format(arr.get(i))+", ");
				System.out.print(df.format(arr.get(i))+", ");
			}else{
				file.write(df.format(arr.get(i)));
				System.out.print(df.format(arr.get(i)));
			}
		file.write("]" + System.getProperty( "line.separator") );
		System.out.print("]\n");
	}

	public void evolveInstance(int size, int mutations) throws IOException{
		
		fit = new BufferedWriter(new FileWriter("C:\\fitness.csv"));
		file = new BufferedWriter(new FileWriter("C:\\example.txt"));
		final long startTime = System.nanoTime();
		
		preMade(size);
		
		
		// step 1, generate random population
		//generateCurrentPopulation(size,25,500);
		
		initialize();
		do{
			file.write(">>>>>>>>Step " + mutations + "<<<<<<<<<" + System.getProperty( "line.separator") );
			// step 2+3
			generateNewPopulation();
			
			// step 4
			successful();
			
			//step 5 
			adaptStepSize();
			
			mutations--;
		}while(mutations>0);
		
		file.flush();
		file.close();
		fit.flush();
		fit.close();
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration + " " + duration/1000000000.0);
	}
	
	private void initialize(){
		sigma = 1;
		step = 0;
		success = 0;
		size = currentPopulation.size();
	}
	
	private void generateCurrentPopulation(int n,int min, int max) throws IOException{
		currentPopulation = new ArrayList<Double>();
		for(int i=0;i<n*2;i++){
			Random rnd = new Random();
			double var = min + (max-min)*rnd.nextDouble();
			currentPopulation.add(var);
		}
		file.write("Generated:");
		System.out.println("Generated:");
		printArr(currentPopulation);
	}
	
	/*private void generateDeviations(int n){
		for(int i=0;i<size;i++){
			Random rnd = new Random();
			deviation.add(rnd.nextGaussian());
		}
	}*/
	
	private void generateNewPopulation() throws IOException{
		for(int i=0;i<size;i++){
			Random rnd = new Random();
			double deviation = rnd.nextGaussian();
			newPopulation.add(currentPopulation.get(i)+deviation*sigma);
		}
		step++;
		validateCoordinates();
		file.write("Deviated:");
		System.out.println("Deviated:");
		printArr(newPopulation);
	}
	
	private void successful() throws IOException{
		file.write("--Current Fitness--"  + System.getProperty( "line.separator") );
		double currentFitness = fitness(currentPopulation);
		file.write("--New Fitness--" + System.getProperty( "line.separator") );
		double newFitness = fitness(newPopulation);
		
		if( currentFitness <= newFitness ){
			//copy newPopulation as current
			currentPopulation = new ArrayList<Double>(newPopulation);
			newPopulation = new ArrayList<Double>();
			file.write("Fitness of new population is better!" + System.getProperty( "line.separator") );
			System.out.println("Fitness of new population is better!");
			success++;
			fit.append(df.format(newFitness));
			fit.append( System.getProperty( "line.separator") );	
		}else{
			newPopulation = new ArrayList<Double>();
		}
	}
	
	private void adaptStepSize() throws IOException{
		if( step == size ){
			step = 0;
			if( (double)success/size > 0.2 ){
				sigma*=2;
				file.write("Step size doubled!" + System.getProperty( "line.separator") );
				System.out.println("Step size doubled!");
			}
			else{
				sigma/=2;
				file.write("Step size halved!" + System.getProperty( "line.separator") );
				System.out.println("Step size halved!");
			}
			success = 0;
		}
	}
	
	private void createInstance(ArrayList<Double> population){
		instance = new Instance();
		for(int i=0;i<size;i+=2){
			//System.out.println(population.get(i)+","+population.get(i+1));
			Node city = new Node(population.get(i), population.get(i+1));
	        instance.addNode(city);
		}
	}
	
	private double fitness(ArrayList<Double> population) throws IOException{
		double heuristicLength=0, optimalLength =0;
		
		createInstance(population);
		
		heuristicTour = new Tour();
		setOptimalTour(new Tour());
		
		// Create distance matrix
		DistanceMatrix distances = new DistanceMatrix();
		distances.createMatrix(instance);
		
		// Calculate path using nearest neighbour first method
		NearestNeighbour heuristic = new NearestNeighbour();
		heuristic.findTour(heuristicTour,instance);
		//file.write("NN:" + heuristicTour.getTour().toString() + System.getProperty( "line.separator") );
		System.out.println(heuristicTour.getTour().toString());
		file.write("NN:"+df.format(heuristicTour.tourTotal(instance)) + System.getProperty( "line.separator") );
		System.out.println("NN:"+heuristicTour.tourTotal(instance));
		LocalSearch ls = new LocalSearch();
		//ls.twoOpt(heuristicTour,instance);
		
		
		file.write("NN:" + heuristicTour.getTour().toString() + System.getProperty( "line.separator") );
		
		System.out.println(heuristicTour.getTour().toString());
		file.write("2Opt:" + df.format(heuristicTour.tourTotal(instance)) + System.getProperty( "line.separator") );
		file.write("2Opt:" + heuristicTour.getTour().toString() + System.getProperty( "line.separator") );
		System.out.println("2Opt:"+heuristicTour.tourTotal(instance));
		heuristicLength = heuristicTour.tourTotal(instance);
		
		ArrayList<Integer> set = new ArrayList<Integer>(); //Create an ArrayList
        for(int i=0;i<instance.size();i++){
        	set.add(i);
        }
        set.remove(0);
		//optimal = new Min(1, set).getMin();
        
        DynamicProgramming optimal = new DynamicProgramming();
        
        
        optimal.findTour(instance,distances,optimalTour);
        optimalLength = optimalTour.tourTotal(instance); 
        		
        file.write("Optimal:" + df.format(optimalLength) + System.getProperty( "line.separator") );
        file.write("Optimal:" + optimalTour.getTour().toString() + System.getProperty( "line.separator") );
        System.out.println(optimalTour.getTour().toString());
		//calculate heuristic
		//calculate optimal
		System.out.println("=========FITNESS==========");
		file.write("Heuristic:"+df.format(heuristicLength) + System.getProperty( "line.separator") +
				"Minimum:"+df.format(optimalLength) + System.getProperty( "line.separator") +
				"Difference:"+df.format((heuristicLength-optimalLength)) + System.getProperty( "line.separator") );
		System.out.println("Heuristic:"+df.format(heuristicLength)+
				"\nMinimum:"+df.format(optimalLength)+
				"\nDifference:"+df.format((heuristicLength-optimalLength)));
		return heuristicLength-optimalLength;
	}
	
	private void validateCoordinates() throws IOException{
		// Fix negative coordinates
		// Assume all coordinates are valid
		boolean valid = true;
		double minx=20, miny=20;
		for(int i=0;i<size;i++){
			if( newPopulation.get(i) < 20 ){
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
					newPopulation.set(i,newPopulation.get(i)-minx+20);
				}else{
					newPopulation.set(i,newPopulation.get(i)-miny+20);
				}
			}
			file.write("Fixed negative coords!" + System.getProperty( "line.separator") );
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
			file.write("Repositioned!" + System.getProperty( "line.separator") );
			System.out.println("Repositioned!");
		}
		
	}
	

}
