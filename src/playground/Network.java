package playground;

public class Network {

    private Edge[] edges;

    public Network(Edge[] edges) {
        this.edges = edges;
    }

    public boolean isSimilar(Network other) {

        if(edges.length != other.edges.length) return false;
        int hashThis = 0;
        int hashOther = 0;

        for(int i = 0; i < edges.length; i++) hashThis += edges[i].getHash();
        for(int i = 0; i < other.edges.length; i++) hashOther += other.edges[i].getHash();

        return hashThis == hashOther;
    }

    public String getOut() {
        String s = "";
        for(int i = 0; i < edges.length; i++) {
            s += edges[i].getName() + ", ";
        }
        return s;
    }

}
