package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {



    //Timeline timeline = new Timeline();

    // board size https://www.google.dk/search?q=pac+man+maze+squares&client=ubuntu&hs=btr&channel=fs&source=lnms&tbm=isch&sa=X&ved=0ahUKEwioiL3g87LTAhWOJFAKHS8JBvAQ_AUIBigB&biw=1279&bih=644#imgrc=EQmm3O12MyJogM:
    NodeObject[][] nodeObject;
    int boxesX = 21;
    int boxesY = 21;
    int blockSize = 40;

    int yHeight = boxesY *blockSize;
    int xWitdh = boxesX *blockSize ;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right =false;

    // x and y are in hole numbers
    int directionX;
    int directionY;
    //taken **********


    @Override
    public void start(Stage primaryStage) throws Exception{


        Group root = new Group();
        Scene scene = new Scene(root, xWitdh, yHeight);
        // initialise the object class
        nodeObject = new NodeObject[boxesX][boxesY];


        // make startupclass
        final Setup setup = new Setup(scene,root,nodeObject,blockSize, boxesX,boxesY);



        // when keys are pressed -source: http://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
        up=false;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {


                /*switch (keyEvent.getCode()) {
                    case W:  up =  true;
                        System.out.println("up");

                        break;
                    case S: down = true;
                        System.out.println("down");
                        break;
                    case A: left = true;
                        System.out.println("left");

                        break;
                    case D: right = true;
                        System.out.println("right");

                        break;
                    case P:
                        System.out.println("pause");

                        break;
                }*/
                switch (keyEvent.getCode()) {
                    case W:  up =  true;
                        System.out.println("up");

                        break;
                    case S: down = true;
                        System.out.println("down");
                        break;
                    case A: left = true;
                        System.out.println("left");

                        break;
                    case D: right = true;
                        System.out.println("right");

                        break;
                    case P:
                        System.out.println("pause");

                        break;
                }

            }
        });

        // source :http://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    up = false; break;
                    case S:  down = false; break;
                    case A:  left  = false; break;
                    case D: right  = false; break;
                    //case SHIFT: running = false; break;
                }
            }
        });

        //Filled rectangle test rectangle
        // x,y, size,size.
        final Rectangle pacMan = new Rectangle((directionX *(blockSize)+blockSize), (directionY *(blockSize)+blockSize), blockSize, blockSize);


        pacMan.setFill(Color.YELLOW);

        //final Rectangle gostpink = new Rectangle(40*blockSize, 40* blockSize, blockSize, blockSize);
        //gostpink.setFill(Color.AQUA);

        // start location

        // end
        System.out.println("start x,y"+directionX *(blockSize)+blockSize+"," +directionY *(blockSize)+blockSize);

        System.out.println("");





        // make the nodeclass, to call each node object, do: nodeObject[x][y]. x, y, are numbers
        //nodeObject = new NodeObject[boxesX][boxesY];

        // make startupclass
       // final Setup setup = new Setup(root,nodeObject,blockSize, boxesX,boxesY);
        setup.startUpBoard();
        setup.makeGosts();

        setup.startMover();

        root.getChildren().addAll(pacMan);

        // add search algoritm classes
        int pacmanPosX =2;
        int pacmanPosY = 2;
        //A_star a_star = new A_star(pacmanPosX, pacmanPosY, root,nodeObject,blockSize, boxesX,boxesY);

        primaryStage.setTitle("A*");
        primaryStage.setScene(scene);
        primaryStage.show();

        //setup.CreateNewTimer();
        setup.startGost();
        //setup.pacmanTimer();
        final AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                //int directionX = 0, directionY = 0;
                //Pacman pacman = new Pacman();
                if (up)
                {
                    System.out.println("up activate");
                    directionY = directionY -blockSize;
                    // update search algoritms
                   //updateSearchAlgoritms();



                }
                // change the color of the node above.



                if(down)
                {
                    directionY = directionY +blockSize ;
                    // upate all gosth



                }
                if (left)
                {
                    directionX = directionX -blockSize;

                }
                if (right)
                {
                    directionX = directionX +blockSize;


                }
                //setup.updateAllAlgorithms();

                pacMan.relocate(directionX, directionY);


                //random x


                //gostpink.relocate(gostPinkpositionX, gostPinkpositionY);

                //sleep
                /*try {
                    Thread.sleep(50);
                } catch (InterruptedException ie)
                {
                    System.out.println("error sleep-main");
                }*/


            }
        };
        timer.start();





    }






    public static void main(String[] args) {
        launch(args);
    }
}
