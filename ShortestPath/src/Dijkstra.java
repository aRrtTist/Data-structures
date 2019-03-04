import java.util.Stack;
import java.util.Vector;

public class Dijkstra<Weight extends Number & Comparable> {

    private WeightedGraph G;
    private int s;
    private Number[] distTo;
    private boolean[] marked;
    private Edge<Weight>[] from;

    public Dijkstra(WeightedGraph graph, int s){
        G = graph;
        assert s >= 0 && s < G.V();
        this.s = s;
        distTo = new Number[G.V()];
        marked = new boolean[G.V()];
        from = new Edge[G.V()];
        for( int i = 0 ; i < G.V() ; i ++ ){
            distTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }
        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(G.V());
        distTo[s] = 0;
        marked[s] = true;
        from[s] = new Edge<Weight>(s, s, (Weight)(Number)(0.0));
        ipq.insert(s, (Weight)distTo[s]);
        while(!ipq.isEmpty()){
            int v = ipq.extractMinIndex();
            marked[v] = true;
            for(Object item: G.adj(v)){
                Edge<Weight> e = (Edge<Weight>)item;
                int w = e.other(v);
                if(!marked[w]){
                    if(from[w] == null || distTo[w].doubleValue() > distTo[v].doubleValue() + e.wt().doubleValue()){
                        distTo[w] = distTo[v].doubleValue() + e.wt().doubleValue();
                        from[w] = e;
                        if(ipq.contains(w))
                            ipq.change(w, (Weight)distTo[w]);
                        else
                            ipq.insert(w, (Weight)distTo[w]);
                    }
                }
            }
        }
    }

    Number shortestPathTo(int w){
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);
        return distTo[w];
    }

    public boolean hasPathTo(int w){
        assert w >= 0 && w < G.V() ;
        return marked[w];
    }

    Vector<Edge<Weight>> shortestPath(int w){
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);
        Stack<Edge<Weight>> stack = new Stack<>();
        Edge<Weight> e = from[w];
        while(e.v() != s){
            stack.push(e);
            e = from[e.v()];
        }
        stack.push(e);
        Vector<Edge<Weight>> res = new Vector<>();
        while(!stack.isEmpty())
            res.add(stack.pop());
        return res;
    }

    void showPath(int w){
        assert w >= 0 && w < G.V();
        assert hasPathTo(w);
        Vector<Edge<Weight>> path =  shortestPath(w);
        for( int i = 0 ; i < path.size() ; i ++ ){
            System.out.print( path.elementAt(i).v() + " -> ");
            if( i == path.size()-1 )
                System.out.println(path.elementAt(i).w());
        }
    }

}
