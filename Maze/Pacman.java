package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		// TODO for assignment12
		Node currNode = end.parent;
		while(currNode.content!='S') {
			currNode.content = '.';
			currNode = currNode.parent;
		}
		
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.print(toString());
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + "";
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			// FILL IN
			String[] dim = input.readLine().split(" ");
			height = Integer.parseInt(dim[0]);
			width = Integer.parseInt(dim[1]);
			maze = new Node[height][width];
			String currentLine = input.readLine();
			int x = 0;
			while(currentLine != null) {
				
				for(int y = 0; y<currentLine.length(); y++) {
					char currChar = currentLine.charAt(y);
					maze[x][y] = new Node(x,y,currChar);
					if(currChar == 'S') {
						startX = y;
						startY = x;
					}
				}
				x+=1; 
				currentLine = input.readLine();
			}
			input.close();
			System.out.println(toString());
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();

		// 1. Inspect the north neighbor

		if(inMaze(currentNode.col,width) && inMaze(currentNode.row+1,height)){
			if((!maze[currentNode.row+1][currentNode.col].visited) && (maze[currentNode.row+1][currentNode.col].content != 'X') ) {
				maze[currentNode.row+1][currentNode.col].visited = true;
				maze[currentNode.row+1][currentNode.col].parent = currentNode;
				neighbors.add(maze[currentNode.row+1][currentNode.col]);
			}
		}
		
		// 2. Inspect the south neighbor

		if(inMaze(currentNode.col,width) && inMaze(currentNode.row-1,height)){
			if((!maze[currentNode.row-1][currentNode.col].visited) && (maze[currentNode.row-1][currentNode.col].content != 'X') ) {
				maze[currentNode.row-1][currentNode.col].visited = true;
				maze[currentNode.row-1][currentNode.col].parent = currentNode;
				neighbors.add(maze[currentNode.row-1][currentNode.col]);
			}
			
		}
		
		// 3. Inspect the west neighbor
		
		if(inMaze(currentNode.col-1,width) && inMaze(currentNode.row,height)){
			if((!maze[currentNode.row][currentNode.col-1].visited) && (maze[currentNode.row][currentNode.col-1].content != 'X') ) {
				maze[currentNode.row][currentNode.col-1].visited = true;
				maze[currentNode.row][currentNode.col-1].parent = currentNode;
				neighbors.add(maze[currentNode.row][currentNode.col-1]);
			}
		}

		// 4. Inspect the east neighbor
		
		if(inMaze(currentNode.col+1,width) && inMaze(currentNode.row,height)){
			if((!maze[currentNode.row][currentNode.col+1].visited) && (maze[currentNode.row][currentNode.col+1].content != 'X') ) {
				maze[currentNode.row][currentNode.col+1].visited = true;
				maze[currentNode.row][currentNode.col+1].parent = currentNode;
				neighbors.add(maze[currentNode.row][currentNode.col+1]);
			}
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Node currentNode = maze[startY][startX];
		currentNode.visited = true;
		
		LinkedList<Node> Q = new LinkedList<Node>();
		Q.add(currentNode);
		Node curr;
		while(!Q.isEmpty()) {
			curr = Q.pop();
			if(curr.content == 'G') {
				backtrack(curr);
				writeOutput();
				System.out.println(toString());
				return;
			}
			ArrayList<Node> neighbors = getNeighbors(curr);
			for(int i = 0; i< neighbors.size(); i++) {
					Q.add(neighbors.get(i));
			}
			
		}
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Node currentNode = maze[startY][startX];
		currentNode.visited = true;
		
		Stack<Node> stack = new Stack<Node>();
		Node v;
		
		stack.push(currentNode);
		while(!stack.isEmpty()) {
			v = stack.peek();
			stack.pop();
			if(v.content == 'G') {
				backtrack(v);
				writeOutput();
				System.out.println(toString());
				return;
			}
			ArrayList<Node> neighbors = getNeighbors(v);
			for(int i=0;i<neighbors.size();i++) {
				stack.push(neighbors.get(i));
				
			}
			
		}
		writeOutput();
	}
}
