import java.util.PriorityQueue;
import java.util.Vector;

public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph<Weight> G;
    private PriorityQueue<Edge<Weight>> pq;
    private boolean[] marked;
    private Vector<Edge<Weight>> mst;
    private Number mstWeight;

    public LazyPrimMST(WeightedGraph<Weight> graph){
        G = graph;
        pq = new PriorityQueue<>();
        marked = new boolean[G.V()];
        mst = new Vector<>();

        visit(0);
        while (!pq.isEmpty()){
            Edge<Weight> e = pq.remove();
            if(marked[e.v()] == marked[e.w()])
                continue;
            mst.add(e);
            if(!marked[e.v()])
                visit(e.v());
            else
                visit(e.w());
        }

        mstWeight = mst.elementAt(0).wt();
        for(int i = 1; i < mst.size(); i++)
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    private void visit(int v){
        marked[v] = true;
        for(Edge<Weight> i: G.adj(v))
            if(!marked[i.other(v)])
                pq.add(i);
    }

    Vector<Edge<Weight>> mstEdges(){
        return mst;
    };

    Number result(){
        return mstWeight;
    };
}
