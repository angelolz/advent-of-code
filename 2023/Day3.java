import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3
{
    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("2023/inputs/3.txt"));
        partOne(lines);
        partTwo(lines);
    }

    private static void partOne(List<String> lines) throws IOException
    {
        int answer = 0;
        List<PartNum> numbers = extractPartNumbers(lines);

        for(PartNum part : numbers)
        {
            if(checkCoord(lines, part))
            {
//                System.out.println(part.getNum() + " is a part num");
                answer += Integer.parseInt(part.getNum());
            }
        }

        System.out.println("D3P1 Answer: " + answer);
    }

    private static void partTwo(List<String> lines) {

    }

    private static class PartNum
    {
        String num;
        int x;
        int y;

        public PartNum()
        {
            this.x = -1;
            this.y = -1;
        }

        public String getNum()
        {
            return num;
        }

        public void setNum(String num)
        {
            this.num = num;
        }

        public int getX()
        {
            return x;
        }

        public void setLocation(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getY()
        {
            return y;
        }
    }

    private static List<PartNum> extractPartNumbers(List<String> lines)
    {
        List<PartNum> numbers = new ArrayList<>();
        int maxRows = lines.size();
        int maxCols = lines.get(0).length();

        for(int i = 0; i < maxRows; i++)
        {
            StringBuilder str = new StringBuilder();
            PartNum partNum = null;

            for(int j = 0; j < maxCols; j++)
            {
                char c = lines.get(i).charAt(j);

                if(Character.isDigit(c))
                {
                    if(partNum == null)
                    {
                        partNum = new PartNum();
                        partNum.setLocation(j, i);
                    }

                    str.append(c);
                }

                else if(partNum != null)
                {
                    partNum.setNum(str.toString());
                    numbers.add(partNum);
                    str = new StringBuilder();
                    partNum = null;
                }
            }

            if(partNum != null)
            {
                partNum.setNum(str.toString());
                numbers.add(partNum);
            }
        }

        return numbers;
    }

    private static boolean checkCoord(List<String> lines, PartNum part)
    {
        return checkTop(lines, part) || checkSides(lines, part) || checkBottom(lines, part);
    }

    private static boolean checkTop(List<String> lines, PartNum part)
    {
        if(part.getY() - 1 < 0) return false;

        String sub = lines.get(part.getY() - 1).substring(Math.max(0, part.x - 1), Math.min(lines.get(part.getY()).length(), part.x + part.getNum().length() + 1));
        return hasSymbol(sub);
    }

    private static boolean checkSides(List<String> lines, PartNum part)
    {
        return (part.getX() - 1 > 0 && isSymbol(lines.get(part.getY()).charAt(part.getX() - 1)))
            || (part.getX() + part.getNum().length() < lines.get(part.getY()).length() && isSymbol(lines.get(part.getY()).charAt(part.getX() + part.getNum().length())));
    }

    private static boolean checkBottom(List<String> lines, PartNum part)
    {
        if(part.getY() + 1 >= lines.size()) return false;

        String sub = lines.get(part.getY() + 1).substring(Math.max(0, part.x - 1), Math.min(lines.get(part.getY()).length(), part.x + part.getNum().length() + 1));
        return hasSymbol(sub);
    }

    private static boolean isSymbol(char c)
    {
        return !Character.isDigit(c) && c != '.';
    }

    private static boolean hasSymbol(String str)
    {
        for(char c : str.toCharArray())
            if(isSymbol(c)) return true;

        return false;
    }
}
