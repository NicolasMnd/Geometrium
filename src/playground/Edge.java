package playground;

public class Edge {

    private final Node from, to;

    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public int getHash() {
        return from.getId() + to.getId();
    }

    public Node getFrom() {
        return this.from;
    }

    public Node getTo() {
        return this.to;
    }

    public String getName() {
        return "[" + getFrom().getName() + " -> " + getTo().getName() + "]";
    }

}
