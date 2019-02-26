public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz;

    public UnionFind3(int size){
        parent = new int[size];
        sz = new int[size];
        for(int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
            if(sz[pID] < sz[qID]) {
                parent[pID] = qID;
                sz[qID] += sz[pID];
            }
            else {
                parent[qID] = pID;
                sz[pID] += sz[qID];
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
        while(p != parent[p])
            p = parent[p];
        return p;
    }
}
