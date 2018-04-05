/* CRITTERS Critter2.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Rahul Gupta>
 * <rg43226>
 * Wed 9-10:30
 * Kaajol Dhana
 * krd985
 * Wed 9-10:30
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Critter2 can only move up. It has no reason to move horizontally and thinks it is more efficient to move up and wrap
 * around world if necessary
 *
 *
 */
//can only move up (direction 0)
public class Critter2 extends Critter {
    @Override
    public String toString() {
        return "2";
    }

    private int dir;
    private int numSteps;

    private int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
    // static boolean diagonal = true;


    @Override
    public CritterShape viewShape() {
        return CritterShape.CIRCLE;
    }
    public javafx.scene.paint.Color viewOutlineColor() { return Color.RED; }
    public javafx.scene.paint.Color viewFillColor() { return Color.RED; }

    public Shape getShape()
    {
        Shape s = new Rectangle(size, size);
        s.setFill(Color.ORANGE);
        return s;

    }


    public Critter2() {
        dir = 0;
        numSteps = 0;

    }
    /**
     * Critter2's are cautious and always try to walk away from a fight
     */
    @Override
    public boolean fight(String oponent) {

        String r = look(dir, false);
        if(!(r==null)){
            return false; //do not walk in the direction because there is another critter there
        }
        numSteps++;
        walk(dir);
        return false;
    }
    /**
     * Steps are still counted in TimeSteps as a prize for staying alive.
     */
    @Override
    public void doTimeStep() {
        numSteps += 2;
        run(1);


    }

    /**
     * There is a competition among the Critter2's on who can step more than 10 times without dying
     *
     *
     */
    public static String runStats(java.util.List<Critter> c1) {
        int total_steps = 0;
        int count = 0;
        for (Object obj : c1) {
            Critter2 crit1 = (Critter2) obj;
            if(crit1.numSteps > 10){
                count++;
            }

        }
        System.out.println("There were " + c1.size() + " Critter2s");
        System.out.println("There were " + count + " Critter2 that moved more than 10 times");

        String result = "There were " + c1.size() + " Critter2s, and " + count + " Critter2 that moved more than 10 times";
        return result;

    }
}

