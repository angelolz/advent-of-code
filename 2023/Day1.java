import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day1
{
	//this was for part 2
	static String[][] wordDigits = new String[][]{{"one", "1"}, {"two", "2"}, {"three", "3"}, {"four", "4"}, {"five", "5"}, {"six", "6"}, {"seven", "7"}, {"eight", "8"}, {"nine", "9"}};

    public static void main(String[] args) throws IOException
    {
		List<String> lines = Files.readAllLines(Paths.get("2023/inputs/1.txt"));
		partOne(lines);
		partTwo(lines);
	}

	public static void partOne(List<String> lines)
	{
		int answer = 0;
		for(String line : lines)
		{
//			System.out.println(line);
			StringBuilder str = new StringBuilder();
			char currDigit = 0;
			for(int i = 0; i < line.length(); i++)
			{
				if(isDigit(line.charAt(i))) {
					currDigit = line.charAt(i);
					if(str.isEmpty()) {
						str.append(line.charAt(i));
					}
				}
				
			}

			str.append(currDigit);
			answer += Integer.parseInt(str.toString());
		}

		System.out.println("D1P1 Answer: " + answer);
	}

	

	public static void partTwo(List<String> lines)
	{
		int answer = 0;
		for (String line : lines) {
//			System.out.println(line);
			StringBuilder sb = new StringBuilder();
			int start = 0;
			for (int i = 0; i < line.length(); i++) {
				String sub = line.substring(start, i + 1);

				int wordIndex = Arrays.stream(wordDigits).map(word -> sub.indexOf(word[0])).filter(idx -> idx >= 0).sorted().findFirst().orElse(-1);
				if (wordIndex >= 0) {
					for (String[] wordDigit : wordDigits) {
						if (wordDigit[0].equals(sub.substring(wordIndex))) {
							sb.append(wordDigit[1]);
							start = i;
							break;
						}
					}
				}

				if (isDigit(line.charAt(i))) {
					sb.append(line.charAt(i));
					start = i + 1;
				}
			}

			if(sb.length() == 1) {
//				System.out.println("   - " + Integer.parseInt("" + sb.charAt(0) + sb.charAt(0)));
				answer += Integer.parseInt("" + sb.charAt(0) + sb.charAt(0));
			}

			else {
//				System.out.println("   - " + Integer.parseInt("" + sb.charAt(0) + sb.charAt(sb.length() - 1)));
				answer += Integer.parseInt("" + sb.charAt(0) + sb.charAt(sb.length() - 1));
			}
		}

		System.out.println("D1P2 Answer: " +answer);
	}
	
	private static boolean isDigit(char c) {
		return (c >= '0' && c <= '9');
	}
}