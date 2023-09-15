import java.util.*;

// this class stores the edges of the graph
class Edge {

    public int source, destination, weight;

    Edge(int s, int d, int w) {
        source = s;
        destination = d;
        weight = w;
    }

}

public class Graph {
     int vertices;

     int edges;

     ArrayList<Edge>[] adjacencyList;

    //constructor
    Graph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;
        adjacencyList = new ArrayList[vertices];

        //initialize adjacency lists of all vertices
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
    }

    //this method adds an edge between source and destination vertices
    public  void addNewEdge(int source, int destination, int weight) {
        if (!containsEdge(source, destination)) {
            Edge edge1 = new Edge(source, destination, weight);
            Edge edge2 = new Edge(destination, source, weight);
            adjacencyList[source].add(edge1);
            adjacencyList[destination].add(edge2);

            edges +=2;
        }

    }

    //this method checks if the graph has an edge or not
    public  boolean containsEdge(int s, int d) {

        ArrayList<Edge> edges = adjacencyList[s];
        if (edges.size() > 0) {
            for (Edge edge : edges) {
                if (edge.destination == d)
                    return true;
            }
        }
        return false;
    }

    //this method returns the degree of a vertex
    public int degree(int node) {
        ArrayList<Edge> edges = adjacencyList[node];
        return edges.size();
    }

    public  void printGraph() {
        for (int i = 0; i < vertices; i++) {
            ArrayList<Edge> edges = adjacencyList[i];

            for (Edge edge : edges) {
                System.out.println("vertex " + i + " is connected to -> " + edge.destination + " with weight " + edge.weight);
            }
        }
    }

    public static void printEdges(List<Edge> sortedEdges) {
        System.out.println("************  Sorted edges  ***********");

        for(int i=1; i<sortedEdges.size(); i++){
            System.out.println(sortedEdges.get(i).source + "->" + sortedEdges.get(i).destination + " weight " + sortedEdges.get(i).weight);
        }
    }

}