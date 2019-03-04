import java.util.Stack;
import java.util.Vector;

public class BellmanFord<Weight extends Number & Comparable> {

    private WeightedGraph G;
    private int s;
    private Edge<Weight>[] from;
    private Number[] distTo;
    private boolean hasNegativeCycle;

    public BellmanFord(WeightedGraph graph, int s){
        G = graph;
        this.s = s;
        from = new Edge[G.V()];
        distTo = new Number[G.V()];
        from[s] = new Edge<Weight>(s, s, (Weight)(Number)(0.0));
        distTo[s] = 0;
        for(int pass = 1; pass < G.V(); pass++){
            for(int i = 0; i < G.V(); i++)
                for(Object item: G.adj(i)){
                    Edge<Weight> e = (Edge<Weight>)item;
                    if(from[e.v()] != null && (from[e.w()] == null || distTo[e.w()].doubleValue() > distTo[e.v()].doubleValue() + e.wt().doubleValue())){
                        distTo[e.w()] = distTo[e.v()].doubleValue() + e.wt().doubleValue();
                        from[e.w()] = e;
                    }
                }
            }
        hasNegativeCycle = detectNegativeCycle();
    }

    private boolean detectNegativeCycle(){
        for(int i = 0; i < G.V(); i++)
            for(Object item: G.adj(i)){
                Edge<Weight> e = (Edge<Weight>)item;
                if(from[e.v()] != null && (from[e.w()] == null || distTo[e.w()].doubleValue() > distTo[e.v()].doubleValue() + e.wt().doubleValue()))
                    return true;
            }
        return false;
    }

    boolean negativeCycle(){
        return hasNegativeCycle;
    }

    Number shortestPathTo( int w ){
        assert w >= 0 && w < G.V();
        assert !hasNegativeCycle;
        assert hasPathTo(w);
        return distTo[w];
    }

    boolean hasPathTo( int w ){
        assert( w >= 0 && w < G.V() );
        return from[w] != null;
    }

    Vector<Edge<Weight>> shortestPath(int w){

        assert w >= 0 && w < G.V() ;
        assert !hasNegativeCycle ;
        assert hasPathTo(w) ;

        // 通过from数组逆向查找到从s到w的路径, 存放到栈中
        Stack<Edge<Weight>> s = new Stack<Edge<Weight>>();
        Edge<Weight> e = from[w];
        while( e.v() != this.s ){
            s.push(e);
            e = from[e.v()];
        }
        s.push(e);

        // 从栈中依次取出元素, 获得顺序的从s到w的路径
        Vector<Edge<Weight>> res = new Vector<Edge<Weight>>();
        while( !s.empty() ){
            e = s.pop();
            res.add(e);
        }

        return res;
    }

    void showPath(int w){

        assert( w >= 0 && w < G.V() );
        assert( !hasNegativeCycle );
        assert( hasPathTo(w) );

        Vector<Edge<Weight>> res = shortestPath(w);
        for( int i = 0 ; i < res.size() ; i ++ ){
            System.out.print(res.elementAt(i).v() + " -> ");
            if( i == res.size()-1 )
                System.out.println(res.elementAt(i).w());
        }
    }
}
