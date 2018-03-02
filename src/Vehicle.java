import java.util.*;

public class Vehicle {
    Position pos, desiredPos;
    ArrayList<Ride> rides;
    boolean gettingToRide;
    int steps_to_wait;
    Random rand = new Random();

    public Vehicle() {
        pos = new Position(0,0);
        rides = new ArrayList<>();
        desiredPos = new Position(0,0);
    }

    public void addRide(Ride r) {
        rides.add(r);
        desiredPos = r.start_pos;
    }

    public int points(Ride r, int time, int bonus, ArrayList<Ride> rides, Vehicle[] vs) {
        // ignore rides you cannot complete
        if (r.end - time < (pos.dist(r.start_pos) + r.distance))
            return Integer.MIN_VALUE;
        int points = 0;

        if (r.start-time >= pos.dist(r.start_pos)) {
            // it's good to get the bonus, but bad to wait
            points += bonus;
            points -= r.start - (time + r.start_pos.dist(pos));
        }

        // long distance is good, but long distance to get there is bad
        points += r.distance;
        points -= pos.dist(r.start_pos);

        // and +/- 10%
        if ((points/10) == 0) return points;
        return points + (rand.nextInt() % (points/10));
    }

    public boolean step(int time) {
        if (steps_to_wait-- > 0) {
            return false;
        }
        if (pos.x != desiredPos.x) {
            pos.x += pos.x > desiredPos.x ? -1 : 1;
        } else if (pos.y != desiredPos.y) {
            pos.y += pos.y > desiredPos.y ? -1 : 1;
        } else {
            gettingToRide = !gettingToRide;
        }

        if (!gettingToRide) {
            steps_to_wait = rides.get(rides.size()-1).start - time;
            desiredPos = rides.get(rides.size()-1).end_pos;
        } else {
            return true;
        }
        return false;
    }
}
