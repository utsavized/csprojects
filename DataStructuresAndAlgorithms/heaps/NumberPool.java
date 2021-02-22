package heaps;

class NumberPool {
    private long [] heap;
    private int size;
    private int leaf;
    private int root;
    public NumberPool(int size) {
        this.heap = new long[size];
        this.size = size;
        this.root = 0;
        this.leaf = -1;
    }
    
    public long checkout() throws Exception {
        return extractMin();
    }
    
    public void checkin(long number) {
        insert(number);
    }
    
    private long extractMin() throws Exception {
        long min = this.heap[root];
        this.heap[root] = this.heap[leaf];
        leaf--;
        int i = root;
        while(i >= root) {
            int minChild = getMin(2 * i + 1, 2 * i + 2);
            if(minChild > 0) {
                if(this.heap[i] > this.heap[minChild]) {
                    long temp = this.heap[i];
                    this.heap[i] = this.heap[minChild];
                    this.heap[minChild] = temp;
                }
            }
            i = minChild;
        }
        return min;
    }
    
    int getMin(int a, int b) throws Exception {
        if(a <= leaf && b <= leaf) {
            if(this.heap[a] < this.heap[b])
                return a;
            else
                return b;
        }
        else if(a <= leaf)
            return a;
        else if(b <= leaf)
            return b;
        else
            return -1;

    }
    
    private void insert(long number) {
        if(leaf == size - 1)
            resizeHeap();
        this.heap[++leaf] = number;
        heapify();
    }
    
    private void heapify() {
        int i = leaf;
        while(i > root) {
            int parent = (i - 1)/2;
            if(this.heap[parent] > this.heap[i]) {
                long temp = this.heap[i];
                this.heap[i] = this.heap[parent];
                this.heap[parent] = temp;
                i = parent;
            }
            else
                break;
                
        } 
    }
    
    private void resizeHeap() {
       //Resize heap to 2 times its size if full
       // this will help me to do the best out of this here 
    }
}