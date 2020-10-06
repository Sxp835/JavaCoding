public class SimpleAgent{

    public boolean square1;
    public boolean square2;
    public int location;

    public void setEnvironment(int agent,boolean square1,boolean square2){
        this.square1 = square1;
        this.square2 = square2;
        location = agent;
    }

    public void clean(){


            if (location == 1) {
                if (square1) {
                    System.out.println("\naction: move right");
                    location = 2;

                } else {
                    System.out.println("\naction: suck");
                    square1 = true;

                }
            } else {
                if (square2) {
                    System.out.println("\naction: move left");
                    location = 1;

                } else {
                    System.out.println("\naction: suck");
                    square2 = true;

                }
            }
        }

    }

