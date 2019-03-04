public class Components {

    Graph G;
    private boolean[] visited;
    private int ccount;
    private int[] id;

    public Components(Graph graph){
        G = graph;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        ccount = 0;
        for(int i = 0; i < G.V(); i++){
            visited[i] = false;
            id[i] = -1;
        }

        for(int i = 0; i < G.V(); i++)
            if(!visited[i]) {
                dfs(i);
                ccount++;
            }
    }

    private void dfs(int v){
        visited[v] = true;
        id[v] = ccount;
        for(int i: G.adj(v))
            if(!visited[i])
                dfs(i);
    }

    public int count(){
        return ccount;
    }

    boolean isConnected(int v, int w){
        assert v >= 0 && v < G.V();
        assert w >= 0 && w < G.V();
        return id[v] == id[w];
    }
}
