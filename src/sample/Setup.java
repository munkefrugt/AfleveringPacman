package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by v on 4/1/17.
 */
public class Setup  {
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
    private Rectangle redGostRectangle;

    Rectangle pinkGostRectangle;

    private Rectangle pinkRect;
    private Rectangle blueRect;
    private Rectangle orangeRect;


    int pinkGhostStartXPos = 10;
    int pinkGhostStartYPos = 7;

    public int redgostPosX = 6* blockSize;
    public int redgostPosY = 2*blockSize;
    int pacmanStartPosX = 10;
    int pacmanStartPosY = 11;
    int pacmanPosX = pacmanStartPosX;
    int pacmanPosY = pacmanStartPosY ;
    private A_star a_star;
    int redGhostPosX = 10;
    int redGhostPosY= 7;
    Thread t1;
    int redgostPosXUpdate =10;
    int redgostPosYUpdate =7;
    private Setup setup;
    private boolean directionChanged= false;
    private boolean canInteruptNow;
    private boolean startThreadT1 = false;
    NodeObject pacmanNode;
     BFS bfs;
    private boolean startThreadBFS = false;
    private NodeObject BFSrootNode;
    private NodeObject BFSupdatedRootNode;
    private boolean foundPacman = false;
    private NodeObject redGostNode;
    private boolean pinkIsMade = false;


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

        // method needs activation
        //maketheRedghostMoveAlongAStarAndStartNewThread();
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
        /*for(int y = 0; y < boxesY; y++) {

            for(int x = 0; x < boxesX; x++) {


                System.out.println("print nodeObject["+x+y+"] return unique x and y value for obj=  "+
                        nodeObject[x][y].getUniqueXval()+ " ," + nodeObject[x][y].getUniqueYval());
            }
        }*/
        // make walls
        makewalls();
        makeFood();

    }

    private void makeFood() {
        System.out.println("make food");

        for(int y = 0; y < boxesY; y++) {

            // make an array of squares.
            for(int x = 0; x < boxesX; x++) {


                        // if node is not wall
                        if(!nodeObject[x][y].getisWall())
                        {
                            //System.out.println("make food");

                            nodeObject[x][y].setFood();



                        }


            }

            // make not food around the start area
            for (int i = 7; i < 14 ; i++) {
                nodeObject[i][7].setNotFood();
            }
            for (int i = 7; i < 14 ; i++) {
                nodeObject[i][11].setNotFood();
            }
            nodeObject[7][8].setNotFood();
            nodeObject[7][9].setNotFood();
            nodeObject[7][10].setNotFood();

            nodeObject[13][8].setNotFood();
            nodeObject[13][9].setNotFood();
            nodeObject[13][10].setNotFood();

            nodeObject[11][9].setNotFood();
            nodeObject[10][9].setNotFood();
            nodeObject[9][9].setNotFood();







        }
        // make the area in the middle not food.
        //nodeObject[x][y].

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




    public void CreateNewTimer() {
        AnimationTimer gostTimer = new AnimationTimer() {

            // start positions


            @Override
            public void handle(long l) {


                System.out.println("animation start");

                //NextMoveRandomgost();


            }

            private void NextMoveRandomgost() {

                int up = 0;
                int down = 1;
                int left = 2;
                int right = 3;
                int direction= (int) (Math.random()*4);
                System.out.println("direction  "+direction);

                // current x,y. for gost
                // x value = redgostPosX/ blocksize
                int x = redgostPosX / blockSize;
                int y = redgostPosY / blockSize;


                System.out.println("current x,y"+ x +", "+y);

                // if wall is false and up is the dir go..
                if (up ==direction ) {


                    System.out.println("gost up activate");
                    redgostPosY = redgostPosY -blockSize;
                    int yCheck = redgostPosY /blockSize;
                    if (!nodeObject[x][yCheck].getisWall()) {

                        System.out.println("not wall");
                    System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());
                        redGostRectangle.relocate(redgostPosX, redgostPosY);

                    }
                    else {
                        // reset
                        System.out.println("block by wall");
                        System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());

                        // reset

                        redgostPosY = redgostPosY +2*blockSize;

                    }



                    //redgostPosY = redgostPosY -blockSize;



                }

                if(down ==direction) {
                    System.out.println("gost down activate");
                    redgostPosY = redgostPosY +blockSize;
                    int yCheck = redgostPosY /blockSize;
                    if (!nodeObject[x][yCheck].getisWall()) {

                        System.out.println("not wall");
                        System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());
                        redGostRectangle.relocate(redgostPosX, redgostPosY);

                    }
                    else {
                        // reset
                        System.out.println("block by wall");
                        System.out.println("nodeObject[x][yCheck].getisWall() = "+nodeObject[x][yCheck].getisWall());

                        redgostPosY = redgostPosY -2*blockSize;

                    }
                    System.out.println("gost down activate");

                    //redgostPosY = redgostPosY +blockSize ;
                    // upate all gosth



                }
                if (left ==direction)
                {
                    System.out.println("gost left activate");

                    redgostPosX = redgostPosX -blockSize;



                    System.out.println("gost up activate");
                    // devide by block size to get the actul x value.
                    int xCheck = redgostPosX /blockSize;

                    if (!nodeObject[xCheck][y].getisWall()) {

                        System.out.println("not wall");
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());
                        redGostRectangle.relocate(redgostPosX, redgostPosY);


                    }
                    else {
                        // reset
                        System.out.println(" wall");
                        redgostPosX = redgostPosX +(2*blockSize);
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());


                    }

                }
                if (right ==direction)
                {
                    System.out.println("gost right activate");

                    redgostPosX = redgostPosX +blockSize;





                    System.out.println("gost up activate");
                    int xCheck = redgostPosX /blockSize;
                    if (!nodeObject[xCheck][y].getisWall()) {

                        System.out.println("not wall");
                        System.out.println("nodeObject[xCheck][y].getisWall() = "+nodeObject[xCheck][y].getisWall());
                        redGostRectangle.relocate(redgostPosX, redgostPosY);

                    }
                    else {
                        // reset
                        System.out.println("block by wall");
                        redgostPosX = redgostPosX -2*blockSize;
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
        redGostRectangle = new Rectangle(0,0,blockSize,blockSize);
        redGostRectangle.setFill(Color.RED);

        pinkGostRectangle = new Rectangle(0,0,blockSize,blockSize);
        pinkGostRectangle.setFill(Color.PINK);

        blueRect = new Rectangle(blockSize,blockSize);
        blueRect.setFill(Color.BLUE);
        orangeRect = new Rectangle(blockSize,blockSize);
        orangeRect.setFill(Color.ORANGE);



        root.getChildren().addAll(blueRect,pinkGostRectangle, redGostRectangle,orangeRect);



    }






    public void startMover() {

        //a_star = new A_star(redGhostPosX,redGhostPosY,pacmanPosX,pacmanPosY,root,nodeObject,blockSize, boxesX,boxesY);
        //maketheRedghostMoveAlongAStarAndStartNewThread();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            int yCheck;
            int xCheck;

            @Override
            public void handle(KeyEvent keyEvent) {

                nodeObject[pacmanPosX][pacmanPosY].makeitPacman();
                nodeObject[pacmanPosX][pacmanPosY].setRectColor(Color.YELLOW);

                switch (keyEvent.getCode()) {
                    case UP:

                        System.out.println("setup - up");
                        yCheck = pacmanPosY -1;
                        if(!nodeObject[pacmanPosX][yCheck].getisWall())
                        {
                            nodeObject[pacmanPosX][pacmanPosY].makeitPacman();

                            nodeObject[pacmanPosX][pacmanPosY].setRectColor(Color.WHITE);
                            pacmanPosY = yCheck;
                            upDatePacman();

                            //System.out.println("no wall for pacman");
                        }
                        else{
                            System.out.println("pacman cant walk here");

                        }
                        break;


                    case DOWN:
                        System.out.println("setup -down");

                        yCheck = pacmanPosY +1;
                        if(!nodeObject[pacmanPosX][yCheck].getisWall())
                        {
                            nodeObject[pacmanPosX][pacmanPosY].makeitPacman();

                            nodeObject[pacmanPosX][pacmanPosY].setRectColor(Color.WHITE);
                            pacmanPosY = yCheck;
                            upDatePacman();
                        }
                        else{
                            System.out.println("pacman cant walk here");

                        }
                        break;


                    case LEFT:
                        System.out.println("setup -left");
                        xCheck = pacmanPosX -1;
                        if(!nodeObject[xCheck][pacmanPosY].getisWall())
                        {
                            nodeObject[pacmanPosX][pacmanPosY].makeitPacman();

                            nodeObject[pacmanPosX][pacmanPosY].setRectColor(Color.WHITE);
                            pacmanPosX = xCheck;
                            upDatePacman();

                            System.out.println("no wall for pacman");
                        }
                        else{
                            System.out.println("pacman cant walk here");

                        }

                        break;
                    case RIGHT:

                        System.out.println("setup -right");
                        System.out.println("setup -left");
                        xCheck = pacmanPosX +1;
                        if(!nodeObject[xCheck][pacmanPosY].getisWall())
                        {
                            nodeObject[pacmanPosX][pacmanPosY].makeitPacman();

                            nodeObject[pacmanPosX][pacmanPosY].setRectColor(Color.WHITE);
                            pacmanPosX = xCheck;
                            upDatePacman();


                            System.out.println("no wall for pacman");
                        }
                        else{
                            System.out.println("pacman cant walk here");

                        }

                        break;
                    case P:
                        System.out.println("setup pause");

                        break;
                }




            }
        });




    }

    private void upDatePacman() {

        if(pacmanNode != redGostNode)
        {
            System.out.println("endgame , log- update pacman.");
        }
        // freeze the gost positions while the pacman moves 1 step.
        // do this by pausing the thread t1.


        pacmanNode = nodeObject[pacmanPosX][pacmanPosY];

        startThreadT1 = true;
        startThreadBFS= true;
        System.out.println("update pacman");
        //nodeObject[pacmanPosX][pacmanPosY].setRectColor(Color.YELLOW);

        nodeObject[pacmanPosX][pacmanPosY].makeitNotPacman();

        startSearchMethods();
        //maketheRedghostMoveAlongAStarAndStartNewThread();

        // make pacman yellow
        pacmanNode.setRectColor(Color.YELLOW);
        //a_star.clearPath();
    }

    private void startSearchMethods() {

        /*Thread t3 = new Thread(new Runnable() {


            public void run() {
                */

                if(t1 != null)
                {
                    //if (canInteruptNow){

                        directionChanged = true;
                        // stop the walking thread and start a new a-star.
                        System.out.println("stop thread t1");


                        //t1.interrupt(); // TODO change this



                        // now remake the a_star algorithm.
                        // Make sure that all the previous date is gone

                        System.out.println("if(t1 != null)");
                        //updatedghost
                        // delete the old information in astar first.
                        // the activate the astar.
                        a_star.showContentOfLists();
                        a_star.clearContentOfLists();

                        // reset all nodes, set all nodes isOnClosedList = false
                        resetNodesForNextMove();


                        a_star.startNewAstar(); // TODO change back
                    a_star = new A_star(setup,redgostPosXUpdate,redgostPosYUpdate,redGhostPosX,redGhostPosY,pacmanPosX,pacmanPosY,root,nodeObject,blockSize, boxesX,boxesY);
                        directionChanged = false;

                        System.out.println("pinkXY"+pinkGhostStartXPos+","+pinkGhostStartYPos);
                        bfs.start(BFSrootNode,pacmanNode,BFSupdatedRootNode);





                }

                // the first round of the game goes through here.
                else if (t1 == null)
                {
                    System.out.println("no thread t1");
                    // make the first a_star algorithm.
                    a_star = new A_star(setup, redgostPosXUpdate, redgostPosYUpdate, redGhostPosX,redGhostPosY,pacmanPosX,pacmanPosY,root,nodeObject,blockSize, boxesX,boxesY);
                    maketheRedghostMoveAlongAStarAndStartNewThread(); // TODO change back


                    // make BFS
                     bfs = new BFS(setup,nodeObject);


                    BFSrootNode = nodeObject[pinkGhostStartXPos][pinkGhostStartYPos];

                    bfs.start(BFSrootNode,pacmanNode, BFSupdatedRootNode);
                    //PinkghostMoveAlongBFSAndStartNewThread();


                }






           /* }
        });
        t3.start();*/


    }

    private void PinkghostMoveAlongBFSAndStartNewThread() {
        // make new thread so the ghost can be timed.

        Thread threadBFS = new Thread(new Runnable() {


            public void run() {
                System.out.println("start t1");

                if(startThreadBFS)
                {
                    // make one tiny breake in begining so the red ghost moves before the pink.

                    try {
                        Thread.sleep(1000);                 //1000 milliseconds is one second.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                        while(!directionChanged && !foundPacman)
                        {
                            //int pathLenght = bfs.getFinalPathNodes().size();

                            //System.out.println("each gost moves");

                            // find a direction and move that way.

                            // make the ghost move through the list of where the path
                            // go though the list the oposite way.  so from currentnode witch is the goal and the to all "going to"'s.



                            NodeObject LastNodeInFinalpathArray;
                            // if array array is empty. is empty ; true if length is 0 otherwise false.
                            if(bfs.finalPathNodes.isEmpty()){
                                System.out.println("pink ate you , array is empty game over");
                                foundPacman = true;
                                break;
                            }

                            LastNodeInFinalpathArray = bfs.BFSgetlastNodeInFinalPathNodesAndRemove();

                            // int relocateValX
                            int updatedPinkGhostXPos= LastNodeInFinalpathArray.getUniqueXval();
                            int updatedPinkGhostYPos=LastNodeInFinalpathArray.getUniqueYval();
                            //inkGhostXPosUpdated = pinkGhostStartXPos;
                            //System.out.println("LastNodeInFinalpathArray.getUniqueXval()"+ LastNodeInFinalpathArray.getUniqueXval());
                            //System.out.println("LastNodeInFinalpathArray.getUniqueYval()"+ LastNodeInFinalpathArray.getUniqueYval());

                            //

                            BFSupdatedRootNode =nodeObject[updatedPinkGhostXPos][updatedPinkGhostYPos];
                            //bfs.setUpdatedRootNodeBFS(updatedRootNodeBFS);
                            pinkGostRectangle.relocate(updatedPinkGhostXPos*blockSize,updatedPinkGhostYPos*blockSize);

                            // delete the first value of the finalPathNodes.
                            try {
                                Thread.sleep(1000);                 //1000 milliseconds is one second.

                                System.out.println("log, sleep inthread BFS");

                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }

                        }

                }
                else System.out.println("method not ready yet");

            }
        });
        threadBFS.start();


    }

    private void BFSThreadMethodLoop() {

        int i = 0;
        // while direction not changed


    }

    private void resetNodesForNextMove() {

        for(int y = 0; y < boxesY; y++) {

            // make an array of squares.
            for(int x = 0; x < boxesX; x++) {
                // here the objects are made.
                nodeObject[x][y].resetNode();


                // make all the  not walls white // by default not wall.
                if(!nodeObject[x][y].isWall)
                {
                    // make it white
                    //nodeObject[x][y].setRectColor(Color.WHITE);
                }


            }

        }
    }

    private void maketheRedghostMoveAlongAStarAndStartNewThread() {




                // make a new thread  so we can pause that thread, thats the way we make the gost move one space per sec.
                 t1 = new Thread(new Runnable() {


                    public void run() {


                        System.out.println("start t1");
                        if(startThreadT1)
                        {


                            while(!foundPacman)
                            {

                            t1ThreadMethodLoop();
                                System.out.println("loop ended found pacman");


                            }
                            //System.out.println("end game");
                        }
                        else System.out.println("method not ready yet");


                    }
                });
                if(startThreadT1){

                t1.start();
                }

    }

    private void t1ThreadMethodLoop() {

        int i = 0;
        // while direction not changed
        while(!directionChanged && !foundPacman)
        {
            //Run pink and red ghost.
            if(pinkIsMade){
            bfs.pinkwalks();
                System.out.println("pinkwalks");
            }

            System.out.println("red moves");
        int pathLenght = a_star.getArrayFinalPathNodes().size();

            //System.out.println("each gost moves");

            // find a direction and move that way.

            // make the ghost move through the list of where the path
            // go though the list the oposite way.  so from currentnode witch is the goal and the to all "going to"'s.


            NodeObject LastNodeInFinalpathArray;
            // if array array is empty. is empty ; true if length is 0 otherwise false.
            if(a_star.finalPathNodes.isEmpty()){
                System.out.println("array is empty game over");
                foundPacman = true;
                break;
            }
            LastNodeInFinalpathArray = a_star.getlastNodeInFinalPathNodesAndRemove();
            redgostPosX= LastNodeInFinalpathArray.getUniqueXval();
            redgostPosY=LastNodeInFinalpathArray.getUniqueYval();
            //System.out.println("LastNodeInFinalpathArray.getUniqueXval()"+ LastNodeInFinalpathArray.getUniqueXval());
            //System.out.println("LastNodeInFinalpathArray.getUniqueYval()"+ LastNodeInFinalpathArray.getUniqueYval());

            //System.out.println("walking redgostPosX "+redgostPosX);
            //System.out.println("walking redgostPosY "+redgostPosY);
            // for some reason itdosent work outside thetime loop and the variables have to be redefined.
            int relocateValX= redgostPosX;
            int relocateValY= redgostPosY;

            redgostPosXUpdate = redgostPosX;
            redgostPosYUpdate = redgostPosY;

            redGostNode = nodeObject[redgostPosXUpdate][redgostPosYUpdate];

            redGostRectangle.relocate(relocateValX*blockSize,relocateValY*blockSize);

            // delete the first value of the finalPathNodes.
            try {
                canInteruptNow = false;// TODO DELETE THIS?
                Thread.sleep(1000);                 //1000 milliseconds is one second.

                //System.out.println("log, sleep inthread t1");
                //set in some kind of thing that its okay to interupt.
                canInteruptNow  = true; // TODO DELETE THIS?

            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }


            i ++;

        }

    }


    public void setSetup(Setup setup) {
        this.setup = setup;
    }


    public boolean getdirectionChanged() {
        return directionChanged;
    }

    public void setpinkIsMade() {
        pinkIsMade = true;
    }
}
