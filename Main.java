package assignment4;
/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Rahul Gupta>
 * <rg43226>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.lang.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) { // primaryStage is created by Java VM
        try {
            GridPane grid = new GridPane();
            Scene scene = new Scene(grid, 800, 700); // creates a scene object with the GridPane
            primaryStage.setScene(scene); // puts the scene onto the stage
            primaryStage.setTitle("Critter World");
            primaryStage.show(); // display the stage with the scene


            for (int i = 0; i < 25; i++) {
                //Critter.makeCritter("assignment4.Craig");
                //Critter.makeCritter("assignment4.Critter1");
                //Critter.makeCritter("assignment4.Critter3");
            }
            Critter[][] check = Critter.generateMatrix();
            for (int i = 0; i < Params.world_height; i++) {
                for (int j = 0; j < Params.world_width; j++) {
                    System.out.print(check[i][j] + " ");
                }
                System.out.println();
            }
            Painter.paint(grid, Critter.generateMatrix()); // paints the icons on the grid

            Stage secondStage = new Stage();
            secondStage.setTitle("second");
            secondStage.show();


            StackPane pane = new StackPane();
            Scene secondS = new Scene(pane, 400, 400);

            secondStage.setScene(secondS);


            Stage rsResStage = new Stage();
            rsResStage.setTitle("Run Stats");
            rsResStage.show();

            StackPane rsResPane = new StackPane();
            Scene rsResScene = new Scene(rsResPane, 400, 400);

            rsResStage.setScene(rsResScene);
            int yTrans=0;




            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////S T E P  O N C E///////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ArrayList<String> runStatsList = new ArrayList<String>();


            Button stepBtn = new Button("Step Once >>");
            stepBtn.setAlignment(Pos.TOP_CENTER);
            pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

            pane.getChildren().add(stepBtn);
            stepBtn.setTranslateY(-40);

            stepBtn.setOnAction(e -> {
                System.out.println(e);
                try {
                    assignment4.Critter.worldTimeStep();
                    Painter.paint(grid, Critter.generateMatrix());
                    rsResPane.getChildren().clear();
                    int yTranslation =0;
                    for (int i = 0; i < runStatsList.size(); i++) {
                        try {
                            String prefixed = "assignment4." + runStatsList.get(i);
                            Class newCritterClass = Class.forName(prefixed);
                            Critter newCritter = (Critter) newCritterClass.newInstance();
                            List<Critter> instanceList = Critter.getInstances(prefixed);
                            Method s = newCritter.getClass().getMethod("runStats", List.class);
                            Object result = s.invoke(null, instanceList);

                            String resultString = (String) result;
                            Label label1 = new Label(resultString);

                            label1.setTranslateY(yTranslation);

                            rsResPane.getChildren().add(label1);
                            yTranslation+=20;



                            // rsResPane.getChildren().add((result);

                        } catch (Exception ev1) {
                            ev1.printStackTrace();
                        }
                        rsResStage.show();

                    }

                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });


            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////S T E P  O N C E///////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////


            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////S T E P  M U L T I P L E//////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////


            Button stepMultiBtn = new Button("Step Multiple >>");
            stepMultiBtn.setAlignment(Pos.TOP_CENTER);
            pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

            pane.getChildren().add(stepMultiBtn);
            stepMultiBtn.setTranslateY(40);

            stepMultiBtn.setOnAction(e -> {
                System.out.println(e);
                try {
                    Stage stepStage = new Stage();
                    stepStage.setTitle("Stepping Multiple Times");
                    stepStage.show();

                    StackPane stepPane = new StackPane();
                    Scene stepScene = new Scene(stepPane, 400, 400);

                    stepStage.setScene(stepScene);

                    TextField countSteps = new TextField(""); //number of critters asked to make
                    stepPane.getChildren().add(countSteps);


                    Button confirmStep = new Button("Confirm Step");
                    stepPane.getChildren().add(confirmStep);
                    confirmStep.setTranslateY(40);
                    confirmStep.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String numCount = countSteps.getText();
                            Integer count;
                            if (numCount.equals("")) {
                                count = 1;
                            } else {
                                count = Integer.parseInt(numCount);
                            }
                            for (int i = 0; i < count; i++) {
                                try {
                                    assignment4.Critter.worldTimeStep();
                                    Painter.paint(grid, Critter.generateMatrix());

                                } catch (Exception exc) {
                                    exc.printStackTrace();
                                }
                            }
                            stepStage.close();
                            Painter.paint(grid, Critter.generateMatrix());

                            rsResPane.getChildren().clear();
                            int yTranslation =0;
                            for (int i = 0; i < runStatsList.size(); i++) {
                                try {
                                    String prefixed = "assignment4." + runStatsList.get(i);
                                    Class newCritterClass = Class.forName(prefixed);
                                    Critter newCritter = (Critter) newCritterClass.newInstance();
                                    List<Critter> instanceList = Critter.getInstances(prefixed);
                                    Method s = newCritter.getClass().getMethod("runStats", List.class);
                                    Object result = s.invoke(null, instanceList);

                                    String resultString = (String) result;
                                    Label label1 = new Label(resultString);

                                    label1.setTranslateY(yTranslation);

                                    rsResPane.getChildren().add(label1);
                                    yTranslation+=20;



                                    // rsResPane.getChildren().add((result);

                                } catch (Exception ev1) {
                                    ev1.printStackTrace();
                                }
                                rsResStage.show();

                            }
                        }
                    });


                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            });


            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////S T E P  M U L T I P L E//////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////


            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// Q U I T //////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////


            Button quitBtn = new Button("Quit");
            quitBtn.setAlignment(Pos.TOP_CENTER);
            pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

            pane.getChildren().add(quitBtn);
            quitBtn.setTranslateY(80);

            quitBtn.setOnAction(e -> {
                System.out.println(e);
                try {
                    primaryStage.close();
                    secondStage.close();
                    rsResStage.close();
                    return;

                } catch (Exception exc) {
                    exc.printStackTrace();
                }


            });


            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// Q U I T //////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////


            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////M A K E///////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////

            Button btn = new Button("Make");
            pane.getChildren().add(btn);


            btn.setOnAction(e -> {
                System.out.println(e);
                try {
                    Stage makeStage = new Stage();
                    makeStage.setTitle("Making a Critter");
                    makeStage.show();

                    StackPane makePane = new StackPane();
                    Scene makeScene = new Scene(makePane, 400, 400);

                    makeStage.setScene(makeScene);

                    TextField countCritters = new TextField(""); //number of critters asked to make
                    makePane.getChildren().add(countCritters);

                    /*
                    final ComboBox<String> makeCritterOptions = new ComboBox();
                    makeCritterOptions.getItems().addAll(
                            "Craig", "Critter1", "Critter2", "Critter3", "Critter4"
                    );
                    */

                    java.io.File f = new java.io.File("C:/Users/Rahul/IdeaProjects/assignment4/src/assignment4");
                    String[] fileList = f.list();
                    java.util.List<String> subclass = new ArrayList<>();
                    for (String s : fileList) {
                        String className = "assignment4." + s;
                        String adjusted2 = className.substring(0, className.length() - 5);
                        try {
                            Class newCritterClass = Class.forName(adjusted2);
                            Critter newCritter1 = (Critter) newCritterClass.newInstance();

                            Class critterClass = Class.forName("assignment4.Critter");
                            Critter newCritter2 = (Critter) newCritterClass.newInstance();

                            if (newCritter1.getClass().isAssignableFrom(newCritter2.getClass())) {
                                String adjusted1 = s.substring(0, s.length() - 5);
                                //if (!(s.equals("InvalidCritterException") || s.equals("Main") || s.equals("Header") || s.equals("Params") || s.equals("Painter")))
                                subclass.add(adjusted1);
                            }
                        } catch (Exception error) {
                        }
                    }


                    final ComboBox <String> makeCritterOptions = new ComboBox();
                    for(String item: subclass){
                        makeCritterOptions.getItems().add(item);
                    }
                    makePane.getChildren().add(makeCritterOptions);
                    makeCritterOptions.setTranslateY(-40);
                    makeCritterOptions.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String nameCritter = makeCritterOptions.getSelectionModel().getSelectedItem();
                        }
                    });

                    Button confirmMake = new Button("Confirm Make");
                    makePane.getChildren().add(confirmMake);
                    confirmMake.setTranslateY(40);
                    confirmMake.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String numCount = countCritters.getText();
                            Integer count;
                            if (numCount.equals("")) {
                                count = 1;
                            } else {
                                count = Integer.parseInt(numCount);
                            }
                            String nameCritter = makeCritterOptions.getSelectionModel().getSelectedItem();
                            //String prefixed = "assignment5." + nameCritter;
                            for (int i = 0; i < count; i++) {
                                try {
                                    String prefixed = "assignment4." + nameCritter;
                                    Critter.makeCritter(prefixed);
                                    continue;
                                } catch (InvalidCritterException e) {
                                    System.out.println("error process: ");
                                    continue;
                                }
                            }
                            makeStage.close();
                            Painter.paint(grid, Critter.generateMatrix());
                        }
                    });



                } catch (Exception except) {
                    except.printStackTrace();
                }
                Painter.paint(grid, Critter.generateMatrix());
            });

            /////////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////// M A K E /////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////


            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// S E E D //////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////

            Button seedBtn = new Button("Set Seed");
            seedBtn.setAlignment(Pos.TOP_CENTER);
            pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

            pane.getChildren().add(seedBtn);
            seedBtn.setTranslateY(-80);

            seedBtn.setOnAction(e-> {
                        System.out.println(e);
                        try {
                            Stage seedStage = new Stage();
                            seedStage.setTitle("Seed");
                            seedStage.show();

                            StackPane seedPane = new StackPane();
                            Scene seedScene = new Scene(seedPane, 400, 400);

                            seedStage.setScene(seedScene);

                            TextField seedNumber = new TextField(""); //number of critters asked to make
                            seedPane.getChildren().add(seedNumber);


                            Button confirmSeed = new Button("Confirm Seed");
                            seedPane.getChildren().add(confirmSeed);
                            confirmSeed.setTranslateY(40);
                            confirmSeed.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    String numCount = seedNumber.getText();
                                    Integer count;
                                    if (numCount.equals("")) {
                                        count = 1;
                                    } else {
                                        count = Integer.parseInt(numCount);
                                    }

                                    try {
                                        Critter.setSeed(count);

                                    } catch (Exception exc) {
                                        exc.printStackTrace();
                                    }

                                    seedStage.close();
                                    Painter.paint(grid, Critter.generateMatrix());
                                }


                            });


                        } catch (Exception exce) {
                            exce.printStackTrace();
                        }

                    });



                /////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////// S E E D //////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////




            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// R U N  S T A T S  ////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////

            Button runStatsBtn = new Button("Run Stats");
            runStatsBtn.setTranslateY(-120);
            pane.getChildren().add(runStatsBtn);


            runStatsBtn.setOnAction(e -> {
                System.out.println(e);
                try {
                    Stage rsStage = new Stage();
                    rsStage.setTitle("Run Stats on Critters");
                    rsStage.show();

                    StackPane rsPane = new StackPane();
                    Scene rsScene = new Scene(rsPane, 400, 400);

                    rsStage.setScene(rsScene);


                    /*
                    final ComboBox<String> makeCritterOptions = new ComboBox();
                    makeCritterOptions.getItems().addAll(
                            "Craig", "Critter1", "Critter2", "Critter3", "Critter4"
                    );
                    */

                    java.io.File f = new java.io.File("C:/Users/Rahul/IdeaProjects/assignment4/src/assignment4");
                    String[] fileList = f.list();
                    java.util.List<String> subclass = new ArrayList<>();
                    for (String s : fileList) {
                        String className = "assignment4." + s;
                        String adjusted2 = className.substring(0, className.length() - 5);
                       try
                       {
                           Class newCritterClass = Class.forName(adjusted2);
                           Critter newCritter1 = (Critter) newCritterClass.newInstance();

                           Class critterClass = Class.forName("assignment4.Critter");
                           Critter newCritter2 = (Critter) newCritterClass.newInstance();

                           if (newCritter1.getClass().isAssignableFrom(newCritter2.getClass())) {
                               String adjusted1 = s.substring(0, s.length() - 5);
                               //if (!(s.equals("InvalidCritterException") || s.equals("Main") || s.equals("Header") || s.equals("Params") || s.equals("Painter")))
                               subclass.add(adjusted1);
                           }
                       }
                       catch(Exception error)
                       {}

                    }
                    ArrayList<CheckBox> rsOptions = new ArrayList<CheckBox>();
                    int yPos = -160;
                    for (int i = 0; i < subclass.size(); i++) {
                        CheckBox critterList = new CheckBox(subclass.get(i));
                        critterList.setTranslateY(yPos);
                        yPos+=20;
                        rsPane.getChildren().add(critterList);
                        rsOptions.add(critterList);
                    }


                    Button confirmRunStats = new Button("Confirm Run Stats");
                    rsPane.getChildren().add(confirmRunStats);
                    confirmRunStats.setTranslateY(160);
                    confirmRunStats.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ArrayList<String> selectedList = new ArrayList<String>();
                            runStatsList.clear();
                            for (int i = 0; i < rsOptions.size(); i++) {
                                if (rsOptions.get(i).isSelected()) {
                                    selectedList.add(rsOptions.get(i).getText());
                                    runStatsList.add(rsOptions.get(i).getText());
                                }
                            }
                            rsResPane.getChildren().clear();
                            int yTranslation =0;
                            for (int i = 0; i < runStatsList.size(); i++) {
                                try {
                                    String prefixed = "assignment4." + runStatsList.get(i);
                                    Class newCritterClass = Class.forName(prefixed);
                                    Critter newCritter = (Critter) newCritterClass.newInstance();
                                    List<Critter> instanceList = Critter.getInstances(prefixed);
                                    Method s = newCritter.getClass().getMethod("runStats", List.class);
                                    Object result = s.invoke(null, instanceList);

                                    String resultString = (String) result;
                                    Label label1 = new Label(resultString);

                                    label1.setTranslateY(yTranslation);
                                    rsResPane.getChildren().add(label1);
                                    yTranslation+=20;



                                    // rsResPane.getChildren().add((result);

                                } catch (Exception ev1) {
                                    ev1.printStackTrace();
                                }
                                rsResStage.show();

                            }
                            rsStage.close();




                        }


                    });
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                Painter.paint(grid, Critter.generateMatrix());
            });



            /////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////// R U N  S T A T S  ////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////











            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

