import java.util.Comparator;

/**
 * File:   NodeComparator.java
 * Author: Jason W Gould
 *     
 * Desc:   Implements a compare function to be used for  
 *         the priority queue in Greedy Graph Search
 */

public class NodeComparator implements Comparator<Node>
{
    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.getH() < node2.getH())
        {
            return -1;
        }
        else if (node1.getH() > node2.getH())
        {
            return 1;
        }
        return 0;
    }
}
