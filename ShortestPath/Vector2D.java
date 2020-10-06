public class Vector2D {
    public double x, y;
    public double heuristic;
    public boolean visited;
    public double totalDistance;
    public double distanceFromStart;
    public Vector2D previous;

    public Vector2D() {
        this(0d, 0d);
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
        setHeuristic();
        this.visited = false;
        distanceFromStart = 1.8 * Math.pow(10,308);


    }

    public void setTotalDistance(double totalDistance){
        this.totalDistance = totalDistance;
    }

    public void setPrevious(Vector2D previous){
        this.previous = previous;
    }

    public void setDistanceFromStart(double d){
        this.distanceFromStart = d;
    }


    public void setHeuristic() {
        this.heuristic = (Math.sqrt((Math.pow((800 - x),2)) + (Math.pow((600 - y),2))));
    }

    public Vector2D add(Vector2D other) {

        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {

        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D scale(double scalar) {

        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public double abs() {

        return Math.sqrt(x*x + y*y);
    }
}
