package playground;

import java.util.ArrayList;
import java.util.List;

public class ControlNetwork {

    Node node1 = new Node(0);
    Node node2 = new Node(1);
    Node node3 = new Node(10);
    Node node4 = new Node(100);
    Node node5 = new Node(1000);
    Node node6 = new Node(10000);
    Node node7 = new Node(100000);
    private Node[] nodes = new Node[]{node1, node2, node3, node4, node5, node6, node7};
    private final Edge[] possibleEdges;
    private List<Network> foundNetworks = new ArrayList<>();
    private int previousLength = 0;

    public ControlNetwork() {
        this.possibleEdges = constructEdges();
        this.previousLength = 0;
    }

    public List<Network> getFoundNetworks() {
        return foundNetworks;
    }

    private Edge[] constructEdges() {
        List<Edge> edges = new ArrayList<>();
        for(int i = 0; i < nodes.length; i++) {
            for(int j = 0; j < nodes.length; j++) {
                if(i != j) {
                    Edge e = new Edge(nodes[i], nodes[j]);
                    if(isValidEdge(e.getHash())) {
                        edges.add(e);
                    }
                }
            }
        }
        return edges.toArray(new Edge[0]);
    }

    private Edge[] copy(Edge[] previous, Edge additional) {
        Edge[] newArray = new Edge[previous.length+1];
        System.arraycopy(previous, 0, newArray, 0, previous.length);
        newArray[previous.length] = additional;
        return newArray;
    }

    private boolean isValidEdge(int hash) {
        //             1&6              6&7         1&4             2&4             2&5             3&5             7&3                 5&7             5&6                 4&7         4&6
        return hash == 10000 || hash == 110000 || hash == 100 || hash == 101 || hash == 1001 || hash == 1010 || hash == 100010 || hash == 101000 || hash == 11000 || hash == 100100 || hash == 10100;
    }

    public boolean examine(Edge[] edges) {
        // No false edges & duplicates
        List<Integer> foundHashes = new ArrayList<>();
        for(int i = 0; i < edges.length; i++) {
            int hash = edges[i].getHash();
            if(!isValidEdge(hash)) return false;
            if(foundHashes.contains(hash)) return false;
            foundHashes.add(hash);
        }
        return true;
    }

    public void expand(Edge[] edges) {
        //System.out.println("Expanding network: " + (new Network(edges)).getOut());
        for(int i = 0; i < possibleEdges.length; i++) {
            Edge[] newArray = copy(edges, possibleEdges[i]);
            Network net = new Network(newArray);
            solve(newArray);
        }
    }

    public void solve(Edge[] collection) {
        if(examine(collection) && collection.length <= 7) {

            if(previousLength != collection.length) {
                previousLength = collection.length;
                System.out.println("Currently having " + foundNetworks.size() + " solutions");
            }

            Network net = new Network(collection);
            boolean hasFound = false;

            for(int i = 0; i < foundNetworks.size(); i++) {
                if(foundNetworks.get(i).isSimilar(net)) hasFound = true;
            }

            if(!hasFound) this.foundNetworks.add(net);

            //System.out.println("Currently having " + foundNetworks.size() + " solutions");

            expand(collection);
        }
    }

}
