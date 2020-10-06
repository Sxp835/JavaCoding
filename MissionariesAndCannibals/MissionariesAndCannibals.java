import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MissionariesAndCannibals {
    static State[] stateMap = new State[5];
    static int depth = 1;

    public static void main(String[] args) {

        State currentState;
        int limit  = 1;
        List<State> children = null;

        for(int i =0; i<5; i++){
            stateMap[i] = new State();
        }


        stateMap[0].moveC();
        stateMap[1].moveM();
        stateMap[2].moveCC();
        stateMap[3].moveMM();
        stateMap[4].moveCM();

        for (int i = 0; i < stateMap.length; i++) {
            currentState = stateMap[i];
            addChildren(currentState);
        }

        while(!search(limit) && limit <= depth){
            limit++;
        }


    }

    public static void addChildren(State currentState){
        int m, c;
        State child;
        if(!currentState.getLeaf()){
            m = currentState.getMissionary();
            c = currentState.getCannibal();
//----------1
            child = new State(m,c);
            if(child.moveM()) {
                currentState.addChild(child);
                addChildren(child);
                depth++;
            }
//----------2
            child = new State(m,c);
            if(child.moveC()) {
                currentState.addChild(child);
                addChildren(child);
                depth ++;
            }
//----------3
            child = new State(m,c);
            if(child.moveMM()) {
                currentState.addChild(child);
                addChildren(child);
                depth++;
            }
//----------4
            child = new State(m,c);
            if(child.moveCC()) {
                currentState.addChild(child);
                addChildren(child);
                depth++;
            }
//----------5
            child = new State(m,c);
            if(child.moveCM()) {
                currentState.addChild(child);
                addChildren(child);
                depth++;
            }
        }
    }


    public static boolean search(int limit){

        State currentState;

 //       System.out.print("SEARCH WITH LIMIT " + limit + " : ");

        if(limit <=0){
            System.out.println("SEARCH WITH LIMIT " + limit + ": not found");
            return false;
        }
        for(int i = 0; i<5; i++){
            currentState = stateMap[i];
//            System.out.println(currentState.getMissionary() + " "+ currentState.getCannibal() +" "+ currentState.getLeaf() +" "+ currentState.getAlive());
            if (currentState.getLeaf() && currentState.getAlive()) {
                System.out.println("(M: " + currentState.getMissionary() + " C: " + currentState.getCannibal() + ")" + "(M: " + (3-currentState.getMissionary()) + " C: " + (3-currentState.getCannibal()) + ")");
                System.out.println("SEARCH WITH LIMIT " + limit + " : found");
                return true;
            }
            boolean check = checkChildren(currentState, limit);
//            System.out.println(check);
            if(check){
                System.out.println("(M: " + currentState.getMissionary() + " C: " + currentState.getCannibal() + ")" + "(M: " + (3-currentState.getMissionary()) + " C: " + (3-currentState.getCannibal()) + ")");
                System.out.println("SEARCH WITH LIMIT " + limit + " : found");
                return true;
            }

        }



        System.out.println("SEARCH WITH LIMIT " + limit + ": not found");
        return false;
    }

    public static boolean checkChildren(State currentState, int limit){
        if(limit == 1){
            return false;
        }

        List<State> children = currentState.getChildren();
        ListIterator<State> iterator = children.listIterator();



        while(iterator.hasNext() && limit >0) {
            State currState = iterator.next();
            if (currState.getAlive() && currState.getLeaf()) {
                System.out.println("(M: " + currState.getMissionary() + " C: " + currState.getCannibal() + ")" + "(M: " + (3-currState.getMissionary()) + " C: " + (3-currState.getCannibal()) + ")");
                return true;
            }
            if(checkChildren(currState, limit -1)){
                System.out.println("(M: " + currState.getMissionary() + " C: " + currState.getCannibal() + ")" + "(M: " + (3-currState.getMissionary()) + " C: " + (3-currState.getCannibal()) + ")");
                return true;
            }
        }


        return false;

    }








}
