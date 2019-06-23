package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		Random rand = new Random();
		Cell start = maze.cells[rand.nextInt(maze.cells.length)][rand.nextInt(maze.cells.length)];
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(start);
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
		currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		int tempX = currentCell.getX();
		int tempY = currentCell.getY();
		ArrayList<String> unvisited = new ArrayList<String>();
		if(tempX+1 < maze.cells.length) {
			if(maze.cells[tempX+1][tempY].hasBeenVisited() == false) {
				unvisited.add("east");
			}
		}
		if(tempX-1 >= 0) {
			if(maze.cells[tempX-1][tempY].hasBeenVisited() == false) {
				unvisited.add("west");
			}
		}
		if(tempY+1 < maze.cells.length) {
			if(maze.cells[tempX][tempY+1].hasBeenVisited() == false) {
				unvisited.add("south");
			}
		}
		if(tempY-1 >= 0) {
			if(maze.cells[tempX][tempY-1].hasBeenVisited() == false) {
				unvisited.add("north");
			}
		}
		
		//C. if has unvisited neighbors,
		
			//C1. select one at random.
		if(unvisited.size() > 0) {
			int randIndex = new Random().nextInt(unvisited.size());
			String wallDirection = unvisited.get(randIndex);
			Cell chosenRandom = maze.cells[tempX][tempY];
			switch(wallDirection) {
			case "east":
				chosenRandom = maze.cells[tempX+1][tempY];
				currentCell.setEastWall(false);
				chosenRandom.setWestWall(false);
				break;
			case "west":
				chosenRandom = maze.cells[tempX-1][tempY];
				currentCell.setWestWall(false);
				chosenRandom.setEastWall(false);
				break;
			case "south":
				chosenRandom = maze.cells[tempX][tempY+1];
				currentCell.setSouthWall(false);
				chosenRandom.setNorthWall(false);
				break;
			case "north":
				chosenRandom = maze.cells[tempX][tempY-1];
				currentCell.setNorthWall(false);
				chosenRandom.setSouthWall(false);
				break;
			}
			//C2. push it to the stack
			uncheckedCells.push(chosenRandom);
			//C3. remove the wall between the two cells
					//done abovee
			//C4. make the new cell the current cell and mark it as visited
			currentCell = chosenRandom;
			currentCell.setBeenVisited(true);
			//C5. call the selectNextPath method with the current cell
			selectNextPath(currentCell);
		}
		else {
			if(uncheckedCells.isEmpty() == false) {
				Cell poppedCell = uncheckedCells.pop();
				currentCell = poppedCell;
				selectNextPath(currentCell);
			}
		}
			
			
		//D. if all neighbors are visited
		
			//D1. if the stack is not empty
			
				// D1a. pop a cell from the stack
		
				// D1b. make that the current cell
		
				// D1c. call the selectNextPath method with the current cell
				
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		return null;
	}
}
