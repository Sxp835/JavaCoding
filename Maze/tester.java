package lab12;

public class tester {
	
	public static void main(String[] args) {
		Pacman testerMaze = new Pacman("unsolvable.txt", "output.txt");
		testerMaze.solveBFS();

	}
}
