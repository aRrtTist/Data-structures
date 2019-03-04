import java.util.Stack;
import java.util.Vector;

public class Path {

    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] from;

    // 构造函数, 寻路算法, 寻找图graph从s点到其他点的路径
    public Path(Graph graph, int s){
        assert s >= 0 && s < graph.V();
        G = graph;
        this.s = s;
        visited = new boolean[G.V()];
        from = new int[G.V()];
        for(int i = 0; i < G.V(); i++) {
            from[i] = -1;
            visited[i] = false;
        }
        dfs(s);
    }

    // 图的深度优先遍历
    private void dfs( int v ){
        visited[v] = true;
        for(int i: G.adj(v)){
            if(!visited[i]){
                from[i] = v;
                dfs(i);
            }
        }
    }

    // 查询从s点到w点是否有路径
    boolean hasPath(int w){
        return visited[w];
    }

    // 查询从s点到w点的路径, 存放在vec中
    Vector<Integer> path(int w){
        assert hasPath(w);
        Stack<Integer> stack = new Stack<>();
        while(w != -1){
            stack.push(w);
            w = from[w];
        }
        Vector<Integer> vector = new Vector<>();
        while(!stack.isEmpty())
            vector.add(stack.pop());
        return vector;
    }

    // 打印出从s点到w点的路径
    void showPath(int w){
        Vector<Integer> vector = new Vector<>();
        vector = path(w);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < vector.size(); i++){
            sb.append(vector.get(i));
            if(i != vector.size() - 1)
                sb.append("->");
        }
        System.out.println(sb.toString());
    }
}
