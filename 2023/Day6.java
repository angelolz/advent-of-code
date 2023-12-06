import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("2023/inputs/6.txt"));
        partOne(lines);
        partTwo(lines);
    }

    private static void partOne(List<String> lines) {
        String[] times = lines.get(0).substring(lines.get(0).indexOf(":") + 1).trim().split("\\s+");
        String[] distances = lines.get(1).substring(lines.get(1).indexOf(":") + 1).trim().split("\\s+");

        BigInteger[] timesLong = Arrays.stream(times).map(BigInteger::new).toArray(BigInteger[]::new);
        BigInteger[] distancesLong = Arrays.stream(distances).map(BigInteger::new).toArray(BigInteger[]::new);

        List<BigInteger> answers = new ArrayList<>();
        for(int i = 0; i < times.length ; i++) { //assuming both arravs will always be equal length
            answers.add(processRace(timesLong[i], distancesLong[i]).add(BigInteger.ONE));
        }

        BigInteger answer = answers.stream().reduce(BigInteger.ONE, BigInteger::multiply);
        System.out.println("D6P1 Answer: " + answer);
    }

    private static void partTwo(List<String> lines) {
        String time = lines.get(0).substring(lines.get(0).indexOf(":") + 1).trim().replaceAll("\\s+", "");
        String distance = lines.get(1).substring(lines.get(1).indexOf(":") + 1).trim().replaceAll("\\s+", "");

        System.out.println("D6P2 Answer: " + processRace(new BigInteger(time), new BigInteger(distance)).add(BigInteger.ONE));
    }

    private static BigInteger processRace(BigInteger time, BigInteger recordDistance) {
        BigInteger low = processLeft(time, recordDistance);
        BigInteger high = processRight(time, recordDistance);

        return high == null || low == null ? BigInteger.ZERO : high.subtract(low);
    }

    private static BigInteger processLeft(BigInteger time, BigInteger recordDistance) {
        for(BigInteger i = BigInteger.ONE; i.compareTo(time.divide(BigInteger.TWO)) < 0; i = i.add(BigInteger.ONE)) { //skip 0 because its definitely not gonna beat record
            BigInteger distance = getDistance(i, time, recordDistance);
            if(distance != null) {
//                System.out.printf("low  -- hold: %d | distance: %d%n", i, distance);
                return i;
            }
        }

        return null; //??
    }

    private static BigInteger processRight(BigInteger time, BigInteger recordDistance) {
        for(BigInteger i = time.subtract(BigInteger.ONE); i.compareTo(time.divide(BigInteger.TWO).add(BigInteger.ONE)) > 0; i = i.subtract(BigInteger.ONE)) { //skip last ms because its def not gonna beat record
            BigInteger distance = getDistance(i, time, recordDistance);
            if(distance != null) {
//                System.out.printf("high  -- hold: %d | distance: %d%n", i, distance);
                return i;
            }
        }

        return null;
    }

    private static BigInteger getDistance(BigInteger i, BigInteger time, BigInteger recordDistance) {
        BigInteger dist = time.subtract(i).multiply(i);
//        System.out.printf("time: %d | speed: %d | distance:%d%n", time, i, dist);
        if(dist.compareTo(recordDistance) > 0) {
            return dist;
        }

        return null; //??
    }
}
