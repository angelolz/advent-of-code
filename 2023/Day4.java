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
        int answer;
        for(int i = 0; i < lines.size(); i++)
        {
            String line = lines.get(i);
            System.out.println(line);
            String[] winningNums = line.substring(line.indexOf(":") + 1, line.indexOf("|")).trim().split("\\s+");
            String[] lottery = line.substring(line.indexOf("|") + 1).trim().split("\\s+");

            int cardId = i + 1;
            lotteryMap.putIfAbsent(cardId, 1); //the first card will always have 1 instance
            int copies = 0;
            for(String winningNum : winningNums)
            {
                int matches = Arrays.stream(lottery).filter(num -> num.equals(winningNum)).toList().size();
                copies += matches;
            }

            for(int j = 1; j <= copies; j++)
            {
                int newVal = lotteryMap.getOrDefault(cardId + j, 1) + lotteryMap.get(cardId);
                lotteryMap.put(cardId + j, newVal);
            }
        }

        answer = lotteryMap.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("D4P2 Answer: " + answer);

    }
}
