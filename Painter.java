package assignment4;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Painter {

    static int numRows = Params.world_height;                             ///Change to Param.height
    static int numCols = Params.world_width;                             ///Change to Param.width
    static int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
   // static boolean diagonal = true;


    /*
     * Paint the grid lines in something not yellow.  The purpose is two-fold -- to indicate boundaries of
     * icons, and as place-holders for empty cells.  Without placeholders, grid may not display properly.
     */
    private static void paintGridLines(GridPane grid) {
        for (int r = 0; r < numRows; r++)
            for (int c = 0; c < numCols; c++) {
                Shape s = new Rectangle(size, size);
                s.setFill(null);
                s.setStroke(Color.BLUEVIOLET);
                grid.add(s, c, r);
            }

    }


    /*
     * Paints the icon shapes on a grid.
     */
    public static void paint(GridPane grid, Critter[][] display) {
        grid.getChildren().clear();
        paintGridLines(grid);


            for (int r = 0; r < numRows; r++)
                for (int c = 0; c < numCols; c++)
                {
                    if(display[r][c] != null)
                    {
                        Shape s = display[r][c].showCritterShape();

                        grid.add(s, c, r);
                    }

                }


    }

    /*
     * Returns a square or a circle depending on the shapeIndex parameter
     *
     */
    static Shape getIcon(int shapeIndex) {
        Shape s = null;

        switch(shapeIndex) {
            case 0: s = new Rectangle(size, size);
                s.setFill(Color.RED); break;
            case 1: s = new Circle(size/2);
                s.setFill(Color.GREEN);
        }
        // set the outline
        s.setStroke(Color.BLUE);
        return s;
    }

}

