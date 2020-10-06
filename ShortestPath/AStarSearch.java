import java.util.LinkedList;
import java.util.*;

public class AStarSearch implements Search {
    public PriorityQueue<Vector2D> pq = new PriorityQueue(new distanceComparator());
    public static Environment environment;
    public LinkedList<Vector2D> search(Environment environment) {
        //
        // TODO
        this.environment = environment;
        Vector2D current, tempVector;
        LinkedList<Vector2D> path = new LinkedList<>();



        current = environment.start;
        current.distanceFromStart = 0;
        pq.add(current);

        while(current.x != 800 && current.y != 600){
            search(current);
            current = pq.poll();
        }

//        current = pq.poll();
        path.add(current);

        while(current.previous!=null){
            path.add(current.previous);
            current = current.previous;
        }

        path.add(current);




        //
        return path;
    }

    public void search(Vector2D current){
        LinkedList<Vector2D> successors;
        double distanceFromPrev, temp, h;

        successors = getSuccessors(current);
        current.visited = true;
        for(int i = 0; i< successors.size(); i++) {
            distanceFromPrev = calculateDistance(successors.get(i), current);
            temp = current.distanceFromStart + distanceFromPrev;
            if(temp < successors.get(i).distanceFromStart){
                successors.get(i).setDistanceFromStart(temp);
                h = successors.get(i).heuristic;
                successors.get(i).setTotalDistance(temp + h);
                successors.get(i).setPrevious(current);
                pq.add(successors.get(i));
            }
        }

    }

    public double calculateDistance(Vector2D a, Vector2D b){
        double distance, x1, x2, y1, y2;

        x1 = a.x;
        x2 = b.x;
        y1 = a.y;
        y2 = b.y;

        distance = (Math.sqrt( Math.pow((x2-x1),2) + Math.pow((y2-y1),2) ));

        return distance;
    }

    public LinkedList<Vector2D> getSuccessors(Vector2D current){
        Vector2D currentVertex;
        Polygon currentPolygon;
        LinkedList<Vector2D> possibleVertex = new LinkedList<>();


        for (int i = 0; i < environment.obstacles.size(); i++) {
            currentPolygon = environment.obstacles.get(i);
            for (int j = 0; j < currentPolygon.sides; j++) {
                currentVertex = currentPolygon.vertices.get(j);
                if(currentVertex == current){
                    continue;
                }
                if (checkPath(current, currentVertex)) {
                    possibleVertex.add(currentVertex);
//                    System.out.println(current.x + " " + current.y + ":    " + currentVertex.x + " " + currentVertex.y);
                }
            }
        }

        return possibleVertex;
    }

    public String getName() {
        return "astar";
    }


    public boolean checkPath(Vector2D a, Vector2D b) {

        Polygon currentPolygon;
        Vector2D currVertex2;
        Vector2D currVertex1;


        for (int i = 0; i < environment.obstacles.size(); i++) {
            currentPolygon = environment.obstacles.get(i);
            for (int j = 0; j < currentPolygon.vertices.size(); j++) {
                for (int k = 0; k < currentPolygon.vertices.size(); k++) {
                    currVertex1 = currentPolygon.vertices.get(j);
                    currVertex2 = currentPolygon.vertices.get(k);
                    if (intersection(a, b, currVertex1, currVertex2) && currVertex1 != currVertex2) {
                        return false;
                    }

                }
            }
        }

        return true;
    }


    public boolean intersection(Vector2D a, Vector2D b, Vector2D c, Vector2D d){
        double x1,x2,x3,x4,y1,y2,y3,y4,a1,a2,b1,b2;
        double x0;

        x1 = a.x;
        y1 = a.y;
        x2 = b.x;
        y2 = b.y;
        x3 = c.x;
        y3 = c.y;
        x4 = d.x;
        y4 = d.y;
        a1 = (y2-y1)/(x2-x1);
        b1 = y1-a1*x1;
        a2 = (y4-y3)/(x4-x3);
        b2 = y3 - a2*x3;



        if(a1 == a2){
//            if(b1 == b2){
//                return false;
//            }
//            else{
//                return false;
//            }
            return false;
        }
        //if both are vertical
        if((x1 == x2) && (x3 == x4)){
            //No intersection
            if(x1 != x3){
                return false;
            }
            else{
                //Do (y1,y2) and (y3,y4) oberlap
                //if((((min(y1,y2)) < (min(y3,y4))) && ((max(y1,y2))>(min(y3,y4)))) || (((min(y1,y2))<(max(y3,y4))) && ((max(y1,y2))>(max(y3,y4)))) || (((min(y3,y4))<(min(y1,y2))) && ((max(y3,y4))>max(y1,y2))){
                if((((Math.min(y3,y4))<(Math.min(y1,y2)))&&((Math.max(y3,y4))>(Math.min(y1,y2))))||(((Math.min(y3,y4))<(Math.max(y1,y2)))&&((Math.max(y3,y4))>(Math.max(y1,y2))))||(((Math.min(y1,y2))<(Math.min(y3,y4)))&&(Math.max(y1,y2)>(Math.min(y3,y4))))||(((Math.min(y1,y2))<(Math.max(y3,y4)))&&(Math.max(y1,y2)>(Math.max(y3,y4))))){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        //if only line one is vertical
        else if(x1 == x2){
            //point of intersection
            x0 = a2 * x1 + b2;
            //is point of intersection within both segments
            if(((Math.min(x1,x2) < x0) && (x0 < Math.max(x1,x2))) && ((Math.min(x3,x4) < x0 ) && (x0 < Math.max(x3, x4)))){
                return true;
            }
            else{
                return false;
            }
        }
        //if only line two is vertical
        else if(x3 == x4){
            x0 = a1 * x3 + b1;
            //is point of intersection within both segments
            if(((Math.min(x1,x2) < x0) && (x0 < Math.max(x1,x2))) && ((Math.min(x3,x4) < x0 ) && (x0 < Math.max(x3, x4)))){
                return true;
            }
            else{
                return false;
            }
        }

        else{
            x0 = -(b1-b2)/(a1-a2);
            if(  (Math.min(x1, x2) < x0) && (x0 < Math.max(x1, x2)) && (Math.min(x3, x4) < x0) && (x0 < Math.max(x3, x4))){
                return true;
            }
        }

        return false;

    }

}

class distanceComparator implements Comparator<Vector2D>{
    public int compare(Vector2D v1, Vector2D v2) {
        if (v1.totalDistance > v2.totalDistance){
            return 1;
        }
        else{
            return 0;
        }
    }

}
