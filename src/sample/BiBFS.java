package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by v on 5/1/17.
 */
public class BiBFS {
    private final LinkedList<NodeObject> AToCommenNodepath;
    private final LinkedList<NodeObject> BToCommenNodepath;
    NodeObject rootNode;
    LinkedList<NodeObject> qA;
    LinkedList<NodeObject> qB;
    Setup setup;
    NodeObject[][] nodeObject;
    ArrayList<NodeObject> VisitedNodesList;

    private NodeObject pacmanNode;
    private ArrayList visitedlistB;
    private ArrayList visitedlistA;
    private boolean ArrayesHaveCommenNode=false;
    private boolean readyToMakepath;
    private boolean colorOnBFS = true;
    // bidirectional Breath first search

    // inpired by. http://stackoverflow.com/questions/38674659/bidirectional-search-implementation-for-graph

    // its to BFS that meet each other.

    public BiBFS(Setup setup, NodeObject[][] nodeObject) {
        this.setup = setup;
        this.nodeObject = nodeObject;

            // qA = ghost, qB = Pacman

         qA = new  LinkedList<NodeObject>();
         qB = new  LinkedList<NodeObject>();

        AToCommenNodepath = new  LinkedList<NodeObject>();
        BToCommenNodepath = new  LinkedList<NodeObject>();


        visitedlistA = new ArrayList<NodeObject>();
        visitedlistB = new ArrayList<NodeObject>();

        VisitedNodesList = new ArrayList<NodeObject>();
        // ghoststartPos
        rootNode = nodeObject[10][7];
    }




    public void start(NodeObject pacman, NodeObject updatedRoot) {


        NodeObject AcurrentCenterViewerNode = rootNode;
        NodeObject BcurrentCenterViewerNode = pacman;


        qA.clear();
        qB.clear();
        visitedlistA.clear();
        visitedlistB.clear();

        if(updatedRoot != null){
            System.out.println("updatedRootNodeBFS inside  BFS"+updatedRoot.getUniqueXval()+","+updatedRoot.getUniqueYval());

            rootNode = updatedRoot;

        }

        //rootNode= newRootNode;

        // add something to the queue so its not empty
        qA.add(rootNode);
        qB.add(pacman);


        // mark root node as visited






        // this is just to be able to clear the visited nodes:
        VisitedNodesList.add(rootNode);
        VisitedNodesList.add(pacman);





        while(!qA.isEmpty() && !qB.isEmpty()){


            // A:
            AcurrentCenterViewerNode = qA.getFirst();
            //remove first

            qA.removeFirst();
            // mark the currentCenterViewerNode as visited.
            AcurrentCenterViewerNode.biBFSvisitedA = true;
            visitedlistA.add(AcurrentCenterViewerNode);
            VisitedNodesList.add(AcurrentCenterViewerNode);


            //currentCenterViewerNode.setRectColor(Color.YELLOW);

            //System.out.println("searching childnodes");
            FindChildrenForA(AcurrentCenterViewerNode);



            // now do the same for B
            // B:
            BcurrentCenterViewerNode = qB.getFirst();
            //remove first

            qB.removeFirst();
            // mark the currentCenterViewerNode as visited.
            BcurrentCenterViewerNode.biBFSvisitedB = true;
            visitedlistB.add(BcurrentCenterViewerNode);

            VisitedNodesList.add(BcurrentCenterViewerNode);


            //currentCenterViewerNode.setRectColor(Color.YELLOW);

            //System.out.println("searching childnodes");
            FindChildrenForB(BcurrentCenterViewerNode);


            // compare the lists.

            //


            //stackoverflow* " CollectionUtils.retainAll : Returns a collection containing all the elements in collection1 that are also in collection2."

            // check elements in both collections
            // trueIfCommenElements
            ArrayesHaveCommenNode = !Collections.disjoint(visitedlistA, visitedlistB);

            if(ArrayesHaveCommenNode){
                System.out.println("commom elements biBFS: "+ArrayesHaveCommenNode);

                //http://stackoverflow.com/questions/5943330/common-elements-in-two-lists
            visitedlistA.retainAll(visitedlistB);

                System.out.println("visitedlistA.size()  "+ visitedlistA.size());
                NodeObject meetInNode = (NodeObject) visitedlistA.get(0);


                if(visitedlistA.size()==1  ) {
                    System.out.println("meetInNode " +meetInNode.getUniqueXval()+","+meetInNode.getUniqueYval());
                    makepath(meetInNode);

                break;

                }
                else{
                    System.out.println("error bi BFS");
                }
            }

            // return true if in bothlist.

            System.out.println(" commom elements: "+ArrayesHaveCommenNode);

            // visitedlistA now contains only the elements which are also contained in visitedlistB.

        }






    }

    private void makepath(NodeObject meetInNode) {

        // make to list and put them toghter:
            // we know where the nodes camefrom make 2 camefrom list.

        // make camefrom A-> middle.

        // the final list should be going from A-> B, A= ghost B =pacman

        // first just add them all from A-middle
        NodeObject A_pathnode = meetInNode;


        while(A_pathnode.getA_cameFromBiBFS() != null){
            AToCommenNodepath.add(A_pathnode);

            if(colorOnBFS){

                A_pathnode.setRectColor(Color.BROWN);
            }

            // get previus node
            NodeObject previusNode = (NodeObject) A_pathnode.getcameFromBFS();


            System.out.println("A bi-BFS previusNode (x,y)  "+previusNode.getUniqueXval()+","+previusNode.getUniqueYval());



            // change the previus node to currentcenterNode
            A_pathnode =  previusNode;

        }

        // first just add them all from B-middle
        NodeObject B_pathnode = meetInNode;
        while(B_pathnode.getB_cameFromBiBFS() != null){
            AToCommenNodepath.add(A_pathnode);

            if(colorOnBFS){

                B_pathnode.setRectColor(Color.BLUE);
            }

            // get previus node
            NodeObject previusNode = (NodeObject) B_pathnode.getcameFromBFS();


            System.out.println("B bi-BFS previusNode (x,y)  "+previusNode.getUniqueXval()+","+previusNode.getUniqueYval());



            // change the previus node to currentcenterNode
            B_pathnode =  previusNode;

        }






    }

    private void FindChildrenForB(NodeObject currentCenterViewerNode) {
        // witch nodes are adjecent or children to this one?

        // is the node over under or left right a path node? is so make it a child

        // first get the coordinates of the node currentNode.

        int x =currentCenterViewerNode.getUniqueXval();
        int y = currentCenterViewerNode.getUniqueYval();



        //Note: coordinate system is wierd y goes from up t down , opposite of normal. therefore y-1 for up direction.
        //not wall and not visited , visited = false by default.

        System.out.println("finding children biBFS B");


        //up
        if (!nodeObject[x][y-1].isWall && !nodeObject[x][y-1].biBFSvisitedB ){
            //System.out.println("BFS up not wall not visited"+ x+","+ (y-1));
            // if not visited && not wall.  make it child
            // now ad it to queueWillOrHaveBeenVisited
            qB.add(nodeObject[x][y-1]);
            // make camefrom / parrent
            nodeObject[x][y-1].setB_cameFromBiBFS(currentCenterViewerNode);
            // add to visited list B
            visitedlistB.add(nodeObject[x][y-1]);

            System.out.println("B child bi BFS "+nodeObject[x][y-1].getUniqueXval()+","+nodeObject[x][y-1].getUniqueYval());

        }
        // down
        if (!nodeObject[x][y+1].isWall && !nodeObject[x][y+1].biBFSvisitedB){

            //System.out.println("BFS down not wall"+ x+","+ (y-1));

            // if not visited && not wall.  make it child
            // now add it to queueWillOrHaveBeenVisited
            qB.add(nodeObject[x][y+1]);
            visitedlistB.add(nodeObject[x][y+1]);

            // make camefrom / parrent
            nodeObject[x][y+1].setB_cameFromBiBFS(currentCenterViewerNode);
            System.out.println("B child bi BFS "+nodeObject[x][y+1].getUniqueXval()+","+nodeObject[x][y+1].getUniqueYval());


        }

        // left
        if (!nodeObject[x-1][y].isWall&& !nodeObject[x-1][y].biBFSvisitedB){
            // System.out.println("BFS left not wall"+ (x-1)+","+ y);
            // if not visited && not wall.  make it child
            // now add it to queueWillOrHaveBeenVisited
            qB.add(nodeObject[x-1][y]);
            visitedlistB.add(nodeObject[x-1][y]);

            // make camefrom / parrent
            nodeObject[x-1][y].setB_cameFromBiBFS(currentCenterViewerNode);
            System.out.println("B child bi BFS"+nodeObject[x-1][y].getUniqueXval()+","+nodeObject[x-1][y].getUniqueYval());

        }

        //right
        if (!nodeObject[x+1][y].isWall&& !nodeObject[x+1][y].biBFSvisitedB){
            //   System.out.println("BFS right not wall"+ (x+1)+","+ y);

            // if not visited && not wall.  make it child
            // now add it to queueWillOrHaveBeenVisited
            qB.add(nodeObject[x+1][y]);

            visitedlistB.add(nodeObject[x+1][y]);

            // make camefrom / parrent
            nodeObject[x+1][y].setB_cameFromBiBFS(currentCenterViewerNode);
            System.out.println("B child bi BFS"+nodeObject[x+1][y].getUniqueXval()+","+nodeObject[x+1][y].getUniqueYval());


        }






    }


    public void FindChildrenForA(NodeObject  currentCenterViewerNode) {
        System.out.println("finding children biBFS A");

        // witch nodes are adjecent or children to this one?

        // is the node over under or left right a path node? is so make it a child

        // first get the coordinates of the node currentNode.

        int x =currentCenterViewerNode.getUniqueXval();
        int y = currentCenterViewerNode.getUniqueYval();



        //Note: coordinate system is wierd y goes from up t down , opposite of normal. therefore y-1 for up direction.
        //not wall and not visited , visited = false by default.




        //up
        if (!nodeObject[x][y-1].isWall && !nodeObject[x][y-1].biBFSvisitedA ){
            //System.out.println("BFS up not wall not visited"+ x+","+ (y-1));
            // if not visited && not wall.  make it child
            // now ad it to queueWillOrHaveBeenVisited
            qA.add(nodeObject[x][y-1]);

            visitedlistA.add(nodeObject[x][y-1]);

            // make camefrom / parrent
            nodeObject[x][y-1].setACameFromBiBFS(currentCenterViewerNode);
            System.out.println("A child bi BFS"+nodeObject[x][y-1].getUniqueXval()+","+nodeObject[x][y-1].getUniqueYval());

        }
        // down
        if (!nodeObject[x][y+1].isWall && !nodeObject[x][y+1].biBFSvisitedA){

            //System.out.println("BFS down not wall"+ x+","+ (y-1));

            // if not visited && not wall.  make it child
            // now add it to queueWillOrHaveBeenVisited
            qA.add(nodeObject[x][y+1]);
            visitedlistA.add(nodeObject[x][y+1]);

            // make camefrom / parrent
            nodeObject[x][y+1].setACameFromBiBFS(currentCenterViewerNode);
            System.out.println("A child bi BFS"+nodeObject[x][y+1].getUniqueXval()+","+nodeObject[x][y+1].getUniqueYval());
        }

        // left
        if (!nodeObject[x-1][y].isWall&& !nodeObject[x-1][y].biBFSvisitedA){
            // System.out.println("BFS left not wall"+ (x-1)+","+ y);
            // if not visited && not wall.  make it child
            // now add it to queueWillOrHaveBeenVisited
            qA.add(nodeObject[x-1][y]);
            visitedlistA.add(nodeObject[x-1][y]);

            // make camefrom / parrent
            nodeObject[x-1][y].setACameFromBiBFS(currentCenterViewerNode);
            System.out.println("A child bi BFS"+nodeObject[x-1][y].getUniqueXval()+","+nodeObject[x-1][y].getUniqueYval());
        }

        //right
        if (!nodeObject[x+1][y].isWall&& !nodeObject[x+1][y].biBFSvisitedA){
            //   System.out.println("BFS right not wall"+ (x+1)+","+ y);

            // if not visited && not wall.  make it child
            // now add it to queueWillOrHaveBeenVisited
            qA.add(nodeObject[x+1][y]);
            visitedlistA.add(nodeObject[x+1][y]);

            // make camefrom / parrent
            nodeObject[x+1][y].setACameFromBiBFS(currentCenterViewerNode);
            System.out.println("A child bi BFS"+nodeObject[x+1][y].getUniqueXval()+","+nodeObject[x+1][y].getUniqueYval());

        }
        //else System.out.println("BFS, no more children to add for this Node");





    }
}
