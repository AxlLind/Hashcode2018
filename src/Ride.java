public class Ride implements Comparable<Ride> {
    public Position start_pos, end_pos;
    public int start, end, id, distance;
    public Ride nearest;

    public Ride(int start_x,int start_y,int end_x,int end_y,int start,int end, int id) {
        this.start_pos = new Position(start_x, start_y);
        this.end_pos   = new Position(end_x, end_y);
        this.start = start;
        this.end = end;
        this.id = id;
        this.distance = start_pos.dist(end_pos);
    }

    @Override
    public int compareTo(Ride o) {
        return Integer.compare(this.start_pos.dist(),o.start_pos.dist());
    }
}
