import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Environment {
    public double width, height;
    public Vector2D start, goal;
    public ArrayList<Polygon> obstacles;

    public Environment() {
        this.width = 800d;
        this.height = 600d;
        this.start = new Vector2D(0d, 0d);
        this.goal = new Vector2D(800d, 600d);
        this.obstacles = new ArrayList<Polygon>();
    }

    public void readFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);

        int polygons = scan.nextInt();
        this.obstacles = new ArrayList<Polygon>(polygons);

        for (int i = 0; i < polygons; i++) {
            int vertices = scan.nextInt();
            Polygon poly = new Polygon(vertices);

            for (int j = 0; j < vertices; j++)
                poly.vertices.add(new Vector2D(scan.nextDouble(), scan.nextDouble()));

            obstacles.add(poly);
        }

        Polygon poly = new Polygon(1);
        poly.vertices.add(new Vector2D(0d,0d));
        obstacles.add(poly);

        poly = new Polygon(1);
        poly.vertices.add(new Vector2D(800d,600d));
        obstacles.add(poly);
    }

    /* This function prints a solution path to a file so it can be displayed by
     * the `solution.html` page. It takes the name of the search and a
     * LinkedList<Vector2D> representing the path that the search produced as
     * a solution.
     */
    public static void printPath(String searchName, List<Vector2D> path) throws FileNotFoundException {
        File file = new File("output/" + searchName + ".js");
        PrintStream printer = new PrintStream(file);
        printer.println("window." + searchName + " =\n\t[");
        for (int i = 0; i < path.size(); i++) {
            if (i != path.size() - 1)
                printer.print("\t\t[" + path.get(i).x + ", " + path.get(i).y + "],");
            else
                printer.print("\t\t[" + path.get(i).x + ", " + path.get(i).y + "]");
        }
        printer.println("\t];");
        printer.close();
    }
}
