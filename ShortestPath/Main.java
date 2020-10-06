import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Environment env = new Environment();
        env.readFromFile("output/environment.txt");
        System.out.println("Loaded an environment with " + env.obstacles.size() + " obstacles.");

    Search[] searches = new Search[]{
      new GreedySearch(),
      new UniformCostSearch(),
      new AStarSearch()
    };

//        AStarSearch search = new AStarSearch();
//
//        System.out.println("Attempting A* search");
//        Environment.printPath("A* search", search.search(env));

    for (Search search : searches) {
      System.out.println("Attempting " + search.getName());
      Environment.printPath(search.getName(), search.search(env));
    }
    }
}
