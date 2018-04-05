package assignment4;
//Refuses to fight, flips a coin and walks or run if in a random direction instead of fighting

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Critter3 finds shirts wherever it goes. It moves left or right and doesn't reproduce.
 *
 *
 */
public class Critter3 extends Critter {

    /**
     * Prints out 3 on grid for Critter3. Overrides Critter class method
     * @return String on grid
     */
    @Override
    public String toString()
    {
        return "3";
    }

    private int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
    // static boolean diagonal = true;

    @Override
    public CritterShape viewShape() {
        return CritterShape.CIRCLE;
    }
    public javafx.scene.paint.Color viewOutlineColor() { return Color.DARKORANGE; }
    public javafx.scene.paint.Color viewFillColor() { return Color.DARKORANGE; }

    @Override
    public Shape getShape()
    {
        Shape s = new Rectangle(size, size);
        s.setFill(Color.GREEN);
        return s;

    }

    private int dir;
    private int countSteps;
    private int numShirts;
    /**
     * Constructor method for Critter3
     *
     */
    public Critter3()
    {
        dir = getRandomInt(8);
        countSteps = 0;
        numShirts=0;
    }

    /**
     * returns number of shirts Critter3 has
     * @return int number of shirts
     */
    private int getShirts()
    {
        return numShirts;
    }

    /**
     * @param oponent the critter encountered
     * generates random number to direction critter wants to walk
     * Critter3 never wants to fight
     *
     */
    @Override
    public boolean fight(String oponent)
    {
        if(getRandomInt(2)==0)
            this.walk(getRandomInt(8));
        else
            this.walk(getRandomInt(8));
        return false;
    }
    /**
     * walks left or right
     * increase count and shirt amount
     *
     */
    @Override
    public void doTimeStep()
    {
        this.countSteps++;
        this.numShirts++;
        if(numShirts%2==0)
            walk(0);
        else
            walk(4);
    }
    /**
     * prints out the location of each Critter3 and how many shirts it has
     *@param c1 a list of all instances of Critter alive at the moment
     */
    public static String runStats(java.util.List<Critter> c1)
    {
        if(c1.size()==0)
        {
            return "There are no Critter3s in the grid.";
        }
        System.out.println("There are " + c1.size() + " Critter3s in the grid.");
        int totShirts=0;
        for(int i=0; i<c1.size(); i++)
        {
            Critter3 crit = (Critter3) c1.get(i);
            totShirts +=crit.numShirts;
        }
        String result = "There are " + c1.size() + " Critter3s in the grid, who own on average " + totShirts/c1.size() + " shirts.";
        return result;
    }
}
