// 测试通过文件读取图的信息
public class Main {

    public static void main(String[] args) {

        String filename = "testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
        g.show();
        System.out.println();

        Path path = new Path(g,0);
        System.out.println("Path from 0 to 6 : ");
        path.showPath(6);

        ShortestPath shortestPath = new ShortestPath(g,0);
        System.out.println("ShortestPath from 0 to 6 : ");
        shortestPath.showPath(6);
    }
}
