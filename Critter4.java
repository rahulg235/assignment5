package assignment4;
//Rarely fights and doesn't reproduce


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Critter4 likes doing its own thing. It goes shopping whenever it faces conflict.
 *
 *
 */
public class Critter4 extends Critter {

    /**
     * Prints out 4 on grid for Critter4. Overrides Critter class method
     * @return String on grid
     */
    @Override
    public String toString()
    {
        return "4";
    }

    private int dir;
    private int countSteps;
    private boolean goneShopping;

    private int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
    // static boolean diagonal = true;


    @Override
    public CritterShape viewShape() {
        return CritterShape.CIRCLE;
    }

    public javafx.scene.paint.Color viewOutlineColor() { return Color.BROWN; }
    public javafx.scene.paint.Color viewFillColor() { return Color.BROWN; }
    public Shape getShape()
    {
        Shape s = new Rectangle(size, size);
        s.setFill(Color.ORANGE);
        return s;

    }

    /**
     * Constructor method for Critter4
     *
     */
    public Critter4()
    {
        dir = 6;
        countSteps = 0;
        goneShopping=true;
    }


    /**
     * @param oponent the critter encountered
     * generates random number to decide if Critter will go shopping
     * Critter4 never wants to fight
     *
     */
    @Override
    public boolean fight(String oponent)
    {
        if(getRandomInt(10) > 4)
        {
            goneShopping=false;
            return true;

        }
        goneShopping=true;
        return false;
    }


    /**
     * runs every time step
     * increase count amount
     *
     */
    @Override
    public void doTimeStep()
    {
        this.countSteps++;
        run(getRandomInt(8));
    }
    /**
     * prints out the location of each Critter4 and whether it has gone shopping or not
     *@param c1 a list of all instances of Critter4 alive at the moment
     */
    public static String runStats(java.util.List<Critter> c1)
    {
        System.out.println("There are " + c1.size() + " Critter4s in the grid.");
        int count =0;
        for(int i=0; i<c1.size(); i++)
        {
            System.out.println("Critter 4." + i + " is located at: (" + c1.get(i).getX_coord() + ", " + c1.get(i).getY_coord() + ")");
            Critter4 crit = (Critter4) c1.get(i);
            if(crit.goneShopping)
            {
                count++;
            }

        }
        String result = "There are " + c1.size() + " Critter4s in the grid, and " + count + " have gone shoppping.";
        return result;

    }
}
