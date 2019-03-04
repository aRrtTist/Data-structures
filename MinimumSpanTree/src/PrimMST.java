import java.util.Vector;

public class PrimMST<Weight extends Number & Comparable> {

    private WeightedGraph G;
    private IndexMinHeap<Weight> ipq;
    private Edge<Weight>[] edgeTo;
    private boolean[] marked;
    private Vector<Edge<Weight>> mst;
    private Number mstWeight;

    public PrimMST(WeightedGraph graph){
        G = graph;
        assert (graph.E() >= 1);
        ipq = new IndexMinHeap<>(graph.V());
        marked = new boolean[graph.V()];
        edgeTo = new Edge[G.V()];
        mst = new Vector<>();
        visit(0);
        while (!ipq.isEmpty()){
            int v = ipq.extractMinIndex();
            assert(edgeTo[v] != null);
            mst.add(edgeTo[v]);
            visit(v);
        }
        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    private void visit(int v){
        assert !marked[v];
        marked[v] = true;
        for(Object item: G.adj(v)) {
            Edge<Weight> e = (Edge<Weight>) item;
            int w = e.other(v);
            if (!marked[w]) {
                if (edgeTo[w] == null) {
                    edgeTo[w] = e;
                    ipq.insert(w, e.wt());
                } else if (e.wt().compareTo(edgeTo[w].wt()) < 0) {
                    edgeTo[w] = e;
                    ipq.change(w, e.wt());
                }
            }
        }
    }

    Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    Number result(){
        return mstWeight;
    }

    public static void main(String[] args) {

        String filename = "testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.out.println("Test Prim MST:");
        PrimMST<Double> primMST = new PrimMST<Double>(g);
        Vector<Edge<Double>> mst = primMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + primMST.result());

        System.out.println();
    }
}
