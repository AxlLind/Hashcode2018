import java.util.*;

public class Position {
    int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int dist(Position a) {
        return Math.abs(this.x-a.x) + Math.abs(this.y - a.y);
    }

    public int dist() {
        return dist(new Position(0,0));
    }
}
