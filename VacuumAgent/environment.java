public class environment {

    public static void main(String[] args) {
//      true if clean, false if dirty
        boolean square1;
        boolean square2;
        int agent;
        int points = 0;


//      randomly assign dirt and agent
        square1 = (Math.random() < 0.5);
        square2 = (Math.random() < 0.5);
        agent = (Math.random() <= 0.5) ? 1 : 2;
//      System.out.println(square1 + " " + square2 + " " + agent);
        print(square1, square2, agent);

        SimpleAgent vacuum;
        vacuum = new SimpleAgent();
        vacuum.setEnvironment(agent, square1, square2);
        while(!vacuum.square1 || !vacuum.square2) {
            vacuum.clean();
            points+=10;
            System.out.println("points: " + points+ "\n");
            print(vacuum.square1, vacuum.square2, vacuum.location);

        }
        System.out.println("Done!");


    }
    public static void print(boolean sq1, boolean sq2, int agent){
        System.out.println("--------------------");
        System.out.print("|");
        if(sq1){
            System.out.print("clean");
        }
        else{
            System.out.print("dirty");
        }
        if(agent == 1){
            System.out.print(" vacuum");
        }
        System.out.print("|");
        if(sq2){
            System.out.print("clean");
        }
        else{
            System.out.print("dirty");
        }
        if(agent == 2){
            System.out.print(" vacuum");
        }
        System.out.println("|");
        System.out.println("--------------------");
    }




}


