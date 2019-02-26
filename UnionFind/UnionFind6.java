public class UnionFind6 implements UF{

    private int[] parent;
    private int[] rank;

    public UnionFind6(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if(pID == qID)
            return;
        else {
            if(rank[pID] < rank[qID])
                parent[pID] = qID;
            else if(rank[pID] > rank[qID])
                parent[qID] = pID;
            else {
                parent[qID] = pID;
                rank[pID] += 1;
            }
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");
        if(p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }
}


