import java.io.*;
import java.util.*;

/**
 * File: GreedyGraphSearch.java
 *
 * Author: Jason W Gould
 * Descr:  GreedyGraphSearch parses a text file and performs greedy (informed)
           graph search on the input nodes. See README.txt for instructions
 */
class GreedyGraphSearch
{
    private BufferedReader reader;
    private HashMap<Integer, Node> nodeList;
    private int start_x,
                start_y,
                goal_x,
                goal_y;
    private Node    startNode = null,
                    goalNode = null;

    public GreedyGraphSearch(int goal_x, int goal_y, int start_x, int start_y)
    {
        this.goal_x = goal_x;
        this.goal_y = goal_y;
        this.start_x = start_x;
        this.start_y = start_y;
    }

    // Perform greedy graph search for the goal node
    public void start()
    {
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        HashMap<Integer, Node> explored = new HashMap<>();

        int count = 0, maxSize = 0;
        // Add the start node to the queue
        frontier.add(startNode);
        while(!frontier.isEmpty())
        {
            // Get the head node from the queue
            Node node = frontier.poll();

            // Check if it's the goal node
            if(goalNode == node)
            {
                System.out.println("iter " + count + ":\tchecking: ("
                                + node.getVertex_X() + ","
                                + node.getVertex_Y() + "),"
                                + "\tgoal found!");
                traceGoalPath(goalNode, count, maxSize, explored);
                return;
            }

            // Add the node to explored list
            explored.put(node.getId(), node);

            // Print the status
            System.out.println("iter " + count + ":\tchecking: ("
                                + node.getVertex_X() + ","
                                + node.getVertex_Y() + "), "
                                + "\tfrontier size = " + frontier.size());

            // Add each of the successors to the frontier if they are not already explored
            for (Node n : node.successor())
            {
                if (!explored.containsKey(n.getId()) && !frontier.contains(n))
                {
                    n.setParent(node);
                    frontier.add(n);
                }
            }
            count++;
            if (maxSize < frontier.size())
            {
                maxSize = frontier.size();
            }
        }
        System.out.println("failure.");
    }

    // Determines the output path
    private void traceGoalPath(Node goal, int count, int maxSize, HashMap explored)
    {
        Stack<String> path = new Stack<>();
        System.out.println("\nsolution path:");

        path.add( " vertex (" + goal.getVertex_X() + ", "
                                + goal.getVertex_Y() + ")");
        int length = 0;
        Node parent = goal.getParent();
        while(parent != null)
        {
            path.push(" vertex (" + parent.getVertex_X() + ", " + parent.getVertex_Y() + ")");
            parent = parent.getParent();
            length++;
        }

        while(!path.isEmpty())
        {
            System.out.println(path.pop());
        }

        System.out.println("\ntotal iterations  = " + count);
        System.out.println("max frontier size = " + maxSize);
        System.out.println("vertices visited  = " + explored.size());
        System.out.println("path length       = " + length);
    }

    // Parse the input file (graph adjacency list format)
    public HashMap<Integer, Node> parseGraph(String fileName)
    {
        loadInputFile(fileName);

        nodeList = new HashMap<>();       // to hold the parsed Nodes
        try
        {
            String inputLine = null;
            // Read in each line of the input file
            while ((inputLine = reader.readLine()) != null)
            {
                Scanner entry = new Scanner(inputLine);
                String identifier = null;

                if (entry.hasNext())
                {
                    identifier = entry.next();

                    // Process the Vertex entries
                    if (identifier.equals("v"))
                    {
                        Node node = parseNode(entry);
                        nodeList.put(node.getId(), node);
                    }
                    // Process the Edge entries
                    else if (identifier.equals("e"))
                    {
                        parseEdge(entry);
                    }
                }
            }
            reader.close();

            if (startNode == null || goalNode == null)
            {
                throw new Exception("Bad start or goal node coordinates.");
            }

        } catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return nodeList;
    }

    // Parse an individual node entry
    private Node parseNode(Scanner entry)
    {
        Integer id = null, x_coord = null, y_coord = null;
        Node newNode = null;

        // Get the rest of the Vertex arguments
        if (entry.hasNext())
        {
            id = entry.nextInt();
            x_coord = entry.nextInt();
            y_coord = entry.nextInt();

            newNode = new Node(new Vertex(id, x_coord, y_coord), goal_x, goal_y);

            // Check if the coordinates match either start or goal node coords
            if (x_coord == start_x && y_coord == start_y)
            {
                startNode = newNode;
            }
            if (x_coord == goal_x && y_coord == goal_y)
            {
                goalNode = newNode;
            }
        }
        return newNode;
    }

    // Parses edge entries. Adds edges to the specified vertex's successor list
    private void parseEdge(Scanner entry)
    {
        // Get the initial node id
        if (entry.hasNext())
        {
            int nodeID = entry.nextInt();

            // If a node with the given id exists:
            if (nodeList.containsKey(nodeID))
            {
                // Get the initial node
                Node node = nodeList.get(nodeID);

                // Add the successors to the node
                while (entry.hasNext())
                {
                    // Read the given node id
                    int edgeNodeID = entry.nextInt();
                    // Get the node from id number
                    if (nodeList.containsKey(edgeNodeID))
                    {
                        Node edgeNode = nodeList.get(edgeNodeID);
                        node.addSuccessor(edgeNode);
                    } else
                    {
                        throw new NoSuchElementException("Edge node "
                                + edgeNodeID + " does not exist!");
                    }
                }
            } else
            {
                throw new NoSuchElementException("Node " + nodeID
                                            + " does not exist!");
            }
        } else
        {
            throw new NoSuchElementException("Input Format Error");
        }
    }

    // Loads a new Buffered Reader using the input file string
    private void loadInputFile(String fileName)
    {
        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            System.out.println("Valid input file: " + fileName);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}