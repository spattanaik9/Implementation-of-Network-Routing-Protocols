import java.lang.reflect.Array;
import java.util.*;
public class KruskalMaxBW {
    private static Graph mst;
    private static int[] dad;
    private static int[] rank;
    private static int maxBandwidth;


    public static void kruskalMaxBandwidth(Graph g, int s, int t){
        //sort edges in decreasing order using heapsort
        Edge[] sortedEdgeArr = heapSort(g);

        //union find to make a maximum spanning tree of above sorted edges
        dad = new int[g.vertices];
        rank = new int[g.vertices];
        for(int i = 0; i< g.vertices; i++){
            dad[i] = i;
            rank[i] = 1;
        }

        mst = new Graph(g.vertices);
        for(Edge e: sortedEdgeArr){
            int u = e.source;
            int v = e.destination;

            int rank1 = find(u);
            int rank2 = find(v);
            if(rank1 != rank2){
                mst.addNewEdge(u, v, e.weight);
                union(rank1, rank2);
            }
        }

        //find max bandwidth path between s and t using DFS
        boolean[] visited = new boolean[mst.vertices];

        for(int i=0; i<mst.vertices; i++){
            visited[i] = false;
            //dfsDad[i] = -1;
        }
        ArrayList<Integer> stack = new ArrayList<>();
        int bandwidth = Integer.MAX_VALUE;
        dfs(s, t, visited, stack, bandwidth);

    }

    public static void dfs(int s, int t, boolean[] visited, ArrayList<Integer> stack, int bandwidth){
        stack.add(s);
        if( s == t){
            maxBandwidth = bandwidth;
            printPath(stack, maxBandwidth);
            return;
        }
        visited[s] = true;
        ArrayList<Edge> edges = mst.adjacencyList[s];
        if(edges.size() > 0){
            for(Edge e : edges){
                //if node is not visited
                if(!visited[e.destination]){
                    dfs(e.destination, t, visited, stack, Math.min(bandwidth, e.weight));
                }
            }
        }
        stack.remove(stack.size() - 1);

    }

    //method to build max heap of the all the edges in graph G
    public static Edge[] heapSort(Graph g){
        int n = g.edges;
        Edge[] edgeArray = new Edge[n];
        int index=0;
        for(int i=0; i<g.vertices; i++){
            for(Edge e : g.adjacencyList[i]){

                edgeArray[index]=e;
                index++;
            }
        }
        //the index of last non-leaf node
        int start = n/2 - 1;

        //build a min heap
        //reverse level order traversal from last non-leaf node till root node, and heapify each node
        for(int i=start; i>=0; i--){
            heapify(edgeArray, n, i);
        }

        //one by one extract an element from heap and call heapify on reduced heap
        for(int i = n-1; i > 0; i--){
            //move root to end
            Edge temp = edgeArray[0];
            edgeArray[0] = edgeArray[i];
            edgeArray[i] = temp;

            heapify(edgeArray,i, 0);
        }

        return edgeArray;
    }

    //to heapify a subtree rooted at i, and n is the size of heap at this point
    public static void heapify(Edge[] arr, int n, int i){
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        //If left child is less than root
        if(left < n && arr[left].weight < arr[largest].weight)
            largest = left;

        //if right child is less than largest so far
        if(right < n && arr[right].weight < arr[largest].weight)
            largest = right;

        if(largest != i){
            Edge tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;

            //recursively heapify the affected sub tree
            heapify(arr, n, largest);
        }
    }

    private static int find(int v){
        int u = v;
        while(dad[u] != u){
            u = dad[u];
        }
        return u;
    }

    public static void union(int v1, int v2){
        if(rank[v1] > rank[v2]){
            dad[v2] = v1;
        }
        else if(rank[v1] < rank[v2]){
            dad[v1] = v2;
        } else{
            dad[v1] = v2;
            rank[v2]++;
        }
    }

    public static void printPath(ArrayList<Integer> stack, int bandwidth){
        for(int i = 0; i< stack.size()-1; i++){
            System.out.print(stack.get(i) + " -> ");
        }
        System.out.print(stack.get(stack.size() - 1));
        System.out.println("\nMaximum bandwidth = " + bandwidth);
    }
}
