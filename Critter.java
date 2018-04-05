package assignment4;

/* CRITTERS Critter.java

 * EE422C Project 4 submission by

 * Replace <...> with your actual data.

 * <Student1 Name>

 * <Student1 EID>

 * <Student1 5-digit Unique No.>

 * <Student2 Name>

 * <Student2 EID>

 * <Student2 5-digit Unique No.>

 * Slip days used: <0>

 * Fall 2016

 */





import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.util.Collections;

import java.util.Comparator;

import java.util.List;



/* see the PDF for descriptions of the methods and fields in this class

 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private

 * no new public, protected or default-package code or data can be added to Critter

 */





public abstract class Critter{

	private static String myPackage;

	private static List<Critter> population = new java.util.ArrayList<Critter>();

	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	private static List<Critter> alive = new java.util.ArrayList<Critter>();

	private static int refreshAlgae = Params.refresh_algae_count;



	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.

	static {

		myPackage = Critter.class.getPackage().toString().split(" ")[1];

	}



	private static java.util.Random rand = new java.util.Random();


	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}

	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background
	 *
	 * critters must override at least one of the following three methods, it is not
	 * proper for critters to remain invisible in the view
	 *
	 * If a critter only overrides the outline color, then it will look like a non-filled
	 * shape, at least, that's the intent. You can edit these default methods however you
	 * need to, but please preserve that intent as you implement them.
	 */
	public javafx.scene.paint.Color viewColor() {
		return javafx.scene.paint.Color.WHITE;
	}

	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }

	public abstract CritterShape viewShape();


	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	protected final String look(int direction, boolean steps) {
		this.energy = this.energy - Params.look_energy_cost;
		int tempX = 0;
		int tempY = 0;
		tempX = old_X;
		tempY = old_Y;
		int count = 0;
		if(steps){
			count = 2;
		}
		else{
			count = 1;
		}
		switch (direction) {
			case 0: //increment x
				tempX = (tempX + count) % (Params.world_width);
				break;
			case 1: //increment x, decrement y
				tempX = (tempX + count) % (Params.world_width);
				if (tempY <= 0) {
					tempY = Params.world_height - count;
				} else {
					tempY-=count;
				}
				break;
			case 2: //decrement y
				if (tempY <= 0) {
					tempY = Params.world_height - count;
				} else {
					tempY-=count;
				}
				break;
			case 3: //decrement x and y
				if (tempY <= 0) {
					tempY = Params.world_height - count;
				} else {
					tempY-=count;
				}
				if (tempX <= 0) {
					tempX = Params.world_width - count;
				} else {
					tempX-=count;
				}
				break;
			case 4: //decrement x
				if (tempX <= 0) {
					tempX = Params.world_width - count;
				} else {
					tempX-=count;
				}
				break;
			case 5: //decrement x, increment y
				if (tempX <= 0) {
					tempX = Params.world_width - count;
				} else {
					tempX=-count;
				}
				tempY = (tempY + count) % Params.world_height;
				break;
			case 6: //increment y
				tempY = (tempY + count) % Params.world_height;
				break;
			case 7: //increment x and y
				tempY = (tempY + count) % Params.world_height;
				tempX = (tempX + count) % (Params.world_width);
				break;
		}
		for(int i = 0; i<alive.size(); i++){
			if(alive.get(i).energy <= 0){
				continue;
			}
			if(alive.get(i).old_X == tempX && alive.get(i).old_Y == tempY){
				return alive.get(i).toString();
			}

		}
		return null;
	}


	public static int getRandomInt(int max) {

		return rand.nextInt(max);

	}


	public static void setSeed(long new_seed) {

		rand = new java.util.Random(new_seed);

	}





	/* a one-character long string that visually depicts your critter in the ASCII interface */

	public String toString() {

		return "";

	}

	private int size = (int) Math.sqrt(300000/((Math.max(Params.world_height, Params.world_width)*(Math.max(Params.world_height, Params.world_width)))));
	// static boolean diagonal = true;


	public Shape getShape()
	{
		Shape s = new Rectangle(size, size);
		s.setFill(Color.YELLOW);
		return s;

	}



	private int energy = 0;



	protected int getEnergy() {

		return energy;

	}



	protected int getX_coord() {

		return x_coord;

	}



	protected int getY_coord() {

		return y_coord;

	}


	private int old_X;
	private int old_Y;
	private int x_coord;

	private int y_coord;

	private int num_move;//counts the number of times a critter has moved in a time step

	private int moveFlag;


	public Shape showCritterShape()
	{
		if(this.viewShape().equals(CritterShape.SQUARE))
		{
			Shape s = new Rectangle(size - (double) size/10, size- (double) size/10);
			s.setTranslateX((double)size/(20));
			s.setFill(this.viewFillColor());
			s.setStroke(this.viewOutlineColor());
			return s;
		}
		if(this.viewShape().equals(CritterShape.CIRCLE))
		{
			Shape s = new Circle(size/2 - (double) size/20);
			s.setTranslateX((double)size/20);
			s.setFill(this.viewFillColor());
			s.setStroke(this.viewOutlineColor());
			return s;
		}
		if(this.viewShape().equals(CritterShape.TRIANGLE))
		{
			Polygon p = new Polygon();
			p.getPoints().addAll(
					(double) size/2.0, (double) size/10,
					(double) size/10, (double)size - size/10,
					(double)size- size/10, (double)size- size/10);
			p.setFill(this.viewFillColor());
			p.setStroke(this.viewOutlineColor());
			p.setTranslateX((double) size/10);
			return p;
		}
		if(this.viewShape().equals(CritterShape.DIAMOND))
		{
			double sizeD = (double) size-0.2*(size);
			Polygon p = new Polygon();
			p.getPoints().addAll((double) sizeD/2, (double) sizeD/10,
					(double) sizeD/10, 	(double)sizeD/2,
					(double) sizeD/2,	(double)sizeD - sizeD/10,
					(double) sizeD - sizeD/10, 		(double)sizeD/2);
			p.setFill(this.viewFillColor());
			p.setStroke(this.viewOutlineColor());
			p.setTranslateX((double) sizeD/10);
			//p.setTranslateY((double) size/10);
			return p;
		}
		if(this.viewShape().equals(CritterShape.STAR))
		{
			Polygon p = new Polygon();
			double sizeD = (double) size-0.2*(size);
			p.getPoints().addAll(
					sizeD/2, sizeD/10,
					2*sizeD/5, 2*sizeD/5,
					sizeD/10, 2*sizeD/5,
					3*sizeD/10, 3*sizeD/5,
					sizeD/5, sizeD - sizeD/10,
					sizeD/2,7*sizeD/10,
					4*sizeD/5, sizeD - sizeD/10,
					7*sizeD/10, 3*sizeD/5,
					sizeD - sizeD/10, 2*sizeD/5,
					3*sizeD/5, 2*sizeD/5
			);
			p.setFill(this.viewFillColor());
			p.setStroke(this.viewOutlineColor());
			p.setTranslateX(sizeD/10);
			return p;

		}
		return null;
	}


	/**

	 * @param direction

	 * @TODO walk/run in fight and checking for same position

	 */

	protected final void walk(int direction) {
		System.out.println("WALK: Critter"+ this + " ENERGY: "+ this.energy + " DIRECTION: "+ direction + " NUM MOVE: " + num_move);
		System.out.println("oldX: "+ x_coord + " oldY: "+ y_coord);
		this.energy = this.energy - Params.walk_energy_cost;
		this.num_move++;
		//critter cannot move more than 2 times but energy is still deducted
		if (energy >= 0) {
			if (this.num_move < 2) {
				moveFlag = 1;
				switch (direction) {
					case 0: //increment x
						old_X = (old_X + 1) % (Params.world_width);
						break;
					case 1: //increment x, decrement y
						old_X = (old_X + 1) % (Params.world_width);
						if (old_Y <= 0) {
							old_Y = Params.world_height - 1;
						} else {
							old_Y--;
						}
						break;
					case 2: //decrement y
						if (old_Y <= 0) {
							old_Y = Params.world_height - 1;
						} else {
							old_Y--;
						}
						break;
					case 3: //decrement x and y
						if (old_Y <= 0) {
							old_Y = Params.world_height - 1;
						} else {
							old_Y--;
						}
						if (old_X <= 0) {
							old_X = Params.world_width - 1;
						} else {
							old_X--;
						}
						break;
					case 4: //decrement x
						if (old_X <= 0) {
							old_X = Params.world_width - 1;
						} else {
							old_X--;
						}
						break;
					case 5: //decrement x, increment y
						if (old_X <= 0) {
							old_X = Params.world_width - 1;
						} else {
							old_X--;
						}
						old_Y = (old_Y + 1) % Params.world_height;
						break;
					case 6: //increment y
						old_Y = (old_Y + 1) % Params.world_height;
						break;
					case 7: //increment x and y
						old_Y = (old_Y + 1) % Params.world_height;
						old_X = (old_X + 1) % (Params.world_width);
						break;

				}
			}
		}
		System.out.println("XCOORD: "+ x_coord + " YCOORD: "+ y_coord);
	}

	protected final void run(int direction) {

		System.out.println("RUN: Critter: "+ this + " ENERGY: " + this.energy + " DIRECTION: "+ direction + " NUM_MOVE " + num_move);

		System.out.println("oldX: "+ x_coord + " oldY: "+ y_coord);

		energy = energy - Params.run_energy_cost;

		this.num_move++;

		//critter cannot move more than twice in each time step

		if (this.num_move < 2) {

			if (energy >= 0) {

				moveFlag = 1;

				//because walk energy is deducted when walk is called

				energy = energy + Params.walk_energy_cost;

				energy = energy + Params.walk_energy_cost;

				num_move-= 2;

				walk(direction);

				walk(direction);



			}

		}

		System.out.println("XCOORD: "+ x_coord + " YCOORD: "+ y_coord);

	}





	/**

	 * @param offspring

	 * @param direction

	 * @TODO add new critter to collection after time step

	 */

	protected final void reproduce(Critter offspring, int direction) {

		System.out.println("REPRODUCE: Critter " + this + " direction: "+ direction);

		System.out.println("XCOORD: "+ x_coord + " YCOORD: "+ y_coord);



		if (this.energy < Params.min_reproduce_energy) {

			return;

		}

		if (this.energy % 2 == 0) {

			offspring.energy = this.energy / 2;

			this.energy = this.energy / 2;

		} else {

			offspring.energy = this.energy / 2;

			this.energy = this.energy / 2 + 1;

		}

		offspring.x_coord = this.x_coord;

		offspring.y_coord = this.y_coord;

		offspring.walk(direction);



		//add new critter to collection after time step

		babies.add(offspring);

	}



	public abstract void doTimeStep();



	public abstract boolean fight(String oponent);



	/**

	 * create and initialize a Critter subclass.

	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,

	 * an InvalidCritterException must be thrown.

	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of

	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of

	 * an Exception.)

	 *

	 * @param critter_class_name

	 * @throws InvalidCritterException

	 */

	public static void makeCritter(String critter_class_name) throws InvalidCritterException {

		try {

			Class newCritterClass = Class.forName(critter_class_name);

			Critter newCritter = (Critter) newCritterClass.newInstance();

			alive.add(newCritter);



			newCritter.x_coord = getRandomInt(Params.world_width);

			newCritter.y_coord = getRandomInt(Params.world_height);

			newCritter.energy = Params.start_energy;





			//Collections.sort(aliveX,Comparator.comparingInt(Critter:: getX_coord).thenComparing(Critter:: getY_coord));

			Collections.sort(alive, Comparator.comparingInt(Critter::getY_coord).thenComparing(Critter::getX_coord));

		} catch (Exception e) {

			throw new InvalidCritterException(critter_class_name);

		}

	}



	/**

	 * Gets a list of critters of a specific type.

	 *

	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.

	 * @return List of Critters.

	 * @throws InvalidCritterException

	 */



	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {

		List<Critter> result = new java.util.ArrayList<Critter>();

		try {

			Class newCritterClass = Class.forName(critter_class_name);

			Critter newCritter = (Critter) newCritterClass.newInstance();

			for (int i = 0; i < alive.size(); i++) {

				if (alive.get(i).getClass().getName().equals(critter_class_name))

					result.add(alive.get(i));

			}

		} catch (Exception e) {

			throw new InvalidCritterException(critter_class_name);

		}

		return result;

	}



	/**

	 * Prints out how many Critters of each type there are on the board.

	 *

	 * @param critters List of Critters.

	 */

	public static String runStats(List<Critter> critters) {

		System.out.print("" + critters.size() + " critters as follows -- ");

		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();

		for (Critter crit : critters) {

			String crit_string = crit.toString();

			Integer old_count = critter_count.get(crit_string);

			if (old_count == null) {

				critter_count.put(crit_string, 1);

			} else {

				critter_count.put(crit_string, old_count.intValue() + 1);

			}

		}

		String prefix = "";

		for (String s : critter_count.keySet()) {

			System.out.print(prefix + s + ":" + critter_count.get(s));

			prefix = ", ";

		}

		System.out.println();
		return null;

	}



	/* the TestCritter class allows some critters to "cheat". If you want to

	 * create tests of your Critter model, you can create subclasses of this class

	 * and then use the setter functions contained here.

	 *

	 * NOTE: you must make sure that the setter functions work with your implementation

	 * of Critter. That means, if you're recording the positions of your critters

	 * using some sort of external grid or some other data structure in addition

	 * to the x_coord and y_coord functions, then you MUST update these setter functions

	 * so that they correctly update your grid/data structure.

	 */

	static abstract class TestCritter extends Critter {

		protected void setEnergy(int new_energy_value) {

			super.energy = new_energy_value;

		}



		protected void setX_coord(int new_x_coord) {

			super.x_coord = new_x_coord;

		}



		protected void setY_coord(int new_y_coord) {

			super.y_coord = new_y_coord;

		}



		protected int getX_coord() {

			return super.x_coord;

		}



		protected int getY_coord() {

			return super.y_coord;

		}





		/*

		 * This method getPopulation has to be modified by you if you are not using the population

		 * ArrayList that has been provided in the starter code.  In any case, it has to be

		 * implemented for grading tests to work.

		 */

		protected static List<Critter> getPopulation() {

			return population;

		}



		/*

		 * This method getBabies has to be modified by you if you are not using the babies

		 * ArrayList that has been provided in the starter code.  In any case, it has to be

		 * implemented for grading tests to work.  Babies should be added to the general population

		 * at either the beginning OR the end of every timestep.

		 */

		protected static List<Critter> getBabies() {

			return babies;

		}

	}



	/**

	 * Clear the world of all critters, dead and alive

	 */



	public static void clearWorld() {

		for (int i = 0; i < population.size(); i++) {

			population.remove(i);

		}

		for (int j = 0; j < alive.size(); j++) {

			alive.remove(j);

		}

		for (int k = 0; k < babies.size(); k++) {

			babies.remove(k);

		}

		// Complete this method.



	}



	/**

	 * @TODO dealing with critters in same position, incrementing time steps, reset num_moves

	 * @TODO critters that walk/run in fight shouldnt move to same position as another creature

	 */

	public static void worldTimeStep() {
		int indexWinner = 0; //index of the winner
		int indexOthers = 0; //to find 2 or more critters in the same position (shouldn't be able to walk/run to a postion with a creature

		Collections.sort(alive, Comparator.comparingInt(Critter::getY_coord).thenComparing(Critter::getX_coord));
		for (int i = 0; i < alive.size(); i++) {
			alive.get(i).old_X = alive.get(i).x_coord;
			alive.get(i).old_Y = alive.get(i).y_coord;
			alive.get(i).num_move = 0; //reset before each doTimeStep
			alive.get(i).moveFlag = 0;
			alive.get(i).doTimeStep();
			alive.get(i).moveFlag = 0; //reset to 0 so can be used in fight
		}
		for(int i = 0; i<alive.size(); i++){ //copy in new positions into the Critter
			alive.get(i).x_coord = alive.get(i).old_X;
			alive.get(i).y_coord = alive.get(i).old_Y;
		}
		for (int i = 0; i < alive.size(); i++) {
			for (int j = i + 1; j < alive.size(); j++) {
				//checking same position and if alive
				if (alive.get(i).x_coord == alive.get(j).x_coord && alive.get(i).y_coord == alive.get(j).y_coord && alive.get(i).energy > 0 && alive.get(j).energy > 0) {
					System.out.println("FIGHT: "+alive.get(i) + " x: " + alive.get(i).x_coord + "y: " + alive.get(i).y_coord);
					System.out.println(alive.get(j) + "x: " + alive.get(i).x_coord + " y: " + alive.get(i).y_coord);
					Critter p1 = alive.get(i);
					Critter p2 = alive.get(j);
					int p1_x = p1.x_coord;
					int p1_y = p1.y_coord;
					int p2_x = p2.x_coord;
					int p2_y = p2.y_coord;

					if (p1.fight(p2.toString())){//A wants to fight
						p1.x_coord = p1.old_X;
						p1.y_coord = p1.old_Y;
						System.out.println(p1 + "wants to fight ");
						//if (p1.moveFlag == 1) { //A moves, no fight btween A B
						if(p1.checkFightWalk(p1_x, p1_y)){
							System.out.println(p1 + " calls walk/run in fight method, no fight");
							System.out.println("checking "+ p2+ "for other encounters");
							checkMulitpleEncounters(p2, j); //check if B has another encounter
						} else {
							if (p2.fight(p1.toString())) {// B wants to fight
								p2.x_coord= p2.old_X;
								p2.y_coord = p2.old_Y;
								System.out.println(p2 + "wants to fight ");
								//if (p2.moveFlag == 1) { //B moves, no fight between A B
								if(p2.checkFightWalk(p2_x, p2_y)){
									System.out.println(p2 + " calls walk/run in fight method, no fight");
									System.out.println("checking "+ p1+ "for other encounters");
									checkMulitpleEncounters(p1, i);
								} else { //fight between A and B
									System.out.println("~~There should be a fight between " + p1 + " and "+ p2);
									if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
										System.out.println("FIGHT between " + p1 +" and " + p2);
										int num1 = p1.getRandomInt(p1.energy);
										int num2 = p2.getRandomInt(p2.energy);
										if (num1 >= num2) { //p1 wins
											System.out.println(p1 + " WINS FIGHT");
											p1.energy += (p2.energy / 2);
											p2.energy = 0;//will be removed later
											if (p1.moveFlag == 0) {
												System.out.println("checking if " + p1 + "has another encounter");
												checkMulitpleEncounters(p1, i);
											}
										}
										if (num1 < num2) {
											//p2 wins
											System.out.println(p2 + " WINS FIGHT");
											p2.energy += (p1.energy / 2);
											p1.energy = 0; //will be removed later
											if (p2.moveFlag == 0) {
												System.out.println("checking if " + p2 + "has another encounter");
												checkMulitpleEncounters(p1, j);
											}
										}
									}

								}
							}
							//B doesnt want to fight but A does
							else {
								if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
									System.out.println("SURRENDER: " + p2 + "doesn't want to fight, " + p1 + " wins ");
									p1.energy += p2.energy / 2;
									p2.energy = 0;
									if (p1.moveFlag == 0) {
										System.out.println("checking if " + p1 + "has another encounter");
										checkMulitpleEncounters(p1, i);
									}
								}
							}

						}
					}
					else{
						//A doesn't want to fight but B does
						if (p2.fight(p1.toString())) {
							p2.x_coord= p2.old_X;
							p2.y_coord = p2.old_Y;
							System.out.println("SURRENDER: " + p1 + "doesn't want to fight, " + p2  + " wins ");
							if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
								p2.energy += p1.energy / 2;
								p1.energy = 0;
							}
							if(p2.moveFlag == 0){
								System.out.println("checking if " + p1 + "has another encounter");
								checkMulitpleEncounters(p2,j);
							}


						}
						else{ //A and B dont want to fight, A wins
							System.out.println("Encounter but no fight - "+ p1 + " wins");
							if (p1.x_coord == p2.x_coord && p1.y_coord == p2.y_coord && p1.energy > 0 && p2.energy > 0) {
								p1.energy += p2.energy / 2;
								p2.energy = 0;
							}
							if(p1.moveFlag == 0){
								checkMulitpleEncounters(p2,j);
							}

						}
					}

				}
			}
		}



		for(int i = 0; i<refreshAlgae; i++){
			Algae a = new Algae();
			a.setEnergy(Params.start_energy);
			a.setX_coord(getRandomInt(Params.world_width));
			a.setY_coord(getRandomInt(Params.world_height));
			alive.add(a);
			population.add(a);
		}
		//adding new babies into the population
		for (Critter baby : babies) {
			alive.add(baby);

		}
		babies.clear();
		//removing rest energy
		for (Critter c : alive) {
			c.energy -= Params.rest_energy_cost;
		}
		//removing dead critters from collection and population
		//add deadCritters to this
		List <Critter> deadCritters = new ArrayList<Critter>();
		for (Critter c : alive) {
			if (c.energy <= 0) {
				deadCritters.add(c);
				System.out.println("Critter "+c + "DIED");
			}
		}
		alive.removeAll(deadCritters);
		population.removeAll(deadCritters);


	}

	protected static Critter[][] generateMatrix()
	{
		Critter[][] display = new Critter [Params.world_height][Params.world_width];
		for(int i =0; i<alive.size(); i++)
		{
			display[alive.get(i).getY_coord()][alive.get(i).getX_coord()] = alive.get(i);
		}
		return display;
	}


	private static void checkMulitpleEncounters(Critter p1, int indexWin) {
		int indexOthers = indexWin + 1;
		for (int i = indexOthers; i < alive.size(); i++) {
			Critter o1 = alive.get(i);
			if (p1.x_coord == o1.x_coord && p1.y_coord == o1.y_coord && p1.energy > 0 && o1.energy > 0) {
				System.out.println("There is another encounter between "+ p1 + " and " + o1);
				if (p1.fight(o1.toString())) { //A wants to fight
					if (o1.fight(p1.toString())) { //B wants to fight
						if (p1.x_coord == o1.x_coord && p1.y_coord == o1.y_coord && p1.energy > 0 && o1.energy > 0) {
							int num1 = p1.getRandomInt(p1.energy);
							int num2 = o1.getRandomInt(o1.energy);
							if (num1 >= num2) { //p1 wins
								p1.energy += (o1.energy / 2);
								o1.energy = 0; //will be removed later
							}
							if (num1 < num2) {
								//p2 wins
								o1.energy += (p1.energy / 2);
								p1.energy = 0; //will be removed later
							}
						}
					} else { //A wants to fight but B doesn't
						if (p1.x_coord == o1.x_coord && p1.y_coord == o1.y_coord && p1.energy > 0 && o1.energy > 0) {
							p1.energy += o1.energy / 2;
							o1.energy = 0;
						}
					}
				} else {
					//A doesn't want to fight but B does
					if (o1.fight(p1.toString())) {
						if (p1.x_coord == o1.x_coord && p1.y_coord == o1.y_coord && p1.energy > 0 && o1.energy > 0) {
							o1.energy += p1.energy / 2;
							p1.energy = 0;
						}
					}
				}
			}
		}
	}



	public static void displayWorld(Stage primaryStage)

	{

		Critter[][] display = new Critter [Params.world_height][Params.world_width];
		for(int i =0; i<alive.size(); i++)
		{
			display[alive.get(i).getY_coord()][alive.get(i).getX_coord()] = alive.get(i);
		}
		try
		{
			primaryStage = new Stage();
			GridPane grid = new GridPane();
			Scene scene = new Scene(grid, 600, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Critter World");
			primaryStage.show();
			Painter.paint(grid, display);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}





		// Complete this method.

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*

        //First Row

        System.out.print("+");                                                    //Top left corner

        for(int i=0; i<Params.world_width; i++)

        {

            System.out.print("-");                                                // n dashes (width)

        }

        System.out.println("+");                                              //Top right corner

        //Middle Rows

        int aliveIndex=0;                                                       //iterates through alive array

        for(int vertical=0; vertical<Params.world_height; vertical++)            //for m rows (height)

        {

            System.out.print("|");                                                //Left hand wall

            if(alive.get(aliveIndex).y_coord==vertical)                                                                 //If the Critter in alive belongs in that row

            {

                for(int horizontal = 0; horizontal < Params.world_width; horizontal++)                                       //For every column in that row

                {

                    if(alive.get(aliveIndex).y_coord==vertical && alive.get(aliveIndex).x_coord==horizontal)                    //If the Critter in alive belongs in that column & row

                    {

                        System.out.print(alive.get(aliveIndex));                                                                //Print it

                        aliveIndex++;                                                                                           //Next Critter

                        while(alive.get(aliveIndex).x_coord==horizontal && alive.get(aliveIndex).y_coord==vertical)             //For every other critter in alive that has the same coordinates

                        {

                            aliveIndex++;                                                                                       //Skip them

                        }

                    }

                    else                                                                                                //Else just print spaces for that row

                    {

                        System.out.print(" ");

                    }

                }

                System.out.println("|");

            }

            else

            {

                for(int horizontal = 0; horizontal < Params.world_width; horizontal++)

                {

                    System.out.print(" ");

                }

                System.out.println("|");

            }

        }

        //Last Row

        System.out.print("+");

        for(int i=0; i<Params.world_width; i++)

        {

            System.out.print("-");

        }

        System.out.println("+");

        */

		///////////////////////////////////////////////////////

	}



	//pass in old x and y and index

	//returns true if moved successfully, false if no move/moves back to original position

	private boolean checkFightWalk(int x, int y) {

		if(moveFlag == 0) { //run or walk was not called from fight

			System.out.println("Critter"+ this + " did not call walk/run from fight method");

			return false;

		}

		for(int i = 0; i<alive.size(); i++){

			if(this != alive.get(i) && this.x_coord == alive.get(i).x_coord && this.y_coord == alive.get(i).y_coord){

				//can move into a position with a dead critter

				if(alive.get(i).energy <= 0){

					System.out.println("Critter"+ this + " moved into position of dead critter during fight");

					return true;

				}

				else{

					System.out.println("Critter"+ this + " encountered another critter trying to escape from fight");

					setX_coord(x);

					setY_coord(y);

					return false;

				}

				//encounter during fight, revert to previous x and y position



			}

		}

		return true;



	}

	private void setX_coord(int x){

		this.x_coord = x;

	}

	private void setY_coord(int y){

		this.y_coord = y;

	}





}

