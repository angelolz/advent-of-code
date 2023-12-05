# 2023 Advent of Code
## Day 1
In my head, solving this was pretty easy. Although the second part was pretty tough. I watched my [partner](https://github.com/AzureToast) work on this and learned a few things:

- Instead of doing `for(int i = 0; i < line.length(); i++)`, I could've just done `for(char c : line.toCharArray())`.
- When I was getting a new substring in part two, I feel like I made it more complicated than I should have. I think that I could've just kept adding chars to the substring, and if a digit or word number was found, just empty the substring.
- I skimmed through some solutions on the subreddit, and noticed some people using regex for part 2. That was one of my possible solutions, but I just couldn't make it work for some reason lol

## Day 2
This was pretty easy, although I did use ChatGPT to help me figure out a good regex for getting the number of cubes and their color in each game.

I do feel like I could've found a better way to get the ID of the game for the first part, though.