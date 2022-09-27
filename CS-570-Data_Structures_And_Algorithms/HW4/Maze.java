package Maze;

import java.util.ArrayList;
import java.util.Stack;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
    	return findMazePath(0, 0); 
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    public boolean findMazePath(int x, int y) {
        
    	/* Check if the current value of a cell is out of grid or not  . If it is out of bound return false. Also check if
    	 *  the maze background color is red or not. If not return false	
    	 */
    	if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) != NON_BACKGROUND)
    		return false;
    	
        //Check if we have reached at the final point or not. If yes color the cells to green and return true
    	else if(x == maze.getNCols() - 1 && y == maze.getNRows() - 1) 
     	{
     			maze.recolor(x,y,PATH);
     			return true;
     	} 
     
    	
        /*Current cell is not the final point and the current cell has NON_BACKGROUND color i.e red. Hence we will use
         *  recursion to find the possible paths. 
         */
        else 
        {
            maze.recolor(x, y, PATH);
            
            //Use the recursion to find the possible paths
            if (findMazePath(x+1,y) || findMazePath(x-1,y) || findMazePath(x,y+1) || findMazePath(x,y-1))
				return true;

            // There is no path so we will mark the current cell value with the background color as black and we will return false
            else 
			{
				maze.recolor(x,y,TEMPORARY);
				return false;
			}
        }
    	
    } 
        
    public void findMazePathStackBased(int x, int y, ArrayList < ArrayList< PairInt > > result, Stack < PairInt > trace)
    {
    	/* Check if the current value of a cell is out of grid or not  . If it is out of bound return false. Also check if the maze
    	 *  background color is red or not. If not return false	
    	 */
    	if(x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) != NON_BACKGROUND)
    		return;
    	
        /*We have found the path. Push the values of x & y in the stack and copy the contents of the stack into an array list.
         * After that remove the value of x & y from the stack and recolor the background color to red so that the cell 
         * can be visited again
         */
    	
        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) 
        {
        	
        	PairInt temp = new PairInt(x,y);
            trace.push(temp);
            ArrayList < PairInt > path = new ArrayList < >();
            path.addAll(trace);
            result.add(path);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
    	
    	/* We haven't found the path yet. We will use the recursive calls to find the path. Copying the value 
    	 * of x & y in the stack and making recursive calls for all the possible values of x & y.
    	 * Once the recursive calls are completed then we will pop the stack and recolor the background color
    	 * of x & y to red i.e. NON_BACKGROUND color so that the cell can be visited again
         */
        else
        {	
        	
        	maze.recolor(x, y, PATH);
        	PairInt temp = new PairInt(x,y);
        	trace.push(temp);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
    	
    }
    
    
    public ArrayList < ArrayList < PairInt > > findAllMazePaths ( int x , int y) 
    {
    	ArrayList < ArrayList < PairInt > > result = new ArrayList < >();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased (0 ,0 , result , trace );
    	return result ;	
    }
    

    //Finding the minimum path among all the possible paths
    public ArrayList < PairInt > findMazePathMin(int x, int y)
    {
    	//Storing all the possible paths
    	ArrayList < ArrayList < PairInt > > results = findAllMazePaths(x, y);
    	
    	//Arraylist to store the shortest path
    	ArrayList < PairInt > minimum_path = new ArrayList<>();

    	//Storing the length of the minimum path
    	int minimum = Integer.MAX_VALUE;
    	
    	//to store the location of the minimum sub path
    	int location=0;
    	
    	//Iterating over all the possible result to check the minimum path
    	for(int i = 0; i < results.size(); i++)
    	{
    		if(minimum > results.get(i).size()) {
    			
    			minimum = results.get(i).size();
    			location = i;					
    		}
    	}
    	
    	//If there are no paths return empty Arraylist
    	if(results.isEmpty())
    		return minimum_path;
    	
    	//Add all the elements of the minimum path into m
    	minimum_path.addAll(results.get(location));
    	return minimum_path;
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/