import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("2023/inputs/4.txt"));
                partOne(lines);
        partTwo(lines);
    }

    private static void partOne(List<String> lines)
    {
        int answer = 0;
        for(String line : lines)
        {
            System.out.println(line);
            int points = 0;
            String[] winningNums = line.substring(line.indexOf(":") + 1, line.indexOf("|")).trim().split("\\s+");
            String[] lottery = line.substring(line.indexOf("|") + 1).trim().split("\\s+");

            for(String winningNum : winningNums)
            {
                if(Arrays.asList(lottery).contains(winningNum))
                {
                    if(points == 0) points = 1;
                    else points *= 2;
                }
            }

            answer += points;
        }

        System.out.println("D4P1 Answer: " + answer);
    }

    private static void partTwo(List<String> lines)
    {
        Map<Integer, Integer> lotteryMap = new HashMap<>();
        lotteryMap.put(1, 1);
        int answer;
        int copies = 0;
        int cardId = 1;
        for(String line : lines)
        {
            System.out.println(line);
            String[] winningNums = line.substring(line.indexOf(":") + 1, line.indexOf("|")).trim().split("\\s+");
            String[] lottery = line.substring(line.indexOf("|") + 1).trim().split("\\s+");

            lotteryMap.putIfAbsent(cardId, 1);
            cardId++;
            for(String winningNum : winningNums)
            {
                int val = Arrays.stream(lottery).filter(num -> num.equals(winningNum)).toList().size();
                if(val > 0) {
                    System.out.println("val: " + val);
                    copies = copies + val;
                }
            }

            for(int i = 0; i < copies; i++)
            {
                if(cardId < lines.size())
                {
                    int newVal = lotteryMap.getOrDefault(cardId + i, 1) + lotteryMap.get(cardId - 1);
                    lotteryMap.put(cardId + i, newVal);
                }
            }

            copies = 0;
        }

        answer = lotteryMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("D4P2 Answer: " + answer);

    }
}
