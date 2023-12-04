public class Day1
{
    public static void main(String[] args) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("/Users/alegaspi/Library/Application Support/JetBrains/IntelliJIdea2022.3/scratches/fun/input.txt"));

		int answer = 0;
		for(String line : lines)
		{
			StringBuilder str = new StringBuilder();
			char currDigit = 0;
			for(int i = 0; i < line.length(); i++)
			{
				if(isDigit(line.charAt(i))) {
					currDigit = line.charAt(i);
					if(str.length() == 0) {
						str.append(line.charAt(i));
					}
				}
				
			}

			str.append(currDigit);
			answer += Integer.parseInt(str.toString());
		}

		System.out.println(answer);
	}

	public static boolean isDigit(char c) {
		return (c >= '0' && c <= '9');
	}
}