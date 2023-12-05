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
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        String wholeGameString = "^Game (\\d+): (.+)$";
        Pattern wholeGamePattern = Pattern.compile(wholeGameString);

        String setString = "(\\d+)\\s(green|red|blue)\\b";
        Pattern setPattern = Pattern.compile(setString);

        int answer = 0;
        for(String line : lines)
        {
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

        System.out.println("Answer: " + answer);
    }

    private static boolean isValidGame(Matcher wholeMatcher, Pattern setPattern)
    {
        boolean valid = true;
        Matcher setMatcher = setPattern.matcher(wholeMatcher.group(2));
        while(setMatcher.find() && valid)
        {
            int amount = Integer.parseInt(setMatcher.group(1));
            switch(setMatcher.group(2).trim())
            {
                case "green" -> valid = amount <= 13;
                case "blue" -> valid = amount <= 14;
                case "red" -> valid = amount <= 12;
            }

            if(!valid) System.out.printf("   - too much %s -- %d%n", setMatcher.group(2).trim(), amount);
        }

        return valid;
    }
}
