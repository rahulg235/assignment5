package assignment4;

/*
 * Do not change or submit this file.
 */
import assignment4.Critter.TestCritter;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Algae extends TestCritter {

	@Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}

	public javafx.scene.paint.Color viewOutlineColor() { return Color.GREEN; }
	public javafx.scene.paint.Color viewFillColor() { return Color.GREEN; }

	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}

	private int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
	// static boolean diagonal = true;


	public Shape getShape()
	{
		Shape s = new Rectangle(size, size);
		s.setFill(Color.BLACK);
		return s;

	}
}
