import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3
{
    public static void main(String[] args) throws IOException
    {
        partOne();
        //        partTwo();
    }

    private static class PartNum {
        String num;
        int x;
        int y;

        public PartNum() {
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

        public boolean isLocationSet() {
            return x < 0 || y < 0;
        }
    }

    private static void partOne() throws IOException
    {

        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        int maxRows = lines.size();
        int maxCol = lines.get(0).length(); //this is because all lines are the same length
        int answer = 0;
        List<Integer[]> symbols = new ArrayList<>();
        List<PartNum> numbers = new ArrayList<>();

        for(int i = 0; i < maxRows; i++)
        {
            StringBuilder str = new StringBuilder();
            PartNum partNum = null;

            for(int j = 0; j < maxCol; j++) {
                char c = lines.get(i).charAt(j);

                if(isSymbol(c)) {
                    symbols.add(new Integer[]{j, i});

                    if(partNum != null) {
                        partNum.setNum(str.toString());
                        numbers.add(partNum);
                        str = new StringBuilder();
                        partNum = null;
                    }
                }

                else if(Character.isDigit(c)) {
                    if(partNum == null) {
                        partNum = new PartNum();
                        partNum.setLocation(j, i);
                    }

                    str.append(c);
                }

                else if(partNum != null) {
                    partNum.setNum(str.toString());
                    numbers.add(partNum);
                    str = new StringBuilder();
                    partNum = null;
                }
            }

            if(partNum != null) {
                partNum.setNum(str.toString());
                numbers.add(partNum);
            }
        }

        for(PartNum num : numbers) {
            boolean isValid = checkCoord(num, symbols, maxRows, maxCol);
            if(isValid) {
                System.out.println(num.getNum() + " is a part num");
                answer += Integer.parseInt(num.getNum());
            }
        }

        System.out.println("Answer: " + answer);
    }

    private static boolean checkCoord(PartNum num, List<Integer[]> symbols, int maxRows, int maxColumns) {
        boolean valid;
        for(int i = 0; i < num.getNum().length(); i++) {
            if(i == 0) //beginning
                valid = checkLeft(num, symbols, maxRows, maxColumns);
            else if( i == num.getNum().length() - 1) //end
                valid = checkRight(num, symbols, i, maxRows, maxColumns);
            else //somewhere in between
                valid = checkMiddle(num, symbols, i, maxRows, maxColumns);

            if(valid) return valid;
        }

        return false;
    }

    private static boolean checkLeft(PartNum num, List<Integer[]> symbols, int maxRows, int maxColumns) {
        if(checkSymbol(num.x - 1, num.y - 1, symbols, maxRows, maxColumns)) return true;
        if(checkSymbol(num.x - 1, num.y, symbols, maxRows, maxColumns)) return true;
        if(checkSymbol(num.x - 1, num.y + 1, symbols, maxRows, maxColumns)) return true;

        return checkMiddle(num, symbols, 0, maxRows, maxColumns);
    }

    private static boolean checkRight(PartNum num, List<Integer[]> symbols, int i, int maxRows, int maxColumns) {
        if(checkSymbol(num.x + 1 + i, num.y + 1, symbols, maxRows, maxColumns)) return true;
        if(checkSymbol(num.x + 1 + i, num.y, symbols, maxRows, maxColumns)) return true;
        if(checkSymbol(num.x + 1 + i, num.y - 1, symbols, maxRows, maxColumns)) return true;

        return checkMiddle(num, symbols, i, maxRows, maxColumns);
    }

    private static boolean checkMiddle(PartNum num, List<Integer[]> symbols, int i, int maxRows, int maxColumns) {
        if(checkSymbol(num.x + i, num.y + 1, symbols, maxRows, maxColumns)) return true;
        return checkSymbol(num.x + i, num.y - 1, symbols, maxRows, maxColumns);
    }

    private static boolean checkSymbol(int x, int y, List<Integer[]> symbols, int maxRows, int maxColumns) {
        if(!inRange(x, y, maxRows, maxColumns)) return false;
        return symbols.stream().anyMatch(coords -> coords[0] == x && coords[1] == y);
    }

    private static boolean inRange(int x, int y, int maxRows, int maxColumns) {
        return x >= 0 && y >= 0 && x < maxColumns && y < maxRows;
    }

    private static boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }
}
