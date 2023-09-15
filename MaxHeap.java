public class MaxHeap
{
    private final int[] H;
    //vertex "values" are given in array D.
    private final int[] D;

    //position of vertices in the heap
    private final int[] P;

    public int heapSize;

    public MaxHeap(int v){
        H = new int[v];
        D = new int[v];
        P = new int[v];
        heapSize = 0;
    }

    public int maximum(){
        return H[0];
    }

    public void insert(int vertex, int bw){

        H[heapSize] = vertex;
        D[vertex] = bw;
        P[vertex] = heapSize;

        //traverse and fix heap
        int current = heapSize;
        int parent = (current-1)/2;
        //bandwidth of parent is less than bw of current node
        while( current > 0 && parent >=0 && (D[H[parent]] < D[H[current]])){
            swap(current, parent);
            current = parent;
            parent = (current-1)/2;
        }
        heapSize++;

    }

    public void delete(int vertex){

        int position = P[vertex];

        swap(position, heapSize-1);
        H[heapSize-1] = -1;
        D[vertex] = -1;
        P[vertex] = -1;
        heapSize--;

        heapify(position);
    }

    public void heapify(int i){

        int largest = i;
        int left = 2*i + 1 ;
        int right = 2*i + 2;

        if(left < heapSize && D[H[left]] > D[H[largest]]){
            largest = left;
        }
        if(right < heapSize && D[H[right]] > D[H[largest]]){
            largest = right;
        }
        if(largest != i){
            swap(largest, i);
            heapify(largest);
        }
    }

    public void swap(int i, int j){
        int temp = H[i];
        H[i] = H[j];
        H[j] = temp;
        int pos = P[H[i]];
        P[H[i]] = P[H[j]];
        P[H[j]] = pos;
    }

}
