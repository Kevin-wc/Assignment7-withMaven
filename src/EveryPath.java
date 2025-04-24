import java.util.*;

public class EveryPath {
    static class Edge {
        String from;
        String to;
        int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Edge> edges = new ArrayList<>();

    public static void addEdge(String from, String to, int weight) {
        edges.add(new Edge(from, to, weight));
    }

    public static void findPaths(String u, String w, int targetWeight) {
        depthFirstSearch(u, w, targetWeight, 0, new ArrayList<>());
    }

    private static void depthFirstSearch(String current, String w, int targetWeight, int currentWeight, List<String> path) {
        path.add(current);

        if (current.equals(w) && currentWeight == targetWeight) {
            System.out.println(path);
            return;
        }

        for (Edge edge : edges) {
            if (edge.from.equals(current) && !path.contains(edge.to)) {
                List<String> newPath = new ArrayList<>(path);
                depthFirstSearch(edge.to, w, targetWeight, currentWeight + edge.weight, newPath);
            }
        }
    }

    public static void main(String[] args) {
        addEdge("A", "B", 3);
        addEdge("A", "C", 2);
        addEdge("B", "D", 4);
        addEdge("C", "D", 5);
        addEdge("D", "E", 2);
        addEdge("C", "E", 5);
        String u = "A", w = "E";


        System.out.println("Simple paths from " + u + " to " + w + " with total weight 7:");
        findPaths(u, w, 7);
    }
}
