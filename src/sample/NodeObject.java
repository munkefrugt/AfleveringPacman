package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by v on 4/1/17.
 */
public class NodeObject {

    // the parent is the node it came from.
    // or a node to reach this node
    int blockSize;
    Group root;
    int x;
    int y;
    int f;
    int h;

    // default g is 0. beacuse the very node g is 0.
    int g= 0;
    // true if there is a wall on that node , false by default
    boolean isWall = false;
    // packMan will change node each time he takes a step. // default is false
    boolean isNodePacman = false;
    boolean isGoalNode = false;


    Rectangle rect;

    // also known as parrent , by here named cameFrom.
    NodeObject cameFrom;
    // in the beginning they are all on openlist exept the startnode witch is on closed list, there for its should be false
    boolean isOnClosedList =false;
    // not food if false
     boolean Food = false;
    // make true when visited used in BFS
    public boolean visited = false;
    NodeObject cameFromBFS;
    public boolean biBFSvisitedA;
    NodeObject A_cameFromBiBFS;
    NodeObject B_cameFromBiBFS;
    public boolean biBFSvisitedB=false;
    private int bestFirstH;
    NodeObject cameFromBestFirst;
    int bestFirstG;
    boolean onClosedListBestFirst=false;


    public NodeObject(int x, int y, Group root, int blockSize) {
        this.root= root;
        this.blockSize= blockSize;
        this.x = x;
        this.y = y;




    }


    public int getUniqueXval() {
        return x;
    }

    public int getUniqueYval()
    {
        return y;
    }

    public void makeNodeRectangle() {
        if(!isWall)
        {

            rect= new Rectangle(x *blockSize, y*blockSize, blockSize, blockSize);

            rect.setFill(Color.WHITE);
            rect.setStroke(Color.RED);
            // set line thickness
            rect.setStrokeWidth(1);

            // add to root
            root.getChildren().add(rect);

        }
        else if(isWall)
        {
            Rectangle rectwall= new Rectangle(x *blockSize, y*blockSize, blockSize, blockSize);

            rectwall.setFill(Color.BLACK);



            // add to root
            root.getChildren().add(rectwall);
        }
    }

    public void makeNodewall() {
        isWall = true;
    }

    public void PacmanSetTrue() {
        isNodePacman = true;
    }

    public void setFZero() {
        f = 0;
    }


    public void setRectColor(Color color) {
        //Rectangle rect= new Rectangle(x *blockSize, y*blockSize, blockSize, blockSize);


        rect.setFill(color);

        // add to root
        // root.getChildren().add(rect);

    }

    public boolean getisWall() {
        return isWall;
    }

    public void makeGreen() {
        rect.setFill(Color.GREEN);
    }






    public void setCameFrom(NodeObject currentCenterNode) {

        this.cameFrom = currentCenterNode;

    }

    public void setCameFromBFS(NodeObject currentCenterNode) {

        this.cameFromBFS = currentCenterNode;

    }

    public void makeitPacman() {
        isNodePacman = true;
        rect.setFill(Color.YELLOW);
    }

    public void setH(int h) {
        this.h = h;
    }
    public void setBestFirstH(int bestFirstH) {
        this.bestFirstH = bestFirstH;
    }

    public int getH() {
        System.out.println("inside NodeObject.class h = "+h);
        return h;
    }
    public int getBestFirstH() {
        System.out.println("inside NodeObject.class h = "+bestFirstH);
        return bestFirstH;
    }

    public void calculateG(int stepvalueG) {


        g = cameFrom.getG() + stepvalueG;
        System.out.println("cameFrom.getG()"+ cameFrom.getG());
        System.out.println("g = "+ g +"**************************");
    }

    public int getG() {
        return g;
    }


    public boolean isOnClosedList() {

        return isOnClosedList;
    }



    public void takeOfOpenList() {
        isOnClosedList = true;
    }

    public int getF() {
        return f;
    }

    public Object getcameFrom() {

        return cameFrom;
    }

    public void setIsWallTofalse() {
        isWall = false;
        makeNodeRectangle();
    }

    public void makeitNotPacman() {
        isNodePacman = false;
    }

    public void resetNode() {
        isOnClosedList = false;
        visited = false;
        cameFromBFS = null;
        onClosedListBestFirst = false;
        cameFromBestFirst = null;
        A_cameFromBiBFS= null;
        B_cameFromBiBFS= null;
        biBFSvisitedA = false;
        biBFSvisitedB = false;
    }

    public void setFood() {
        setRectColor(Color.GREEN);
        Food = true;
    }

    public void setNotFood() {
        setRectColor(Color.WHITE);
        Food = false;
    }

    public NodeObject getcameFromBFS() {
        return cameFromBFS;
    }

    public void setACameFromBiBFS(NodeObject cameFromBiBFS) {
        this.A_cameFromBiBFS = cameFromBiBFS;
    }

    public void setB_cameFromBiBFS(NodeObject b_cameFromBiBFS) {
        this.B_cameFromBiBFS = b_cameFromBiBFS;
    }



    public NodeObject getA_cameFromBiBFS() {
        return A_cameFromBiBFS;
    }

    public NodeObject getB_cameFromBiBFS() {
        return B_cameFromBiBFS;
    }

    public void setCameFromBestFirst(NodeObject cameFromBestFirst) {
        this.cameFromBestFirst = cameFromBestFirst;
    }

    public NodeObject getCameFromBestFirst() {
        return cameFromBestFirst;
    }

    public void BestFirstCalculateG(int stepValueG) {
        bestFirstG = cameFromBestFirst.getBestFirstG() + stepValueG;
        System.out.println("cameFromBestFirst.getBestFirstG()"+ cameFromBestFirst.getBestFirstG());
        System.out.println("bestFirstG = "+ bestFirstG +"**************************");

    }

    public int getBestFirstG() {
        return bestFirstG;
    }

    public boolean isOnClosedListBestFirst() {
        return onClosedListBestFirst;
    }

    public void takeOfOpenListBestFirst() {
        onClosedListBestFirst = true;
    }
}
