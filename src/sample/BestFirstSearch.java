package sample;

import javafx.scene.Group;
import javafx.scene.paint.Color;

import java.util.*;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by v on 5/3/17.
 */
public class BestFirstSearch {




    // best first search can be a * algorithm,
    // "The A* search algorithm is an example of best-first search," - wikipedia

    // so I made that my best first search.

        boolean showAstarpathWithColor = true;


        final Setup setup;
        int orangegostPosYUpdate;
        int orangegostPosXUpdate;
        NodeObject goalNode;
        // the neighbornodes that are not walls are added to list list for, where they later are evaluated, for finding lowest f.
        Map<NodeObject, Double> OpenlistUncheckedNeighbors = new HashMap<NodeObject, Double>();

        // Closed list are visited nodes, Initially, only the start node is known. and visited,
        // we add it to the closed list in the constructor.
        ArrayList<NodeObject> closedList = new ArrayList<NodeObject>();

        // list for the Astar final path.
        LinkedList<NodeObject> finalPathNodesBestFirst = new LinkedList<NodeObject>();

        //initialize the open list
        // insert the currently discovered nodes that are not evaluated yet.
        // Initially, only the start node is known.
        // nodes that needs to be checked.
        // we add nodes to openlist for each step when the A* tries to find the next values.
        // !! note!! not not all nodes will have pass through the openlist in the and, some will most likely remain untouch ,
        // in the comming a-star search.
        ArrayList<NodeObject> openList = new ArrayList<NodeObject>();



        int CurrentCenterNodeX;
        int CurrentCenterNodeY;
        int startX ;
        int startY ;
        NodeObject orangeGostNodeAstarStartingPos;
        //
        NodeObject currentCenterNode = orangeGostNodeAstarStartingPos;


        // the node that its is currently checking from is the current center node, from here it finds neighbors,
        // in each step as the a-star goes along, the center node is changed.


        int boxesY;
        int boxesX;
        int blockSize;
        NodeObject[][] nodeObject;
        Group root;
        //int g =10; // can be changed if needed.

        int goalX, goalY;

        private int stepValueG = 1;

        int testvalue;
        // start with the pacman location
        // now just testing.
    /*
    psudo code in steps:

    initialize the open list
    initialize the closed list
    put the starting node on the open list (you can leave its f at zero)


    */


        public BestFirstSearch(Setup setup, int orangegostPosXUpdate, int orangegostPosYUpdate, int orangeGhostPosX, int orangeGhostPosY, int pacmanPosX, int pacmanPosY, Group root, NodeObject[][] nodeObject, int blockSize, int boxesX, int boxesY){

            //empty the old list of OpenlistUncheckedNeighbors
            //OpenlistUncheckedNeighbors.clear();


            // check the size of the array's
            System.out.println("OpenlistUncheckedNeighbors size "+ OpenlistUncheckedNeighbors.size());
            System.out.println("closedList size "+ closedList.size());
            System.out.println("openList size "+ openList.size());


            //closedList.clear();
            //openList.clear();


            this.orangegostPosXUpdate = orangegostPosXUpdate;
            this.orangegostPosYUpdate = orangegostPosYUpdate;

            System.out.println("testvalue astar  "+ testvalue);
            System.out.println("astar start orangegostPosXUpdate+ orangegostPosYUpdate"+ orangegostPosXUpdate+","+orangegostPosYUpdate);
            System.out.println("astar start orangeGhostPosX+ orangegostPosY"+ orangeGhostPosX+","+orangeGhostPosY);
            //  the goal is pacman
            // the start is the ghost
            goalX = pacmanPosX;
            goalY = pacmanPosY;
            startX = orangegostPosXUpdate;
            startY = orangegostPosYUpdate;




            this.setup = setup;
            this.root=root;
            this.nodeObject = nodeObject;
            this.blockSize = blockSize;
            this.boxesX = boxesX;
            this.boxesY = boxesY;



            // starts here

            orangeGostNodeAstarStartingPos = nodeObject[startX][startY];
            currentCenterNode = nodeObject[startX][startY];
            currentCenterNode.takeOfOpenListBestFirst();
            //Make  goal node.
            goalNode= nodeObject[goalX][goalY];
            //nodeObject[goalX][goalY].setRectColor(Color.orange);
            System.out.println(" best first pacman node x,y"+ nodeObject[goalX][goalY].getUniqueXval() + ","+ nodeObject[goalX][goalY].getUniqueYval());
            System.out.println(" orangeGhost node x,y"+ nodeObject[startX][startY].getUniqueXval() + ","+ nodeObject[startX][startY].getUniqueYval());


            // replace "true" with is openlistempty..
            int i=0;


            // the main loop that makes the a-star  **********************************
            //while the open list is not empty


            // i<3 || true // TODO replace with true
            while(true)
            // TODO remember g costs get added. each node will have a gvalue, g = g value of where it came from plus new g cost.

            {
                i++;
                System.out.println("Openbest first listUncheckedNeighbors size "+ OpenlistUncheckedNeighbors.size());

                System.out.println("bestFirst currentCenterNode (x,y)  "+currentCenterNode.getUniqueXval()+","+currentCenterNode.getUniqueYval());
                System.out.println("bestFirst goalNode (x,y)  "+goalNode.getUniqueXval()+","+goalNode.getUniqueYval());

                // is current node = goal? then break out.

                // find the goal for the a star. this is walking from pack man to the ghost
                if (currentCenterNode == goalNode)
                {
                    // jump out of loop
                    System.out.println("bestFirst goal node is found , break *******************************");
                    break;
                }





                // get the new node onto the closed list.
                //showContentOfLists();

                // show

                closedList.add(currentCenterNode);




                // when nodes are checked they go on closed list.


                // put the starting node on the closed list (you can leave its f at zero)

                // pretend start very top corners inside boarder

                //startNode

                closedList.add(nodeObject[startX][startY]);
                nodeObject[startX][startY].setFZero();
                nodeObject[startX][startY].makeGreen();



                CurrentCenterNodeX= currentCenterNode.getUniqueXval(); //nodeObject[startX][startY].getUniqueXval();
                CurrentCenterNodeY= currentCenterNode.getUniqueYval();//nodeObject[startX][startY].getUniqueYval();


                //System.out.println("startnode  x,y"+ nodeObject[1][1].getUniqueXval() + ","+ nodeObject[1][1].getUniqueYval());
                // end node is in opposite corner



                //find the node with the least f on the open list, call it "q", search the neighbors
                // it has max 4 neighborsNodes
                System.out.println("make neighborsNodes");
                // find the neighborNodes
                findAndcheckNeighborsNodes();
                findLowestFAndmakeNewCenterNode();




                currentCenterNode.takeOfOpenListBestFirst();




                System.out.println("************ i = "+i);
                System.out.println(CurrentCenterNodeX);
                System.out.println(CurrentCenterNodeY);






                int currentNodeH = currentCenterNode.getBestFirstH();
                System.out.println("bestFirst currentNodeH? = "+ currentNodeH);
                System.out.println("currentCenterNode(x,y)"+currentCenterNode.getUniqueXval() + ","+ currentCenterNode.getUniqueYval());
                System.out.println(":::::::::::::::::::::::::::::::WHILE end::::::::::::::::::::::::");



            }

            // what is the f and h and g?


            goalNode.setRectColor(Color.RED);

            // now when the goal is found track the way back.
            makeThePath();



        }








        private void makeThePath() {





            //int i =0;
            while(true)
            {

                if (currentCenterNode == orangeGostNodeAstarStartingPos)
                {
                    System.out.println("Best first path done , and path have been followed all the way back trough all the " +
                            "previus nodes to where it came from, this should be the ghost  "+currentCenterNode.getUniqueXval()+","+currentCenterNode.getUniqueYval());

                    // path done
                    setup.orangeIsMade =true;
                    break;
                }

                // add the nodes to the final pathlist.
                finalPathNodesBestFirst.add(currentCenterNode);





                // change to next node.
                NodeObject previusNode = (NodeObject) currentCenterNode.getCameFromBestFirst();

                if(showAstarpathWithColor)
                {

                    previusNode.setRectColor(Color.ORANGE);
                }

                System.out.println("previusNode (x,y)  "+previusNode.getUniqueXval()+","+previusNode.getUniqueYval());


                // change the previus node to currentcenterNode
                currentCenterNode =  previusNode;
                //i++;

                // add every step to an arraylist.






            }

        }

        private void findLowestFAndmakeNewCenterNode() {

            //
            //print the list
            System.out.println("BestFirst print OpenlistUncheckedNeighbors "+Arrays.asList(OpenlistUncheckedNeighbors));


            // TODO !!!find out how this works...
            System.out.println("best first find lowest F");

            if(OpenlistUncheckedNeighbors.isEmpty())
            {
                System.out.println("best first OpenlistUncheckedNeighbors is empty");
            }

            Map.Entry<NodeObject, Double> min = Collections.min(OpenlistUncheckedNeighbors.entrySet(), new Comparator<Map.Entry<NodeObject, Double>>() {
                public int compare(Map.Entry<NodeObject, Double> entry1, Map.Entry<NodeObject, Double> entry2) {
                    return entry1.getValue().compareTo(entry2.getValue());
                }
            });

            System.out.println("Min F value printed ++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(min.getValue());
            System.out.println("its key:");
            System.out.println(min.getKey());
            System.out.println();

            NodeObject newLowestNode = min.getKey();

            // set it new centerNode
            currentCenterNode = newLowestNode;


            if(showAstarpathWithColor)
            {
                currentCenterNode.setRectColor(Color.PINK);
            }

            closedList.add(newLowestNode);



            // remove key pair from OpenlistUncheckedNeighbors List
            OpenlistUncheckedNeighbors.remove(min.getKey());



            //note where the  nextnode camefrom, if came from the Currentcenternode. that is done already in the previus method.



        }



        private void printMap(TreeMap<Integer, NodeObject> map) {

            for (Map.Entry<Integer, NodeObject> entry : map.entrySet()) {
                System.out.println("Key : " + entry.getKey()
                        + " Value : " + entry.getValue());
            }

        }

        // check above
        private void findAndcheckNeighborsNodes() {
            System.out.println("findAndcheckNeighborsNodes  Bestfirst");
            // this method checks the 4 surrounding neighbors above below left and right.

            // check above
            // check if its a wall
            // and is not on closed list has to be true






            // if not wall and not on closedList
            NodeObject nodeAbove = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY - 1];

            System.out.println("CurrentCenterNode"+CurrentCenterNodeX+","+CurrentCenterNodeY);
            System.out.println();
            System.out.println(nodeAbove.getisWall()+"****************************######################  Bestfirst");
            if (!nodeAbove.getisWall() && !nodeAbove.isOnClosedListBestFirst())
            {
                // todo set came form for each node.
                // the currentcenternode is the camefrom node. since thats the node where there is been checked from. !!



                nodeAbove.setCameFromBestFirst(currentCenterNode);
                System.out.println("node above not wall");
                if(showAstarpathWithColor)
                {
                    nodeAbove.setRectColor(Color.BLUE);
                }
                int h= getAndcalculateH(nodeAbove);
                System.out.println("h->Astar = "+h);
                // store h in node.
                nodeAbove.setBestFirstH(h);
                int hFromObject = nodeAbove.getBestFirstH();
                System.out.println("hFromObject" +hFromObject);



                // calculate g. g = current g value + new g value, so the g value in the end when the goal is reached is the
                //  "total  walking distance"

                nodeAbove.BestFirstCalculateG(stepValueG);
                int g= nodeAbove.getBestFirstG();
                int f= nodeAbove.getBestFirstG()+h;
                System.out.println("F = "+f +"="+g+"+"+h);
                System.out.println("coordinates = " +nodeAbove.getUniqueXval()+ ", "+ nodeAbove.getUniqueYval());


                // add to treemap openlist
                // insert f and node
                double fDouble = (double) f;
                OpenlistUncheckedNeighbors.put(nodeAbove,fDouble);



            }
            else if(!nodeAbove.isOnClosedListBestFirst()){
                System.out.println("nodeAbove is on closed list");
            }
            else {
                System.out.println("wall at y : " + (CurrentCenterNodeY - 1));
            }


            // if wall is false
            // check below

            NodeObject nodebelow = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY + 1];
            System.out.println(!nodebelow.isOnClosedListBestFirst());
            //&& !nodebelow.isOnClosedListBestFirst()
            if(!nodebelow.getisWall() && !nodebelow.isOnClosedListBestFirst() )
            {
                // if node is goal node break out of loop.
                //if (nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1] = )

                System.out.println("node below not wall");
                // set camefrom
                nodebelow.setCameFromBestFirst(currentCenterNode);
                int nodeUnderY = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].getUniqueYval();

                if(showAstarpathWithColor)
                {

                    nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].setRectColor(Color.BLUE);
                }


                System.out.println("abovenodeY" + nodeUnderY);

                // calculate  first :
                int h= getAndcalculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);
                nodebelow.BestFirstCalculateG(stepValueG);
                int g= nodebelow.getBestFirstG();

                int f= g+h;

                System.out.println("best first F = "+f +"="+g+"+"+h);
                //int h = calculateH(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);
                //int h = nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1].calculateAndGetH();
                //neighborsNodes.add(nodeObject[CurrentCenterNodeX][CurrentCenterNodeY+1]);



                // insert f and node
                System.out.println("coordinates = " +nodebelow.getUniqueXval()+ ", "+ nodebelow.getUniqueYval());
                double fDouble =  (double) f;
                OpenlistUncheckedNeighbors.put(nodebelow,fDouble);



            }
            else if(!nodebelow.isOnClosedListBestFirst()){
                System.out.println("nodebelow is on closed list");
            }

            else {
                System.out.println("wall at y : " + (CurrentCenterNodeY + 1));
            }

            // check left
            NodeObject nodeLeft = nodeObject[CurrentCenterNodeX - 1][CurrentCenterNodeY];

            // && !nodeLeft.isOnClosedList()
            if (!nodeLeft.getisWall() && !nodeLeft.isOnClosedListBestFirst())
            {
                // set camefrom
                nodeLeft.setCameFromBestFirst(currentCenterNode);
                System.out.println("node left not wall");
                if(showAstarpathWithColor)
                {

                    nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY].setRectColor(Color.BLUE);
                }

                int h= getAndcalculateH(nodeObject[CurrentCenterNodeX-1][CurrentCenterNodeY]);
                nodeLeft.BestFirstCalculateG(stepValueG);
                int g= nodeLeft.getBestFirstG();
                int f= g+h;
                System.out.println("best first F = "+f +"="+g+"+"+h);
                System.out.println("coordinates = " +nodeLeft.getUniqueXval()+ ", "+ nodeLeft.getUniqueYval());


                // insert f and node
                double FDouble =  (double) f;
                OpenlistUncheckedNeighbors.put(nodeLeft,FDouble);

            }
            else if(!nodeLeft.isOnClosedListBestFirst()){
                System.out.println("nodeLeft is on closed list");
            }
            else {
                System.out.println("wall at X : " + (CurrentCenterNodeX - 1));
            }

            // check right
            NodeObject noderight = nodeObject[CurrentCenterNodeX + 1][CurrentCenterNodeY];
            //&& !noderight.isOnClosedList()
            if (!noderight.getisWall() && !noderight.isOnClosedListBestFirst())
            {
                // set camefrom
                noderight.setCameFromBestFirst(currentCenterNode);

                System.out.println("node right not wall");

                if(showAstarpathWithColor)
                {

                    nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY].setRectColor(Color.BLUE);
                }

                int h= getAndcalculateH(nodeObject[CurrentCenterNodeX+1][CurrentCenterNodeY]);

                noderight.BestFirstCalculateG(stepValueG);
                int g= noderight.getBestFirstG();
                int f= g+h;
                System.out.println("best first F = "+f);
                System.out.println("coordinates = " +noderight.getUniqueXval()+ ", "+ noderight.getUniqueYval());

                // insert f and node
                double fDouble = (double) f;
                OpenlistUncheckedNeighbors.put(noderight,fDouble);


            }
            else if(!noderight.isOnClosedListBestFirst()) {
                System.out.println("noderight is on closed list");
            }
            else {
                System.out.println("wall at X : " + (CurrentCenterNodeX + 1));
            }


        }

        public int getAndcalculateH(NodeObject nodeObject) {
            // h is calculated as manhatten heuristics

            // ex. h= 9-3 + 9-2;

            int CurrentNeighborX = nodeObject.getUniqueXval();
            int CurrentNeighborY = nodeObject.getUniqueYval();
            int h = Math.abs(CurrentNeighborX-goalX)   + Math.abs(CurrentNeighborY - goalY);
            System.out.println("BestFirst getAndcalculateH CurrentNeighborX,Y = " +CurrentNeighborX+ ", "+ CurrentNeighborY);
            System.out.println("BestFirst getAndcalculateH goalX,Y = " +goalX+ ", "+ goalY);
            System.out.println("h = "+h);




            return h;
        }

        public void clearPath() {
            currentCenterNode.setRectColor(Color.BLACK);
            OpenlistUncheckedNeighbors.clear();
            closedList.clear();
            finalPathNodesBestFirst.clear();
            // reset currentCenterNode to ghostNode , the ghost is the chaser. pacman is target.


        }


        //public int getorangeGhostPosX() { // TODO is it used?
         //   return orangeGhostPosX;
        //}

        public void moveorangegostalongPath() {

            // the ghost is walking now.
            // the the first element andmake that the new posotion
            // relocate the orange ghost

            //finalPathNodesBestFirst.get(0);


        }







        public void showContentOfLists() {

            if(closedList == null)
            {
                System.out.println("closedList empty" );
            }

            for (int i = 0; i < closedList.size(); i++) {

                System.out.println("show closedList"+closedList.get(i));
            }
        }

        public void clearContentOfLists() {
            System.out.println("clearList");
            closedList.clear();
            // now show
            showContentOfLists();
        }

        public void sendTestvalue(int test) {
            testvalue = test;
        }


    public void orangewalks() {

    }
}
