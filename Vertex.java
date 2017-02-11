/**
 * File: Vertex.java
 *
 * Author: Jason W Gould
 * Descr:  Serves as the base class for graph vertices
 */

public class Vertex
{
    private int x = 0, 
                y = 0, 
                id = 0;

    // Constructor
    public Vertex(int id, int x, int y)
    {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Vertex(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getId() {return id;}

    public int getX() {return this.x;}

    public int getY() {return this.y;}

    public String toString()
    {
        return "<Vertex " + this.id + ": x: " + this.x + " y: " + this.y + ">";
    }
}