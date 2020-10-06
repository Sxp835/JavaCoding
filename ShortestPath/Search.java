import java.util.LinkedList;

public interface Search {
    public LinkedList<Vector2D> search(Environment environment);

    public String getName();


    // TODO: you might want to write methods like getCost, getSteps, etc. for
    // more verbose output
}
