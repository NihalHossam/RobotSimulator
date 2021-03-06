
package main.java.robotsimulator;

import java.util.ArrayList;
import java.util.HashMap;

/*Map class represents a grid map of the environment. As soon as 70% of the area has been covered, the search mission 
 * can be terminated*/
public class Map {
	
	//precision of the map grid
	public static int PRECISION = 50/Environment.WORLDSIZE;
	
	//boolean array representing grid
	private boolean[] mapGrid;
	
	//instance of the PositionAdapter
	private PositionAdapter adapter;
	
	/*single object of the Map class*/
	private static Map instance = new Map();
	
	/*Constructor for the class Map*/
	private Map() {
		this.mapGrid = new boolean[Environment.WORLDSIZE*Map.PRECISION * Environment.WORLDSIZE*Map.PRECISION];
		for(int i = 0; i < Environment.WORLDSIZE*Map.PRECISION * Environment.WORLDSIZE*Map.PRECISION; i++){
			mapGrid[i] = false;
		}
	}
	
	/*Retrieving the singleton instance of the Map class*/
	public static Map getInstance() {
		return instance;
	}
	
	/**Updates Grid with robot's searched locations. Returns the percent of searched areas that are newly updated*/
	public double updateGrid(HashMap<Position, double[]> locations) {
		adapter = new PositionAdapter(locations);
		int updatedGridLocations = 0;
		ArrayList<Integer> coveragePatch = new ArrayList<Integer>();
		coveragePatch = adapter.generateCoveragePatch();
		double size = coveragePatch.size();
		while(coveragePatch.size()!= 0){
			int gridValue = coveragePatch.remove(0);
			if(gridValue<=2500 && gridValue>=0){
				if(mapGrid[gridValue] == false) {
					mapGrid[gridValue] = true;
					updatedGridLocations++;
				}
			}
		}
	
	return updatedGridLocations/size;
	}

	/** Calculates the percentage of the map that has been searched*/
	public double getCoveragePercentage() {
		int searchedGridLocations = 0;
		for(int i=0; i<Environment.WORLDSIZE*Map.PRECISION * Environment.WORLDSIZE*Map.PRECISION; i++){
			if(mapGrid[i] == true){
				searchedGridLocations++;
			}
		}
	return (double)searchedGridLocations/(Environment.WORLDSIZE*Map.PRECISION * Environment.WORLDSIZE*Map.PRECISION);
	}

	/**Clears map for a new search*/
	public void resetMap() {
		for(int i=0; i<Environment.WORLDSIZE*Map.PRECISION * Environment.WORLDSIZE*Map.PRECISION; i++){
			mapGrid[i] = false;
		}
	}
	
	/*determines area in the environment which has not been yet covered by robots*/
	public Direction findUnsearchedArea(){
		int biggestGridCounter = 0;
		int freeGridCounter = 0;
		for(int i = 0; i < 1250; i += 50){
			for(int j = 0; j <= 24; j++){
				if(mapGrid[i+j]== false)
					freeGridCounter++;
			}
		}
		biggestGridCounter = freeGridCounter;
		Direction newSearchDirection = Direction.SOUTH_EAST;
		freeGridCounter = 0;
		
		for(int i = 0; i < 1250; i += 50){
			for(int j = 25; j <= 49; j++){
				if(mapGrid[i+j]== false)
					freeGridCounter++;
			}
		}
		if(freeGridCounter > biggestGridCounter)
			newSearchDirection = Direction.SOUTH_WEST;
		freeGridCounter = 0;
		
		for(int i = 1300; i < 2500; i += 50){
			for(int j = 0; j <= 24; j++){
				if(mapGrid[i+j]== false)
					freeGridCounter++;
			}
		}
		if(freeGridCounter > biggestGridCounter)
			newSearchDirection = Direction.NORTH_EAST;
		freeGridCounter = 0;
		
		for(int i = 1300; i < 2500; i += 50){
			for(int j = 25; j <= 49; j++){
				if(mapGrid[i+j]== false)
					freeGridCounter++;
			}
		}
		if(freeGridCounter > biggestGridCounter)
			newSearchDirection = Direction.NORTH_WEST;
		
		return newSearchDirection;
	}
};