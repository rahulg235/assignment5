package assignment4;

//wants to reproduce alot

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Critter1 extends Critter {

    @Override

    public String toString(){

        return "1";

    }

    @Override
    public CritterShape viewShape() {
        return CritterShape.SQUARE;
    }

    public javafx.scene.paint.Color viewOutlineColor() { return Color.BLUE; }
    public javafx.scene.paint.Color viewFillColor() { return Color.BLUE; }

    static int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
    // static boolean diagonal = true;

    @Override
    public Shape getShape()
    {
        Shape s = new Rectangle(size, size);
        s.setFill(Color.BLUE);
        return s;

    }

    private int dir;

    private int countKids;



    public Critter1(){

        dir = getRandomInt(8);

        countKids = 0;

    }



    @Override

    public boolean fight(String oponent) {

        if(getRandomInt(10) > 4){

            Critter1 c = new Critter1();

            this.countKids++;

            reproduce(c,c.dir);

        }

        return false;

    }



    @Override

    public void doTimeStep() {

        Critter1 c = new Critter1();

        this.countKids++;

        reproduce(c,c.dir);

    }

    public static String runStats(java.util.List<Critter> c1){

        int total_kids = 0;

        for(Object obj: c1){

            Critter1 crit1 = (Critter1) obj;

            total_kids+= crit1.countKids;

        }

        System.out.println("There were "+ c1.size() + " Critter1s");
        if(c1.size() ==0)
        {
            return "There were no Critter1s found";
        }
        int avg = total_kids/c1.size();

        System.out.println("On average, each Critter1 had " + avg+ " kids");
        String res = "There were " + c1.size() + " Critter1s who had on average " + avg + " kids.";
        return res;


    }

}