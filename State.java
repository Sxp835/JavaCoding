import java.util.ArrayList;
import java.util.List;
public class State {
    int missionary;
    int cannibal;
    boolean alive = true;
    List<State> children = new ArrayList<State>();
    boolean leaf = false;

    public State(){

        missionary = 3;
        cannibal = 3;
    }

    public State(int m, int c){
        missionary = m;
        cannibal = c;
    }

    public boolean moveC(){
        if(cannibal == 0){
            return false;
        }
        cannibal--;
        checkAlive();
        return true;
    }

    public boolean moveM(){
        if(missionary == 0) {
            return false;
        }
        missionary--;
        checkAlive();
        return true;
    }

    public boolean moveCC(){
        if(cannibal < 2){
            return false;
        }
        cannibal--;
        cannibal--;
        checkAlive();
        return true;
    }

    public boolean moveMM(){
        if(missionary < 2){
            return false;
        }
        missionary= missionary -2;
        checkAlive();
        return true;
    }

    public boolean moveCM(){
        if(cannibal == 0 || missionary == 0){
            return false;
        }
        cannibal--;
        missionary--;
        checkAlive();
        return true;
    }


    public void checkAlive(){
        if(cannibal>missionary && missionary != 0){
            alive = false;
            leaf = true;
        }
        if((3-cannibal)>(3-missionary) && missionary != 3){
            alive = false;
            leaf = true;
        }
        if(cannibal == 0 && missionary == 0){
            leaf = true;
        }
    }

    public boolean getAlive(){
        return alive;
    }

    public boolean getLeaf(){
        return leaf;
    }

    public int getMissionary() {
        return missionary;
    }

    public int getCannibal(){
        return cannibal;
    }

    public void addChild(State child){
        children.add(child);
    }

    public List getChildren(){
        return children;
    }


}
