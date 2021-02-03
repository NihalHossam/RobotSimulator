
package main.java.robotsimulator;
import java.awt.Color;
import java.util.ArrayList;
import javax.vecmath.Vector3d;

/************************************************************/
/* Central Station class is responsible for managing the search mission. It controls mission execution, communicates with robots,
 * terminates mission when robots have covered pre-defined percentage of the environment, maintains the internal list of active robots 
 * and helps stuck robots by giving them new directions */
public class CentralStation {
	
	/*percentage of the new area covered compared to all collected positions in the environment
	 *  which was covered by the robot. The robot sends the report containing its covered positions and 
	 *  CentralStation can determine if it is stuck in the environment (in this case less than 30% of all covered 
	 *  points are new)
	 *  */
	private static double MINSATISFACTORYPROGRESS = 0.3;
	
	/*list for manipulating and controlling robot instances*/
	private ArrayList<Robot> robots = new ArrayList<Robot>();
	
	/*singleton instance of the map class*/
	private Map map;
	
	/*minimal amount of covered environment in percentage to determine the mission completion*/
	private static double PERCENTGOAL = 0.7;
	
	/*singleton instance for Central Station class*/
	private static CentralStation instance = new CentralStation();
	
	/*list for manipulating and controlling task instances*/
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	/*instance of Album to store found target box photographs and its locations*/
	private Album album;
	
	/*private constructor for Central Station class as an implementation
	 * of Singleton design pattern*/
	private CentralStation() {
	}

	/**Retrieves the singleton instance*/
	public static CentralStation getInstance() {
		return instance;
	}
	
	/*sets up all necessary attributes for performing search mission:
	 * 4 robot instances are created and added to the environment, 
	 * map, album instances are initialized*/
	public void init() {
		Environment environment = Environment.getInstance();
		map = Map.getInstance();
		album = new Album();
		Robot robot1 = new Robot("Robot 1", new Vector3d(0, 0, 2), Direction.WEST);
		environment.add(robot1);
		robots.add(robot1);
		
		Robot robot2 = new Robot("Robot 2", new Vector3d(0, 0, -2), Direction.EAST);
		environment.add(robot2);
		this.robots.add(robot2);
		
		Robot robot3 = new Robot("Robot 3", new Vector3d(2, 0, 0), Direction.SOUTH);
		environment.add(robot3);
		this.robots.add(robot3);
		
		Robot robot4 = new Robot("Robot 4", new Vector3d(-2, 0, 0), Direction.NORTH);
		environment.add(robot4);
		this.robots.add(robot4);
	}
	
	/**Sets the received color as the target box color and attaches robots as observers to task object.
	 *If Central Station receives one target color, all robots are attached to one task object.
	 *If Central Station receives two target colors, robots are divided into two groups and attached to
	 *two different tasks corresponding to two different target colors*/
	public void startSearch(ArrayList<Color> boxColors) {
		if(boxColors.size() == 1){
			Color taskColor = boxColors.get(0);
			Task newTask0 = new Task(taskColor);
			for(int i = 0; i < robots.size(); i++){		
				robots.get(i).receiveTask(newTask0);
			}
			newTask0.setTaskStatus(TaskStatus.START);
			this.tasks.add(newTask0);
		}
		else if(boxColors.size() == 2){
				Color taskColor0 = boxColors.get(0);
				Task newTask0 = new Task(taskColor0);
				Color taskColor1 = boxColors.get(1);
				Task newTask1 = new Task(taskColor1); 
				robots.get(0).receiveTask(newTask0);
				robots.get(1).receiveTask(newTask0);
				robots.get(2).receiveTask(newTask1);
				robots.get(3).receiveTask(newTask1);
				newTask0.setTaskStatus(TaskStatus.START);
				this.tasks.add(newTask0);
				newTask1.setTaskStatus(TaskStatus.START);
				this.tasks.add(newTask1);
		}
	}

	/**Retrieves whether the area of the environment which has been searched by the 
	 * robots exceeds the threshold determined in the beginning of the mission.*/
	private boolean isSearchComplete() {
		if(map.getCoveragePercentage() >= PERCENTGOAL){
			return true;
		}
		return false;
	}

	/**Retrieves the percentage of the environment that has been searched by the 
	 * robots at the current moment of time*/
	public String getSearchProgress() {
		return "The current search progress is " + Double.toString(map.getCoveragePercentage());
	}

	/** Handles a message containing the search results received from the 
	 * robot by routing to the relevant internal handler: map message, box message or error message*/
	public void handleMessage(Message Message) {
		if(Message instanceof BoxMessage){
			handleBoxMessage((BoxMessage)Message);
		}
		else if(Message instanceof MapMessage){
			handleMapMessage((MapMessage)Message);
		}
		else if(Message instanceof ErrorMessage){
			handleErrorMessage((ErrorMessage)Message);
		}
	}

	/** Unpacks a box message and stores a photograph of the discovered box in the album.*/
	private void handleBoxMessage(BoxMessage boxmessage) {
		album.addPhoto(boxmessage.getBoxLocation(), boxmessage.getBoxPhoto());
	}

	/**Unpacks a map message and updates the map grid with the newly searched coordinates*/
	private void handleMapMessage(MapMessage mapmessage) {
		double updatedGrids = map.updateGrid(mapmessage.getSearchedAreas());
		System.out.println(this.getSearchProgress());
		if(!updateMissionCompletion()){
			checkRobotProgress(updatedGrids, mapmessage.getRobotID());
		}			
	}
	
	/*Terminates the mission if the robots have covered at least 70% of the environment by changing the status in the
	 * Task object. Returns true if the mission has been terminated. If mission has not been terminated, returns false*/
	private boolean updateMissionCompletion() {
		if(isSearchComplete()) {
			if(album.returnPhotoCount() > 0){
				for(Task task : tasks){
					task.setTaskStatus(TaskStatus.COMPLETED);
				}
				System.out.println("The search has completed, see the result in the same folder.");
				album.exportPhotos();
			}
			else{
				for(Task task : tasks){
					task.setTaskStatus(TaskStatus.FAILED);
				}
				System.out.println("The search has failed. There are no search results.");
			}
			map.resetMap();
		}
		else{
			return false;
		}
		return true;
	}
	
	/*Determines if the robot is stuck in the environment and giving to it the new directions if needed*/
	private void checkRobotProgress(double updatedGrids, String robotID){
		if(updatedGrids < MINSATISFACTORYPROGRESS){
			for(int i = 0; i < robots.size(); i++ ){
				if(robotID.equals(robots.get(i).getName())){
					robots.get(i).setOrderedDirection(map.findUnsearchedArea());
				}
			}
		}
	}

	/**Unpacks an error message and evicts the broken robot from the pool of active robots*/
	private void handleErrorMessage(ErrorMessage errormessage) {
		System.out.println(errormessage.getMessageDescription());
		for(int i = 0; i < robots.size(); i++ ){
			if(errormessage.getRobotID().equals(robots.get(i).getName())){
				robots.remove(i);
			}
		}
	}
};
