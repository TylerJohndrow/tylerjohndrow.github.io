import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


/**
 * This class is used to create a random walk location.
 * @author Tyler Johndrow
 */
public class RandomWalk implements RandomWalkInterface 
{
	//Initializing the variables
	private Long seed;
	private int gridSize;
	private Random rand;
	private Point start;
	private Point end;
	private int stepX;
	private int stepY;
	@SuppressWarnings("unused")
	private Point p1 = new Point(stepX, stepY);
	private ArrayList<Point> path = new ArrayList<Point>();
	private Boolean done = false;
	
	/**
	 * Creates a random walk with a seed value
	 * @param gridSize
	 * @param seed
	 */
	public RandomWalk(int gridSize, long seed) 
	{
		this.gridSize = gridSize;
		this.seed = seed;
		start = new Point(0, (gridSize - 1));
		end = new Point((gridSize-1), 0);
		stepX = start.x;
		stepY = start.y;
		rand = new Random(seed);
		path.add(start);
	}
	
/**
 * Creates a random walk with no seed value
 * @param gridSize
 */
	//Creating an object with no seed value
	public RandomWalk(int gridSize) 
	{
		this.gridSize = gridSize;
		start = new Point(0, (gridSize - 1));
		end = new Point((gridSize-1), 0);
		stepX = start.x;
		stepY = start.y ;
		rand = new Random();
		path.add(start);
	}
	
	//Creates one step for the object
	public void step() {
		
		int i = rand.nextInt(2);
		if(i == 0) 
		{
		if(stepX < end.x) 
			{
			stepX = stepX + 1;
			}
		}
		else if (i == 1) 
		 if(stepY > end.y) 
		 {
			{
				stepY = stepY - 1;
			}
		 }
		Point p1 = new Point(stepX, stepY);
		
		if(!path.contains(p1)) {
			path.add(p1);
		}
		
		//path.add(p1);
		if(p1.equals(end)) 
		{
			done = true;
		}
	}


	//Creates multiple step until it reaches the end goal.
	public void createWalk() {
		// TODO Auto-generated method stub
		while(done != true) 
		{
		step();
		}
	}


	//Returns the value of boolean done
	public boolean isDone() {
		// TODO Auto-generated method stub
		return done;
	}

	//Gets the size of the grid
	public int getGridSize() {
		// TODO Auto-generated method stub
		return gridSize;
	}

	//Gets the start point for the grid
	public Point getStartPoint() {
		// TODO Auto-generated method stub
		return start;
	}

	//Gets the end point for the grid
	public Point getEndPoint() {
		// TODO Auto-generated method stub
		return end;
	}

	//Gets the current point with in the array
	public Point getCurrentPoint() {
		// TODO Auto-generated method stub
		return path.get(path.size() - 1);
	}

	//Returns the value of the array
	public ArrayList<Point> getPath() {
		// TODO Auto-generated method stub
		
		return path;
	}
	//Tostring for object
	public String toString()
	{
		String toString = "";
		
		for(int i = 0; i < path.size(); i++) 
		{
			toString += "[" + path.get(i).x + ", " + path.get(i).y + "] " ;
		}
		return toString;
	}
	
	//Getter and setter for grid size and seed

	public void setgridSize(int gridSize) 
	{
		this.gridSize = gridSize;
	}
	public Long getSeed() 
	{
		return seed;
	}
	public void setSeed(long Seed) 
	{
		this.seed = Seed;
	}

}
