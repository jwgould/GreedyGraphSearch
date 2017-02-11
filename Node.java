import java.util.ArrayList;

/**
 * File: Node.java
 *
 * Author: Jason W Gould
 *
 * Descr: This class serves as a wrapper around the Vertex class for Graph Search
 */

public class Node implements Comparable<Node>
{
    private Vertex v;
    private Node parent;
    private int depth;

    private double hVal;                    // heuristic value

    private ArrayList<Node> successorList;
    // Constructor
    public Node(Vertex v, int goal_x, int goal_y)
    {
        this.v = v;
        this.parent = null;
        setHeuristic(goal_x, goal_y);
        successorList = new ArrayList<>();
    }

    public double getH() { return hVal; }

    public void setParent(Node node) { this.parent = node; }

    public Node getParent() { return this.parent; }

    public int getVertex_X() { return this.v.getX(); }

    public int getVertex_Y() { return this.v.getY(); }

    ArrayList<Node> successor() { return successorList; }

    public void addSuccessor(Node node) { successorList.add(node); }

    public int getId() { return v.getId(); }


    public String toString()
    {
        StringBuilder successorString = new StringBuilder("[");

        for (int i = 0; i < successorList.size(); i++)
        {
            Node node = successorList.get(i);
            successorString.append(node.getId());

            if ((successorList.size() - i) > 1)
            {
                successorString.append(", ");
            }
        }
        successorString.append("]");

        StringBuilder sb = new StringBuilder(   "<Node: " +
                                                v.toString() +
                                                " <Successors: " +
                                                successorString +
                                                ">" +
                                                ">");
        return sb.toString();
    }

    // Heuristic function - sets hVal as straight line distance from node to goal
    private void setHeuristic(int goal_x, int goal_y)
    {
        int x2 = this.v.getX();
        int y2 = this.v.getY();

        double x_sqrd = Math.pow((goal_x - x2), 2);
        double y_sqrd = Math.pow((goal_y - y2), 2);

        hVal = Math.sqrt(x_sqrd + y_sqrd);
    }

    @Override
    public int compareTo(Node node)
    {
        if (this.getH() < node.getH())
        {
            return -1;
        }
        else if (node.getH() > node.getH())
        {
            return 1;
        }
        return 0;
    }
}
