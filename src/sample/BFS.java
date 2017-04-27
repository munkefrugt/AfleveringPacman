package sample;

import java.util.LinkedList;

/**
 * Created by v on 4/26/17.
 */
// inspired by https://gist.github.com/gennad/791932

    // BFS Breath First Search.
public class BFS {


     NodeObject[][] nodeObject;
    Setup setup;
    NodeObject currentCenterViewerNode;

    public BFS(Setup setup, NodeObject[][] nodeObject){

        this.nodeObject = nodeObject;
        this.setup = setup;
        /*
                wiki version :
             Breadth-First-Search(Graph, root):
             will display the nodes

            // s is for result
            create empty set S
            // Q queue is for the next element to be checked,
            //each element that is first in the Q is the new one to start from

            create empty queue Q

            // the first element gets added to the result
            add root to S

            // add the first element since the root is forsure the first.
            Q.enqueue(root)

        // as long as there is soething in the Queue do following.
            while Q is not empty:
            // take the current node that is beeing checked of the queue
                current = Q.dequeue()
                if current is the goal:
                    return current

                    // check that each node that is "child"/ "adjacent" to the current node.
                for each node n that is adjacent to current:
                    if n is not in S:
                        add n to S
                        n.parent = current
                        Q.enqueue(n)


         */


       // start();
        //
        // */




    }




    public void start(NodeObject startRootNode) {
        /*
        // go through each node with BFS

        // BFS uses Queue data structure
        Queue queue = new LinkedList();
        // add the root node
        queue.add(this.rootNode);
        // print it
        printNode(this.rootNode);
        // marke root node as visited
        rootNode.visited = true;
        // while the queue is not empty go through it and

        // good explanation //
        while(!queue.isEmpty()) {

        // take the newest node.
            Node node = (Node)queue.remove();
            Node child=null;
            while((child=getUnvisitedChildNode(node))!=null) {
                child.visited=true;
                printNode(child);
                queue.add(child);
            }
        // Clear visited property of nodes
        clearNodes();
        }*/

        // queue each link is getting removed each time there is
        LinkedList<NodeObject> queueWillOrHaveBeenVisited = new LinkedList<NodeObject>();



        NodeObject rootNode = startRootNode;
        queueWillOrHaveBeenVisited.add(rootNode);

        NodeObject firstElement = queueWillOrHaveBeenVisited.getFirst();
        System.out.println("firstElement "+ firstElement);

        // marke root node as visited
        rootNode.visited = true;

        // while the queue is not empty go through it and

        // good explanation //

        // in this while loop. while queue is not empty. still nodes to explore and see if they have children.
            //

        // make the root node the current Viewer CenterNode, the centernode is the one that explores the next nodes around it. looks

         currentCenterViewerNode = rootNode;

        // take the first rootnode and and make its camefrom = null ?

        // find the first children and add them to the queue. after that you start finding the rest and take them of one by one as you go through.

        findChildrenOfNode();

        /*while(!queueWillOrHaveBeenVisited.isEmpty()) {


            // take the first element from the queue and look for its neighbors / children..



            /*if(currentCenterViewerNode == pacmanNode){
                System.out.println("pink found found pacman");
                break;
            }*/




        //}









    }

    public void findChildrenOfNode() {

        // witch nodes are adjecent or children to this one?

            // is the node over under or left right a path node? is so make it a child

        // first get the coordinates of the node currentNode.

        int x =currentCenterViewerNode.getUniqueXval();
        int y = currentCenterViewerNode.getUniqueYval();



        //down?
        int ycheck = y-1;
        if (!nodeObject[x][ycheck].isWall){
            System.out.println("BFS not wall"+ x+","+ ycheck);
            // if not visited && not wall.  make it child

        }





    }
}
