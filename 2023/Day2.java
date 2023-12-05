import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2
{
    public static void main(String[] args) throws IOException
    {
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        String wholeGameString = "^Game (\\d+): (.+)$";
        Pattern wholeGamePattern = Pattern.compile(wholeGameString);

        String setString = "(\\d+)\\s(green|red|blue)\\b";
        Pattern setPattern = Pattern.compile(setString);

        int answer = 0;
        for(String line : lines)
        {
//            System.out.println(line);
            int id;
            boolean isValid;
            Matcher wholeMatcher = wholeGamePattern.matcher(line);
            while(wholeMatcher.find())
            {
                id = Integer.parseInt(wholeMatcher.group(1));
                isValid = isValidGame(wholeMatcher, setPattern);
                if(isValid) answer += id;
            }
        }

        System.out.println("D2P1 Answer: " + answer);
    }

    private static void partTwo() throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        int answer = 0;
        for(String line : lines)
        {
            String setString = "(\\d+)\\s(green|red|blue)\\b";
            Pattern setPattern = Pattern.compile(setString);
            answer += getPower(line, setPattern);
        }

        System.out.println("D2P2 Answer: " + answer);
    }

    //for part 1
    private static boolean isValidGame(Matcher wholeMatcher, Pattern setPattern)
    {
        boolean valid = true;
        Matcher setMatcher = setPattern.matcher(wholeMatcher.group(2));
        while(setMatcher.find() && valid)
        {
            int amount = Integer.parseInt(setMatcher.group(1));
            switch(setMatcher.group(2).trim())
            {
                case "red" -> valid = amount <= 12;
                case "green" -> valid = amount <= 13;
                case "blue" -> valid = amount <= 14;
            }

            if(!valid) System.out.printf("   - too much %s -- %d%n", setMatcher.group(2).trim(), amount);
        }

        return valid;
    }

    private static int getPower(String line, Pattern setPattern) {
        int red = 0;
        int green = 0;
        int blue = 0;
        Matcher setMatcher = setPattern.matcher(line);
        while(setMatcher.find())
        {
            int amount = Integer.parseInt(setMatcher.group(1));

            switch(setMatcher.group(2).trim())
            {
                case "red" -> { if(red < amount) red = amount; }
                case "green" -> { if(green < amount) green = amount; }
                case "blue" -> { if(blue < amount) blue = amount; }
            }
        }

        //            System.out.printf("   - red: %d | green: %d | blue: %d | POWER: %d%n", red, green, blue, red * green * blue);
        return red * green * blue;
    }
}
