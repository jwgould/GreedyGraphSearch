import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * File: GraphSearch.java
 *
 * Author: Jason W Gould
 *
 * Desc: This class serves as the "Main" program for searching graphs
 */

public class GraphSearch {

    public static void main(String[] args)
    {
        if (args.length == 5)
        {
            // Get the user-input file name
            String fileName = args[0];
            try
            {
                // Get the start and goal node, x and y vals
                int start_x = Integer.parseInt(args[1]);
                int start_y = Integer.parseInt(args[2]);
                int goal_x = Integer.parseInt(args[3]);
                int goal_y = Integer.parseInt(args[4]);

                GreedyGraphSearch search = new GreedyGraphSearch( goal_x,
                                                                    goal_y,
                                                                    start_x,
                                                                    start_y);
                // Process the nodes from the input file
                HashMap<Integer, Node> graphList
                                        = search.parseGraph(fileName);

                // Uncomment next line to print the input nodes/edges
                // graphList.values().forEach(System.out::println);

                search.start();

            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Incorrect number of input values.");
        }
    }
}
