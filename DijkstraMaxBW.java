import java.util.*;

public class DijkstraMaxBW {

    public static String[] status;
    public static Integer[] bw;
    public static Integer[] dad;
    //*****************      Approach 1     ***************************
    //the below method accepts a graph G, and two vertices s and t, and prints maximum bandwidth path
    //using a modification of Dijkstra's algorithm without using a heap structure
    public static void dijkstra_bw(Graph G, int s, int t){
        int n = G.vertices;

        status = new String[n];
        bw = new Integer[n];
        dad = new Integer[n];

        //initialize all vertices as unseen, bw as 0 and dad as 0
        for(int i=0; i<n; i++){
             status[i] = "unseen";
             bw[i] = Integer.MAX_VALUE;
        }

        status[s] = "in-tree";
        bw[s] = Integer.MAX_VALUE;
        dad[s] = -1;

        //adjacency list of vertex s
        List<Edge> edges = G.adjacencyList[s];
        for(Edge edge : edges){

            //w is the neighbor vertex of s
            int w = edge.destination;
            status[w] = "fringer";
            bw[w] = edge.weight;
            dad[w] = s;
        }

        while(!status[t].equals("in-tree")){
            int max_bw = Integer.MIN_VALUE;
            int v = -1;
            //find fringer with largest bandwidth value
            for(int i=0; i<G.vertices; i++){
                if(status[i].equals("fringer")) {
                    if (bw[i] > max_bw) {
                        max_bw = bw[i];
                        v = i;
                    }
                }
            }
            status[v] = "in-tree";

            //adjacency list of vertex v
            edges = G.adjacencyList[v];

            for(Edge edge : edges){

                //w is the neighbor vertex of s
                int w = edge.destination;
                if(status[w].equals("unseen")){
                    status[w] = "fringer";
                    bw[w] = Math.min(bw[v], edge.weight);
                    dad[w] = v;
                }
                else if(status[w].equals("fringer") && (bw[w] < Math.min(bw[v], edge.weight))){
                    dad[w] = v;
                    bw[w] = Math.min(bw[v], edge.weight);
                }

            }
        }
        printMaxBandwidthPath(dad, s, t, bw[t]);

    }


    //*****************      Approach 2     ***************************
    //the below method accepts a graph G, and two vertices s and t, and prints maximum bandwidth path
    //using a modification of Dijkstra's algorithm with using a heap structure for fringers
    public static void dijkstra_bw_heap(Graph G, int s, int t){
        int n = G.vertices;

        String[] status = new String[n];
        Integer[] bw = new Integer[n];
        Integer[] dad = new Integer[n];

        //initialize all vertices as unseen, bw as 0 and dad as 0
        for(int i=0; i<n; i++){
            status[i] = "unseen";
            bw[i] = Integer.MAX_VALUE;
        }

        status[s] = "in-tree";
        dad[s] = -1;

        //maintain a set to keep track of all fringers
        MaxHeap heap = new MaxHeap(n);

        //adjacency list of vertex s
        List<Edge> edges = G.adjacencyList[s];
        for(Edge edge : edges){

            //w is the neighbor vertex of s
            int w = edge.destination;
            status[w] = "fringer";
            bw[w] = edge.weight;
            dad[w] = s;
            heap.insert(w, bw[w]);
        }

        // while there are fringers
        while(!status[t].equals("in-tree")){
        //while(heap.heapSize>0){
            int v = heap.maximum();
            status[v] = "in-tree";

            heap.delete(v);

            //adjacency list of vertex v
            edges = G.adjacencyList[v];

            for(Edge edge : edges){

                //w is the neighbor vertex of s
                int w = edge.destination;
                if(status[w].equals("unseen")){
                    status[w]="fringer";
                    bw[w] = Math.min(bw[v], edge.weight);
                    dad[w] = v;
                    heap.insert(w, bw[w]);
                }
                else if(status[w].equals("fringer") && (bw[w] < Math.min(bw[v], edge.weight))){
                    //delete from heap and insert again
                    heap.delete(w);
                    dad[w] = v;
                    bw[w] = Math.min(bw[v], edge.weight);
                    heap.insert(w, bw[w]);
                }
            }
        }
        printMaxBandwidthPath(dad, s, t, bw[t]);
    }

    private static void printMaxBandwidthPath(Integer[] dad, int s, int t, int bandwidth) {
        /*Stack<Integer> stack = new Stack<>();
        List<Integer> path = new ArrayList<>();
        stack.push(t);
        int i = t;
        while(dad[i] != -1){
            stack.push(dad[i]);
            i = dad[i];
        }
        while(!stack.empty()){
            path.add(stack.pop());
        }

        System.out.println("The maximum bandwidth path from " + s + " to " + t + " is");
        //print nodes from the path list
        for(int j = 0; j< path.size()-1; j++){
            System.out.print(path.get(j) + " -> ");
        }
        System.out.print(path.get(path.size()-1));*/

        int[] stack = new int[dad.length];
        int stackSize=0;
        int vertex = t;
        while(dad[vertex] != -1){
            stack[stackSize] = vertex;
            stackSize++;
            vertex = dad[vertex];
        }
        stack[stackSize] = vertex;

        //System.out.println("The maximum bandwidth path from " + s + " to " + t + " is");

        for(int i=stackSize; i>0; i--){
            System.out.print(stack[i] + " -> ");
        }
        System.out.print(stack[0]);


        System.out.println("\nMaximum bandwidth = " + bandwidth);
    }
}
