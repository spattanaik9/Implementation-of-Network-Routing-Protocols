
public class NetworkOptimization{
    public static void main(String[] args) {

        //Question 1

        int n = 5000;
        int d = 6;

        int p = 20;
        //generate graph G2 with n nodes and probability p
        final Graph g2 = RandomGraphGenerator.generateGraphG2(n, p);
        System.out.println("Graph.java G2 generated");
        //g.printGraph();


        //generate graph G1 with n nodes and degree d
        final Graph g1 = RandomGraphGenerator.generateGraphG1(n, d);
        System.out.println("Graph.java G1 generated");
        //g1.printGraph();

        //Routing routing = new Routing();
        //find max bandwidth path between vertices s and t
        final int s = 10;
        final int t = 2;

        //approach 1 - using dijkstra without heap
        System.out.println("\n********     using approach 1   ********");
        DijkstraMaxBW.dijkstra_bw(g1, s, t);

        //approach 2 - using Dijkstra with heap
        System.out.println("\n********     using approach 2   ********");
        DijkstraMaxBW.dijkstra_bw_heap(g1, s, t);


        //approach 3 - using Kruskal in which edges are sorted by HeapSort
        System.out.println("\n********     using approach 3   ********");
        //KruskalMaxBW krus = new KruskalMaxBW();
         KruskalMaxBW.kruskalMaxBandwidth(g1, s, t);

    }
}

