import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

import java.util.*;

public class DrawGraph {

    static class Vertex {
        String name;
        int distance;

        Vertex(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }
    }

    public static List<Vertex> parseVertices(String input) {
        input = input.replaceAll("[\\[\\](){}]", "").replace(" ", "");
        List<Vertex> vertices = new ArrayList<>();
        if (input.isEmpty()) return vertices;

        String[] parts = input.split(",");
        for (int i = 0; i < parts.length; i += 2) {
            String name = parts[i];
            int distance = Integer.parseInt(parts[i + 1]);
            vertices.add(new Vertex(name, distance));
        }
        return vertices;
    }

    public static void buildAndDisplayGraph(List<Vertex> vertices) {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("Circular Directed Graph");

        // adding the nodes
        for (Vertex v : vertices) {
            graph.addNode(v.name).setAttribute("ui.label", v.name);
        }

        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Vertex v = vertices.get(i);
            int left = (i - v.distance + n) % n;
            int right = (i + v.distance) % n;

            String from = v.name;
            String toLeft = vertices.get(left).name;
            String toRight = vertices.get(right).name;

            // avoid duplication and code breaks
            String edgeIdLeft = "edge_" + from + "_to_" + toLeft + "_L";
            String edgeIdRight = "edge_" + from + "_to_" + toRight + "_R";

            if (!from.equals(toLeft) && graph.getEdge(edgeIdLeft) == null) {
                graph.addEdge(edgeIdLeft, from, toLeft, true);
            }
            if (!from.equals(toRight) && !toLeft.equals(toRight) && graph.getEdge(edgeIdRight) == null) {
                graph.addEdge(edgeIdRight, from, toRight, true);
            }

        }

        // style drip points
        graph.setAttribute("ui.stylesheet",
                "node { size: 25px; fill-color: lightblue; text-size: 18px; text-alignment: center; }" +
                        "edge { arrow-shape: arrow; arrow-size: 12px, 8px; fill-color: gray; }");

        graph.display();
    }

    public static void main(String[] args) {
        String input = "[ (I, 2) , (A, 5) , (E, 4) , (F,2) , (T, 2) , (S, 3) ]";
        List<Vertex> vertices = parseVertices(input);
        buildAndDisplayGraph(vertices);
    }
}
