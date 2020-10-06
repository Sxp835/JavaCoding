import java.util.ArrayList;

public class Polygon {
    public int sides;
    public ArrayList<Vector2D> vertices;

    public Polygon(int sides) {
        this.sides = sides;
        vertices = new ArrayList<Vector2D>(sides);
    }
}
