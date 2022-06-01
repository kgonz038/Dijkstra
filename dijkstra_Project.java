import java.util.Scanner;
import java.util.*;

/**
 * Kimberly Gonzalez
 *
 * This program asks the user to input a chosen source node and a given digraph via an adjacency 2-d matrix.
 * Dijkstra's Shortest Path Algorithm is used in order to find/display the shortest distance and path from the chosen
 * source node/vertex to all other nodes in the digraph.
 */
public class dijkstra_Project
{
        static char vertSource;
        static int currDist[] = new int[10];
        static int nodeChecked[] = new int[10];
        static int atSource = -5;

        public static void main(String[] args)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.print("\nPlease choose the source node (a to j): ");

            char vertexSource = scanner.next().charAt(0);; //saves user input

            vertSource = vertexSource;

            //converts the char letter source node into its alphabetical letter int number
            int vNode = ((int)vertexSource - 97);

            int tNodes= 1;
            int initial = 0;
            int ind = 0;

            //shortest path of source
            int[] shortPath = new int[10];

            //the provided lecture adjacency matrix
            int digraph[][] = new int[][]{
                    //a  b  c  d  e  f  g  h  i  j
                    {0, 0, 0, 0, 1, 0, 0, 10, 0, 0}, // a
                    {0, 0, 2, 0, 0, 0, 0, 0, 0, 0},  // b
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // c
                    {4, 0, 0, 0, 0, 0, 0, 1, 0, 0},  // d
                    {0, 0, 0, 0, 0, 3, 0, 0, 0, 0},  // e
                    {0, 1, 3, 0, 0, 0, 7, 0, 1, 0},  // f
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // g
                    {0, 0, 0, 0, 5, 0, 0, 0, 9, 0},  // h
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 2},  // i
                    {0, 0, 0, 0, 0, 0, 1, 0, 0, 0} };// j

            //to all nodes
            while (initial <= 9)
            {
                //classifies the node as not checked
                nodeChecked[initial] = -5;

                //distance are initially infinity
                currDist[initial] = Integer.MAX_VALUE;

                initial++;
            }
            shortPath[vNode] = atSource; //classifies it as a source vertex node

            currDist[vNode] = 0; //source node distance

            //goes through all nodes and finds the shortest distance
            while(tNodes < 10)
            {
                int vDist = Integer.MAX_VALUE;
                int unVisitDist = -1;

                for (int j = 0; j < 10; j++)
                {
                    if ( currDist[j] <= vDist && nodeChecked[j] == -5)
                    {
                        unVisitDist = j;
                        vDist = currDist[j];
                    }
                }
                //vertex with the shortest distance
                int v = unVisitDist;

                nodeChecked[v] = -10; //classifies the node as already checked

                for (int k = 0; k < 10; k++)
                {
                    //checks the nearest node's distances
                    if (nodeChecked[k] == -5  && currDist[v] + digraph[v][k] < currDist[k] && currDist[v] != Integer.MAX_VALUE && digraph[v][k] != 0)
                    {
                        //shortest vertex distance added to shortest path
                        shortPath[k] = v;
                        //Adds the distance of paths
                        currDist[k] = currDist[v] + digraph[v][k];
                    }
                }
                tNodes++;
            }
            System.out.print("\n-----------------------------------------------------------");
            System.out.print("\n  GOAL |    SHORTEST DISTANCE    |       PATHING    ");
            System.out.print("\n-----------------------------------------------------------");
            char letter = 'a';

            //displays for all nodes
            while (ind <= 9)
            {
                //if a node is not reachable from source vertex
                if (currDist[ind] > 50)
                {
                    System.out.print("\n   "+ letter + "   |      Unreachable        |  Unreachable    " );
                }
                //otherwise prints the total shortest distance of the destination goal node from the source node
                else
                {
                    System.out.print("\n   " + letter + "   |           " + currDist[ind]);

                    //when the node in question is the source vertex, no path to print for the source node itself
                    if (currDist[ind] == 0)
                    {
                        System.out.print("             |  The Source Node");
                    }
                    //otherwise for all other nodes that aren't the source vertex, prints the path
                    else
                    {
                        if (currDist[ind] < 10)
                        {
                            System.out.print("             |  " );
                            System.out.print(vertSource +  "" );
                        }
                        else
                        {
                            System.out.print("            |  ");
                            System.out.print(vertSource +  "" );
                        }
                        shortestPathing(shortPath, ind, vNode);
                    }
                }
                letter++;
                ind++;
            }
            System.out.println(" ");
        }

        /**
         * Recursively, will print the next node in the path that was found with the shortest distance. Stops the recursive
         * calls when the node is the source vertex node.
         *
         * @param shortPath all nodes with shortest path
         * @param i each index node
         * @param sNode the chosen source vertex node
         */
        static void shortestPathing(int shortPath[], int i, int sNode)
        {
            //when next node is the chosen node, because total distance is 0. Stops recursively calling
            while(currDist[sNode] == 0 && shortPath[i] == atSource)
            {
                System.out.print(":");
                return;
            }
            //whenever the next node is the source node, stops recursively calling
            while(shortPath[i] == atSource)
            {
                System.out.print(":");
                return;
            }
            //recursively calls this method again for all shortest found nodes
            shortestPathing(shortPath, shortPath[i], sNode);

            //prints out th node in char letter format instead of converted int letter order number
            System.out.print("- " + ((char) ('a' + i)) + " ");
        }
    }


