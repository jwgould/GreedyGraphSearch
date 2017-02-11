
/////////////////////////////////////////////////////////////
///                      README.txt                       ///
/////////////////////////////////////////////////////////////
Author:  Jason W Gould
Program: GreedyGraphSearch

============================================================
===                 FILES REQUIRED TO RUN                ===
============================================================

1. GraphSearch.java
2. GreedyGraphSearch.java
2. Vertex.java
3. Node.java 
4. NodeComparator.java
5. ATM_graph2.txt        (or other similar graph file)

============================================================
===                     INSTRUCTIONS:                    ===
============================================================

To compile and run the greedy graph search program:
    1 - Navigate to the folder containing the required files

    2 - Compile the source code with the following command:

    EX: javac GraphSearch.java

    3 - Run the program by calling the JVM with:

        java GraphSearch <graph> <start> <end>

        GraphSearch - The name of the compiled java program
        <graph>     - The filename of the desired search graph
        <start>     - The location of the start node, ex: 12 2
        <end>       - The location of the end node, ex: 16 18 
    
    EX: java GraphSearch ATM_graph2 20 1 1 20