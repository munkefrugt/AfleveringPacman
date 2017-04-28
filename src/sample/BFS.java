package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by v on 4/26/17.
 */
// inspired by https://gist.github.com/gennad/791932

    // BFS Breath First Search.
public class BFS  {


     NodeObject[][] nodeObject;
    Setup setup;
    NodeObject currentCenterViewerNode;
    LinkedList<NodeObject> queueWillOrHaveBeenVisited;
    LinkedList<NodeObject> queueMakingpathforGhost;
    NodeObject pacmanNode;
    ArrayList<NodeObject> VisitedNodesList;
    //path for ghost to follow
    ArrayList<NodeObject> finalPathNodes;


    public BFS(Setup setup, NodeObject[][] nodeObject){

        this.nodeObject = nodeObject;
        this.setup = setup;



    }




    public void start(NodeObject startRootNode, NodeObject pacmanNode) {


        // queue each link is getting removed each time there is
        queueWillOrHaveBeenVisited = new LinkedList<NodeObject>();
        VisitedNodesList =new ArrayList<NodeObject>();



        NodeObject rootNode = startRootNode;
        queueWillOrHaveBeenVisited.add(rootNode);

        NodeObject firstElement = queueWillOrHaveBeenVisited.getFirst();
        System.out.println("firstElement "+ firstElement);

        // marke root node as visited
        rootNode.visited = true;

        VisitedNodesList.add(rootNode);
        // while the queue is not empty go through it and

        // good explanation //

        // in this while loop. while queue is not empty. still nodes to explore and see if they have children.
            //

        // make the root node the current Viewer CenterNode, the centernode is the one that explores the next nodes around it. looks

         currentCenterViewerNode = rootNode;

        // take the first rootnode and and make its camefrom = null ?

        // find the first children and add them to the queue. after that you start finding the rest and take them of one by one as you go through.



       // while(!queueWillOrHaveBeenVisited.isEmpty()) { // TODO change it back.
        int i = 0;
        while(!queueWillOrHaveBeenVisited.isEmpty()) {


            if(currentCenterViewerNode == pacmanNode){
                System.out.println("pink found found pacman, BFS");
                // now break out. of while loop
                break;
            }

            // take the first element of the queue and look for its neighbors / children..
            // set the new currentCenterViewerNode, the first node.
            currentCenterViewerNode = queueWillOrHaveBeenVisited.getFirst();
            //remove first

            queueWillOrHaveBeenVisited.removeFirst();
            // mark the currentCenterViewerNode as visited.
            currentCenterViewerNode.visited = true;
            VisitedNodesList.add(currentCenterViewerNode);

            //currentCenterViewerNode.setRectColor(Color.YELLOW);

            System.out.println("searching childnodes");
            findChildrenOfNode();

            //

            i++;




        }




        // clear visited
        clearNodes();

        // make path from the pink host to pacman



        makePath();


    }



    private void makePath() {

        // start remove last list.
        if(finalPathNodes != null ) {
            finalPathNodes.clear();
        }
        finalPathNodes = new ArrayList<NodeObject>();

        while(currentCenterViewerNode.getcameFromBFS() != null)
        {
            finalPathNodes.add(currentCenterViewerNode);
        // follow back from the pacman node to get to the ghost node.

        currentCenterViewerNode.setRectColor(Color.BROWN);

        // get previus node
        NodeObject previusNode = (NodeObject) currentCenterViewerNode.getcameFromBFS();

        previusNode.setRectColor(Color.BROWN);



        System.out.println("BFS previusNode (x,y)  "+previusNode.getUniqueXval()+","+previusNode.getUniqueYval());



        // change the previus node to currentcenterNode
        currentCenterViewerNode =  previusNode;


        }


    }

    public void clearNodes() {
        // clear each visited node.
        for (int i = 0; i < VisitedNodesList.size(); i++) {
            // make them all visited = false
            VisitedNodesList.get(i).visited = false;

        }

    }

    public void findChildrenOfNode() {

            // witch nodes are adjecent or children to this one?

                // is the node over under or left right a path node? is so make it a child

            // first get the coordinates of the node currentNode.

            int x =currentCenterViewerNode.getUniqueXval();
            int y = currentCenterViewerNode.getUniqueYval();



            //Note: coordinate system is wierd y goes from up t down , opposite of normal. therefore y-1 for up direction.
            //not wall and not visited , visited = false by default.




            //up
            if (!nodeObject[x][y-1].isWall && !nodeObject[x][y-1].visited ){
                System.out.println("BFS up not wall not visited"+ x+","+ (y-1));
                // if not visited && not wall.  make it child
                // now ad it to queueWillOrHaveBeenVisited
                queueWillOrHaveBeenVisited.add(nodeObject[x][y-1]);
                // make camefrom / parrent
                nodeObject[x][y-1].setCameFromBFS(currentCenterViewerNode);


            }
            // down
            if (!nodeObject[x][y+1].isWall && !nodeObject[x][y+1].visited){
                    System.out.println("BFS down not wall"+ x+","+ (y-1));
                // if not visited && not wall.  make it child
                    // now add it to queueWillOrHaveBeenVisited
                    queueWillOrHaveBeenVisited.add(nodeObject[x][y+1]);

                // make camefrom / parrent
                nodeObject[x][y+1].setCameFromBFS(currentCenterViewerNode);

                }

            // left
            if (!nodeObject[x-1][y].isWall&& !nodeObject[x-1][y].visited){
                System.out.println("BFS left not wall"+ (x-1)+","+ y);
                // if not visited && not wall.  make it child
                // now add it to queueWillOrHaveBeenVisited
                queueWillOrHaveBeenVisited.add(nodeObject[x-1][y]);

                // make camefrom / parrent
                nodeObject[x-1][y].setCameFromBFS(currentCenterViewerNode);

            }

            //right
            if (!nodeObject[x+1][y].isWall&& !nodeObject[x+1][y].visited){
                System.out.println("BFS right not wall"+ (x+1)+","+ y);

                // if not visited && not wall.  make it child
                // now add it to queueWillOrHaveBeenVisited
                queueWillOrHaveBeenVisited.add(nodeObject[x+1][y]);
                // make camefrom / parrent
                nodeObject[x+1][y].setCameFromBFS(currentCenterViewerNode);


            }
            else System.out.println("BFS, no more children to add for this Node");





    }

    public ArrayList<NodeObject> getFinalPathNodes() {

        return finalPathNodes;
    }

    public NodeObject BFSgetlastNodeInFinalPathNodesAndRemove() {
        // get last nodeObject in the list.

        // finalPathNodes.size()-1 because the first index number is 0.
        NodeObject lastinfinalPathNodes= finalPathNodes.get(finalPathNodes.size()-1);
        finalPathNodes.remove(finalPathNodes.size()-1);
        return lastinfinalPathNodes;
    }
}
