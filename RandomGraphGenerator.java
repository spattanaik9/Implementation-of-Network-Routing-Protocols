import java.util.*;

public class RandomGraphGenerator {
    private static int max_weight = 100;

    public static Graph generateGraphG1(int n, int degree){

        Random random = new Random();

        //create base structure of graph with n vertices which are connected in a cycle
        Graph G1 = getGraphBaseStructure(new Graph(n), n);
        //System.out.println("Base structure created");

        //to maintain average degree of 6, total number of edges should be n*6
        //so keep adding edges until total number of edges is less than n*6
        while(G1.edges < n*degree){
            int i = random.nextInt(n);
            int j = random.nextInt(n);
            int weight = random.nextInt(max_weight) + 1;
            if(i!=j){
                G1.addNewEdge(i, j, weight);
            }
        }
        return G1;
    }

    public static Graph generateGraphG2(int n, int probability){
        Random random = new Random();

        //create base structure of graph with n vertices which are connected in a cycle
        Graph G2 = getGraphBaseStructure(new Graph(n), n);

        //add edge when randomly generated prob is less than 20
        for(int i = 0; i< n; i++){
            int r, weight;
            for(int j = 0; j< n; j++){

                //random probability between 0 and 100
                r = random.nextInt(100);

                //random weight between 1 and max_weight
                weight = random.nextInt(max_weight) +1;

                //if the random probability generated out of 100 is less than 20, and i and j are not equal, then create an edge
                if((r < probability) && (i!=j)){
                    G2.addNewEdge(i, j, weight);
                }
            }
        }
        return G2;
    }
    private static Graph getGraphBaseStructure(Graph G, int n) {

        Random random = new Random();
        int random_weight;
        //creating a cycle so that all nodes are connected in the graph
        for(int i = 0; i<n-1; i++){
            random_weight = random.nextInt(max_weight)+1;
            G.addNewEdge(i, i+1, random_weight);
        }
        random_weight = random.nextInt(max_weight)+1;
        G.addNewEdge(0, n-1, random_weight);

        return G;
    }
}
