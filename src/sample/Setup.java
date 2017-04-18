package sample;

import javafx.scene.Group;

/**
 * Created by v on 4/1/17.
 */
public class Setup {
    int boxesY;
    int boxesX;
    Group root;
    int blockSize;
    NodeObject[][] nodeObject;

    public Setup(Group root, NodeObject[][] nodeObject, int blockSize, int boxesX, int boxesY) {
        // nodeObject = new NodeObject[10][10];

        this.root=root;
        this.nodeObject = nodeObject;
        this.blockSize = blockSize;
        this.boxesX = boxesX;
        this.boxesY = boxesY;

        //Gost pinkGost = new Gost();

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

        // make outer walls:
        // line vertical

        makewallElement(2,2);
        makewallElement(2,3);
        makewallElement(2,4);
        makewallElement(2,5);
        makewallElement(2,6);
        makewallElement(2,7);
        makewallElement(2,8);

        //horisontal
        makewallElement(3,2);
        makewallElement(4,2);
        makewallElement(5,2);

        // line vertical

        makewallElement(7,1);
        makewallElement(7,2);
        makewallElement(7,3);
        makewallElement(7,4);
        makewallElement(7,5);

        makewallElement(7,7);


        makewallElement(4,8);
        makewallElement(5,8);
        makewallElement(6,8);
        makewallElement(7,8);
        makewallElement(7,9);
        makewallElement(8,8);

        makewallElement(9,9);

        for(int y = 0; y < 20; y++) {

            makewallElement(15,y);

        }
        for(int y = 2; y < 30; y++) {

            makewallElement(11,y);

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


    }

    public void makewallElement(int x, int y) {
        //System.out.println("make wall");
        nodeObject[x][y].makeNodewall();
        nodeObject[x][y].makeNodeRectangle();
    }




    // make the gosts moves and make the algoritms.
    public void updateAllAlgorithms() {


    }


}
