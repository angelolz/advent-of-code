# 2023 Advent of Code
## Day 1 (completed on 12/4/23)
In my head, solving this was pretty easy. Although the second part was pretty tough. I watched my [partner](https://github.com/AzureToast) work on this and learned a few things:

- Instead of doing `for(int i = 0; i < line.length(); i++)`, I could've just done `for(char c : line.toCharArray())`.
- When I was getting a new substring in part two, I feel like I made it more complicated than I should have. I think that I could've just kept adding chars to the substring, and if a digit or word number was found, just empty the substring.
- I skimmed through some solutions on the subreddit, and noticed some people using regex for part 2. That was one of my possible solutions, but I just couldn't make it work for some reason lol

## Day 2 (completed on 12/4/23)
This was pretty easy, although I did use ChatGPT to help me figure out a good regex for getting the number of cubes and their color in each game.

I do feel like I could've found a better way to get the ID of the game for the first part, though.

## Day 3 (completed on 12/5/23)
This day had me in a chokehold. All the high I got from solving Day 2 quickly went away fast. I was working on Part 1 the same day I finished Day 1 and 2, and I was writing the hottest garbage code that I have written in a while. Decided to skip to Day 4 and work on that one for a bit before taking a break.

On the next day, I figured out a different (and better way) to determine symbols. Instead of checking each symbol around the number, I can just get the substring from the top and bottom, and then check the sides, then determining if any of them had symbols. That worked!