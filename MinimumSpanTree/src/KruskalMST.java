import java.util.PriorityQueue;
import java.util.Vector;

public class KruskalMST<Weight extends Number & Comparable> {

    private Vector<Edge<Weight>> mst;   // 最小生成树所包含的所有边
    private Number mstWeight;           // 最小生成树的权值

    // 构造函数, 使用Kruskal算法计算graph的最小生成树
    public KruskalMST(WeightedGraph graph){
        mst = new Vector<>();
        PriorityQueue<Edge<Weight>> pq = new PriorityQueue<>(graph.E());
        UnionFind uf = new UnionFind(graph.V());
        for(int i = 0; i < graph.V(); i++)
            for(Object item: graph.adj(i)){
                Edge<Weight> e = (Edge<Weight>)item;
                if(e.v() <= e.w()){
                    pq.add(e);
                }
            }
        while(!pq.isEmpty() && mst.size() < graph.V()){
            Edge<Weight> e = pq.remove();
            if(uf.isConnected(e.v(), e.w()))
                continue;
            mst.add(e);
            uf.unionElements(e.v(), e.w());
        }

        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges(){
        return mst;
    }

    // 返回最小生成树的权值
    Number result(){
        return mstWeight;
    }


    // 测试 Kruskal
    public static void main(String[] args) {

        String filename = "testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Kruskal
        System.out.println("Test Kruskal:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        Vector<Edge<Double>> mst = kruskalMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ )
            System.out.println(mst.elementAt(i));
        System.out.println("The MST weight is: " + kruskalMST.result());

        System.out.println();
    }
}
