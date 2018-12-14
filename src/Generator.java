/**
 * JGraphT : a free Java graph-theory library
 */

import org.jgrapht.*;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.io.*;
import java.util.*;

public class Generator {
    public static void main(String args[]) {

        class Edge {
            int source;
            int dest;

            public Edge(int s, int dest) {
                this.source = s;
                this.dest = dest;
            }

            @Override
            public int hashCode() {
                return source + dest + 1;
            }

            @Override
            public boolean equals(Object obj) {
                Edge s = (Edge) obj;
                return s.source == source &&
                        s.dest == dest;
            }
        }

        Scanner sc = new Scanner(System.in);

        int vertices = sc.nextInt();
        int edges = sc.nextInt();

        Writer writer = null;

        Set<Edge> set = new HashSet<>();

        Random random = new Random();
        int range = edges - vertices - 1;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("testBig.in"), "utf-8"));
            writer.write(vertices + " " + edges + " " + 1 + "\n");
            for (int i = 1; i < edges; i++) {
                int source = 1 + random.nextInt(range);
                int dest =  1 + random.nextInt(range);
                Edge edge = new Edge(source, dest);
                set.add(edge);

                for(Edge edge_in_set : set) {
                    if(edge_in_set.dest == edge.source && edge_in_set.source == edge.dest) {
                        edge.source = 1 + random.nextInt(range);
                        edge.dest =  1 + random.nextInt(range);
                    }
                }
            }
            int range1 = 1000 - 1;
            for(Edge edge : set) {
                int cost = 1 + random.nextInt(range1);
                writer.write(edge.source + " " + edge.dest + " " + cost + '\n');
            }
        } catch (IOException ex) {

        } finally {
            try {
                writer.close();
            } catch (Exception ex) {

            }
        }

    }
}
