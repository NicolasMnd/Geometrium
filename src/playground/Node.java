package playground;

public class Node {

    private final int name;

    public Node(int name) {
        this.name = name;
    }

    public int getId() {
        return this.name;
    }

    public int getName() {
        switch(name) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 10:
                return 3;
            case 100:
                return 4;
            case 1000:
                return 5;
            case 10000:
                return 6;
            case 100000:
                return 7;
            default:
                return -1;
        }
    }

}
