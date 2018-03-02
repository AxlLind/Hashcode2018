import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Main {
    static int F, N, B, T, time;
    static ArrayList<Ride> rides;
    static Vehicle[] vs;

    public static void main(String[] args) {
        makeSolution("a_example");
        makeSolution("b_should_be_easy");
        makeSolution("c_no_hurry");
        makeSolution("d_metropolis");
        makeSolution("e_high_bonus");
    }

    public static void makeSolution(String fileName) {
        readInput( new File("../in/" + fileName + ".in") );
        System.out.println(fileName + " " + N + " " + B + " " + T);

        vs = new Vehicle[F];
        for (int i = 0; i < F; ++i)
            vs[i] = new Vehicle();

        time = 0;
        while (time++ < T) step();

        printOutput(fileName);
    }

    public static void step() {
        for (Vehicle v : vs) {
            if (v.step(time))
                assignNewRide(v);
        }
    }

    public static void assignNewRide(Vehicle v) {
        int max = Integer.MIN_VALUE;
        int maxI = -1;
        for (int i = 0; i < rides.size(); ++i) {
            int points = v.points(rides.get(i), time, B, rides, vs);
            if (points > max) {
                maxI = i;
                max = points;
            }
        }
        if (maxI == -1) return;
        v.addRide( rides.get(maxI) );
        rides.remove(maxI);
    }

    public static void readInput(File file) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // skip R, C
        sc.nextInt(); sc.nextInt();
        F = sc.nextInt();
        N = sc.nextInt();
        B = sc.nextInt();
        T = sc.nextInt();

        rides = new ArrayList<>();
        for (int i = 0; i < N; ++i)
            rides.add(new Ride(sc.nextInt(),sc.nextInt(),sc.nextInt(),
                               sc.nextInt(),sc.nextInt(),sc.nextInt(),i));
    }

    public static void printOutput(String fileName) {
        StringBuilder sb = new StringBuilder();

        for (Vehicle v : vs) {
            sb.append(v.rides.size() + " ");
            for (int j = 0; j < v.rides.size(); ++j)
                sb.append(v.rides.get(j).id + " ");
            sb.append("\n");
        }

        try {
            Path p = Paths.get("../out/" + fileName + ".out");
            Files.write(p, sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
