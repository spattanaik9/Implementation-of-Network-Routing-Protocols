import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Testing {

    public static void main(String[] args){

        System.out.println("----------------- GRAPH G1, 5000 vertices and degree 6 -------- ");
        for(int i=0; i<5; i++) {

            System.out.println("\n\n ------------- Iteration# " +(i+1) + " -------------");
            //generate graph G1 with n nodes and degree d
            Graph g1 = RandomGraphGenerator.generateGraphG1(5000, 6);

            //generate random source and destination vertices between 0 and 4999
            Random random = new Random();
            int s = random.nextInt(5000);
            int t = random.nextInt(5000);

            while(s==t){
                t = random.nextInt(5000);
            }

            //to calculate time
            Instant start, end;

            //on graph 1
            System.out.println("\n\nThe maximum bandwidth path in Graph G1 from " + s + " to " + t + " is");
            //max bandwidth path between s and t using approach 1
            //approach 1 - using dijkstra without heap
            System.out.println("++++++  using Dijkstra without heap  ++++++");
            start = Instant.now();
            DijkstraMaxBW.dijkstra_bw(g1, s, t);
            end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

            //approach 2 - using Dijkstra with heap
            System.out.println("\n++++++  using Dijkstra with heap   ++++++");
            start = Instant.now();
            DijkstraMaxBW.dijkstra_bw_heap(g1, s, t);
            end = Instant.now();
            timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");


            //approach 3 - using Kruskal in which edges are sorted by HeapSort
            System.out.println("\n++++++  using Kruskal   ++++++");
            start = Instant.now();
            KruskalMaxBW.kruskalMaxBandwidth(g1, s, t);
            timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

        }


        System.out.println("\n\n\n----------------- GRAPH G2, 5000 vertices and probability 20 -------- ");
        for(int i=0; i<5; i++) {

            System.out.println("\n\n ------------- Iteration# " +(i+1) + " -------------");

            //generate graph G2 with n nodes and probability p
            Graph g2 = RandomGraphGenerator.generateGraphG2(5000, 20);
            //g2.printGraph();
            //generate random source and destination vertices between 0 and 4999
            Random random = new Random();
            int s = random.nextInt(5000);
            int t = random.nextInt(5000);

            while(s==t){
                t = random.nextInt(5000);
            }

            //to calculate time
            Instant start, end;
            Duration timeElapsed;
            //on graph 2
            System.out.println("\n\nThe maximum bandwidth path in Graph G2 from " + s + " to " + t + " is");
            //max bandwidth path between s and t using approach 1
            //approach 1 - using dijkstra without heap
            System.out.println("++++++  using Dijkstra without heap   ++++++");
            start = Instant.now();
            DijkstraMaxBW.dijkstra_bw(g2, s, t);
            end = Instant.now();
            timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

            //approach 2 - using Dijkstra with heap
            System.out.println("\n++++++  using Dijkstra with heap  ++++++");
            start = Instant.now();
            DijkstraMaxBW.dijkstra_bw_heap(g2, s, t);
            end = Instant.now();
            timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");


            //approach 3 - using Kruskal in which edges are sorted by HeapSort
            System.out.println("\n++++++  using Kruskal   ++++++");
            start = Instant.now();
            KruskalMaxBW.kruskalMaxBandwidth(g2, s, t);
            end = Instant.now();
            timeElapsed = Duration.between(start, end);
            System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");

        }

    }
}
