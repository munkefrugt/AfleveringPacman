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

/*
the main grid is is made with nodes, first node is 0,0
 X: (left to right) 0, 1,2 ect
 Y: (from up to down) 0, 1,2 ect
 x and y values stored in node are the same values. also starts with 0.



notes on a a-star

	// A* psudo code: http://web.mit.edu/eranki/www/tutorials/search/

initialize the open list
initialize the closed list
put the starting node on the open list (you can leave its f at zero)

while the open list is not empty
    find the node with the least f on the open list, call it "q"
    pop q off the open list
    generate q's 8 successors and set their parents to q
    for each successor
    	if successor is the goal, stop the search
        successor.g = q.g + distance between successor and q
        successor.h = distance from goal to successor
        successor.f = successor.g + successor.h

        if a node with the same position as successor is in the OPEN list \
            which has a lower f than successor, skip this successor
        if a node with the same position as successor is in the CLOSED list \
            which has a lower f than successor, skip this successor
        otherwise, add the node to the open list
    end
    push q on the closed list
end

 */
public class Main extends Application {



    //Timeline timeline = new Timeline();


    NodeObject[][] nodeObject;
    int boxesX = 40;
    int boxesY = 40;
    int blockSize = 20;

    int yHeight = boxesY *blockSize;
    int xWitdh = boxesX *blockSize ;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right =false;

    // x and y are in hole numbers
    int directionX;
    int directionY;
    int gostPinkpositionX;
    int gostPinkpositionY;
    //taken **********


    @Override
    public void start(Stage primaryStage) throws Exception{


        Group root = new Group();
        Scene scene = new Scene(root, xWitdh, yHeight);

        // when keys are pressed -source: http://stackoverflow.com/questions/29962395/how-to-write-a-keylistener-for-javafx
        up=false;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {


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
        nodeObject = new NodeObject[boxesX][boxesY];

        // make startupclass
        final Setup setup = new Setup(root,nodeObject,blockSize, boxesX,boxesY);
        setup.startUpBoard();




        root.getChildren().addAll(pacMan);

        // add search algoritm classes
        //Gost gost = new Gost();
        A_star a_star = new A_star(root,nodeObject,blockSize, boxesX,boxesY);

        primaryStage.setTitle("A*");
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                //int directionX = 0, directionY = 0;
                //Pacman pacman = new Pacman();
                if (up)
                {
                    System.out.println("up activate");
                    directionY = directionY -blockSize;
                    // update search algoritms
                    updateSearchAlgoritms();



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
                setup.updateAllAlgorithms();

                pacMan.relocate(directionX, directionY);
                //random x


                //gostpink.relocate(gostPinkpositionX, gostPinkpositionY);

                //sleep
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ie)
                {
                    System.out.println("error sleep-main");
                }


            }
        };
        timer.start();

    }

    public void updateSearchAlgoritms() {

    }




    public static void main(String[] args) {
        launch(args);
    }
}
