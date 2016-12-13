/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tue.we.li;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author s152022
 *
 * this class is used to create graph data based on the input parameters. three
 * files will be generated, namely: graph, vertices, labels
 */
/*
the format of graph file is as follows:
start_id,   label,  end_id
1,          1,      2

 "vertices" and "labels" store more information about vertices and labels.
 */
public class DataGenerator {

    final static int scale = 1;

    final static int numberOfVerticesFinal = 10000;
    final static int numberOfLabelsFinal = 50;
    final static int numberOfEdgesFinal = 8000;

    static int numberOfVertices = numberOfVerticesFinal / scale;
    static int numberOfLabels = numberOfLabelsFinal / scale;
    static int numberOfEdges = numberOfEdgesFinal / scale;

    public static void main(String[] s) {
        Random r = new Random();
//        vertices and labels are used only to build edges
/*
         the "real" vertices can be seen as a range[0, numberOfVertices]
         the "real" labels can be seen as a range[0, numberOfLabels]
        
         isolated islands can exist, labels can be left unused.
         */
        int[] vertices = r.ints(numberOfEdges * 2, 0, numberOfVertices).toArray();
        int[] labels = r.ints(numberOfEdges, 0, numberOfLabels).toArray();
        List<Edge> edgeList = new ArrayList<Edge>();
        for (int i = 0; i < numberOfEdges; i++) {
            Edge e = new Edge(vertices[i], vertices[i + numberOfEdges], labels[i]);
            edgeList.add(e);
        }
        File dir=new File("graphdata");
        dir.mkdir();
        File f = new File("graphdata/graph");
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(f));
            for (Edge e : edgeList) {
                bw.write(e.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Edge {

    int start;
    int end;
    int label;

    public Edge(int s, int e, int l) {
        start = s;
        end = e;
        label = l;
    }

    @Override
    public String toString() {
        return start + "," + label + "," + end;
    }
}
