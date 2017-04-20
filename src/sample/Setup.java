package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by v on 4/1/17.
 */
public class Setup {
    private final Scene scene;
    Gost orangeGost;
    Gost redGost;
    Gost pinkGost;
    Gost blueGost;
    int boxesY;
    int boxesX;
    Group root;
    int blockSize;
    NodeObject[][] nodeObject;
    private Rectangle redRect;
    private Rectangle pinkRect;
    private Rectangle blueRect;
    private Rectangle orangeRect;

    public Setup(Scene scene, Group root, NodeObject[][] nodeObject, int blockSize, int boxesX, int boxesY) {
        // nodeObject = new NodeObject[10][10];

        this.scene = scene;
        this.root=root;
        this.nodeObject = nodeObject;
        this.blockSize = blockSize;
        this.boxesX = boxesX;
        this.boxesY = boxesY;


        redGost = new Gost();


        pinkGost = new Gost();
        blueGost = new Gost();

        orangeGost = new Gost();


    }
    // make nodes,and rectangles
    public void startUpBoard() {
        // this makes an a array of NodeObjects
        //Rectangle[][] rect = new Rectangle[10][10];

        for(int y = 0; y < boxesY; y++) {

            // make an array of squares.
            for(int x = 0; x < boxesX; x++) {
                // here the objects are made.
                nodeObject[x][y]=new NodeObject(x,y,root,blockSize);
                // make a square
                //Transparent rectangle with Stroke
                nodeObject[x][y].makeNodeRectangle();


            }

        }
        // make a node Pacman
        //nodeObject[3][3].PacmanSetTrue();



        // prints the nodes
        for(int y = 0; y < boxesY; y++) {

            for(int x = 0; x < boxesX; x++) {


                System.out.println("print nodeObject["+x+y+"] return unique x and y value for obj=  "+
                        nodeObject[x][y].getUniqueXval()+ " ," + nodeObject[x][y].getUniqueYval());
            }
        }
        // make walls
        makewalls();

    }

    private void makewalls() {

        // line at x,6

        for(int x = 0; x < 5; x++) {

            makewallElement(x,6);

        }

        for(int x = 7; x < 9; x++) {

            makewallElement(x,6);

        }


        for(int x = 12; x < 15; x++) {

            makewallElement(x,6);

        }

        // middel pieces
        for(int x = 8; x < 13; x++) {

            makewallElement(x,4);

        }
        for(int x = 8; x < 13; x++) {

            makewallElement(x,8);

        }
        for(int x = 8; x < 13; x++) {

            makewallElement(x,10);

        }
        for(int x = 8; x < 13; x++) {

            makewallElement(x,12);

        }
        for(int x = 8; x < 13; x++) {

            makewallElement(x,16);

        }

        // middle line vertical
        for(int y = 5; y < 7; y++) {
            makewallElement(10,y);
        }

        for(int y = 13; y < 15; y++) {
            makewallElement(10,y);
        }
        for(int y = 17; y < 19; y++) {
            makewallElement(10,y);
        }

        // ghost box
        makewallElement(8,9);
        makewallElement(12,9);

        // wall left huge block

        for(int y = 6; y < 9; y++) {

            for(int x = 2; x < 5; x++) {
            makewallElement(x,y);
             }
        }
        // wall right huge block

        for(int y = 6; y < 9; y++) {

            for(int x = 16; x < 19; x++) {
                makewallElement(x,y);
            }
        }
        // wall left huge block under
        for(int y = 10; y < 13; y++) {

            for(int x = 2; x < 5; x++) {
                makewallElement(x,y);
            }
        }
        for(int y = 10; y < 13; y++) {

            for(int x = 16; x < 19; x++) {
                makewallElement(x,y);
            }
        }


        ///bottom parts

            // bottom left

        makewallElement(2,16);
        makewallElement(18,16);

        for(int x = 16; x < 18; x++) {
            makewallElement(x,14);
        }

        for(int x = 3; x < 5; x++) {
            makewallElement(x,14);
        }


        for(int x = 6; x < 9; x++) {
            makewallElement(x,14);
        }

        for(int x = 12; x < 15; x++) {
            makewallElement(x,14);
        }

        // bottom lines
        for(int x = 3; x < 9; x++) {
            makewallElement(x,18);
        }
        for(int x = 12; x < 18; x++) {
            makewallElement(x,18);
        }

        // lines vertical bottom
        for(int y = 15; y < 17 ; y++) {
            makewallElement(4,y);
        }
        for(int y = 15; y < 17 ; y++) {
            makewallElement(16,y);
        }
        for(int y = 16; y < 18 ; y++) {
            makewallElement(14,y);
        }
        for(int y = 16; y < 18 ; y++) {
            makewallElement(6,y);
        }





        // random upper parts
        // first 4 lines from the top




        makewallElement(3,2);
        makewallElement(4,2);

        makewallElement(16,2);
        makewallElement(17,2);

        makewallElement(3,4);
        makewallElement(4,4);

        makewallElement(16,4);
        makewallElement(17,4);

        makewallElement(6,2);
        makewallElement(7,2);
        makewallElement(8,2);

        makewallElement(10,2);
        makewallElement(10,1);


        makewallElement(12,2);
        makewallElement(13,2);
        makewallElement(14,2);

        // down line (6,y )
        for(int y = 4; y < 9 ; y++) {
            makewallElement(6,y);

        }

        for(int y = 10; y < 13 ; y++) {
            makewallElement(6,y);

        }
        // down line (14,y )

        for(int y = 4; y < 9 ; y++) {
            makewallElement(14,y);

        }

        for(int y = 10; y < 13 ; y++) {
            makewallElement(14,y);

        }

















        // boarders
        for(int y = 0; y < boxesY; y++) {
            makewallElement(0,y);
            makewallElement(boxesX-1,y);
        }

        for(int x = 0; x < boxesX; x++) {
            makewallElement(x,0);
            makewallElement(x,boxesY-1);
        }
        // second inder boarder
        for(int y = 0; y < boxesY; y++) {
            makewallElement(1,y);
            makewallElement(boxesX-2,y);
        }



    }

    public void makewallElement(int x, int y) {
        //System.out.println("make wall");
        nodeObject[x][y].makeNodewall();
        nodeObject[x][y].makeNodeRectangle();
    }




    // make the gosts moves and make the algoritms.
    public void updateAllAlgorithms() {


    }


    public void CreateNewTimer() {
        AnimationTimer gostTimer = new AnimationTimer() {

            // start positions
            public int gostPosX = 6* blockSize;
            public int gostPosY = 2*blockSize;

            @Override
            public void handle(long l) {


                System.out.println("animation start");

                NextMoveRandomgost();


            }

            private void NextMoveRandomgost() {

                int up = 0;
                int down = 1;
                int left = 2;
                int right = 3;
                int direction= (int) (Math.random()*4);
                System.out.println("direction  "+direction);

                // current x,y. for gost
                // x value = gostPosX/ blocksize
                int x = gostPosX / blockSize;
                int y = gostPosY / blockSize;


                System.out.println("current x,y"+ x +", "+y);

                // if wall is false and up is the dir go..
                if (up ==direction ) {


                    System.out.println("gost up activate");
                    gostPosY = gostPosY -blockSize;
                    int yCheck = gostPosY /blockSize;
                    if (!nodeObject[x][yCheck].getisWall()) {

                        System.out.println("not wall");
                    System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());
                        redRect.relocate(gostPosX, gostPosY);

                    }
                    else {
                        // reset
                        System.out.println("block by wall");
                        System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());

                        // reset

                        gostPosY = gostPosY +2*blockSize;

                    }



                    //gostPosY = gostPosY -blockSize;



                }

                if(down ==direction) {
                    System.out.println("gost down activate");
                    gostPosY = gostPosY +blockSize;
                    int yCheck = gostPosY /blockSize;
                    if (!nodeObject[x][yCheck].getisWall()) {

                        System.out.println("not wall");
                        System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());
                        redRect.relocate(gostPosX, gostPosY);

                    }
                    else {
                        // reset
                        System.out.println("block by wall");
                        System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());

                        gostPosY = gostPosY -2*blockSize;

                    }
                    System.out.println("gost down activate");

                    //gostPosY = gostPosY +blockSize ;
                    // upate all gosth



                }
                if (left ==direction)
                {
                    System.out.println("gost left activate");

                    gostPosX = gostPosX -blockSize;



                    System.out.println("gost up activate");
                    // devide by block size to get the actul x value.
                    int xCheck = gostPosX /blockSize;

                    if (!nodeObject[xCheck][y].getisWall()) {

                        System.out.println("not wall");
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());
                        redRect.relocate(gostPosX, gostPosY);


                    }
                    else {
                        // reset
                        System.out.println(" wall");
                        gostPosX = gostPosX +(2*blockSize);
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());


                    }

                }
                if (right ==direction)
                {
                    System.out.println("gost right activate");

                    gostPosX = gostPosX +blockSize;





                    System.out.println("gost up activate");
                    int xCheck = gostPosX /blockSize;
                    if (!nodeObject[xCheck][y].getisWall()) {

                        System.out.println("not wall");
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());
                        redRect.relocate(gostPosX, gostPosY);

                    }
                    else {
                        // reset
                        System.out.println("block by wall");
                        gostPosX = gostPosX -2*blockSize;
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());


                    }

                }

                // update x,y



                // make random
                System.out.println("direction"+ direction );

                try {
                    Thread.sleep(200);
                } catch (InterruptedException ie)
                {
                    System.out.println("error sleep-main");
                }


            }

        };
        gostTimer.start();
    }


    public void makeGosts() {

        // remember the number Rectangle (is the size of the square,)
        redRect = new Rectangle(0,0,blockSize,blockSize);
        redRect.setFill(Color.RED);

        pinkRect = new Rectangle(blockSize,blockSize);
        pinkRect.setFill(Color.PINK);

        blueRect = new Rectangle(blockSize,blockSize);
        blueRect.setFill(Color.BLUE);
        orangeRect = new Rectangle(blockSize,blockSize);
        orangeRect.setFill(Color.ORANGE);



        root.getChildren().addAll(blueRect,pinkRect, redRect,orangeRect);



    }


    public void startGost() {
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {


                System.out.println("each gost moves");

                // find a direction and move that way.


                RedWalksWalkTheAstarPlusrand();



            }


        }, 0, 1000);
    }

    private void RedWalksWalkTheAstarPlusrand() {




    }

    public void startMover() {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public boolean up;
            public boolean down;
            public boolean left;
            public boolean right;

            @Override
            public void handle(KeyEvent keyEvent) {



                switch (keyEvent.getCode()) {
                    case UP:  up =  true;
                        System.out.println("setup up");

                        
                        break;
                    case DOWN: down = true;
                        System.out.println("setup down");
                        break;
                    case LEFT: left = true;
                        System.out.println("setup left");

                        break;
                    case RIGHT: right = true;
                        System.out.println("setup right");

                        break;
                    case P:
                        System.out.println("setup pause");

                        break;
                }

            }
        });




    }


}
