import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day5
{
    private static Map<Range, Range> seedToSoilMap;
    private static Map<Range, Range> soilToFertilizerMap;
    private static Map<Range, Range> fertilizerToWaterMap;
    private static Map<Range, Range> waterToLightMap;
    private static Map<Range, Range> lightToTemperatureMap;
    private static Map<Range, Range> temperatureToHumidityMap;
    private static Map<Range, Range> humidityToLocationMap;
    private static Map<Long, Long> seedToLocationMap;

    public static void main(String[] args) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get("2023/inputs/5.txt"));
        partOne(lines);
        partTwo(lines);
    }

    private static void partOne(List<String> lines)
    {
        //set up maps
        initMaps();

        Long[] seeds = new Long[]{};
        String sourceCategory = "";
        String destinationCategory = "";

        for(String line : lines)
        {
            if(line.isEmpty()) continue;


            if(line.contains("map"))
            {
                String[] categories = line.replace("-to-", " ").split("\\s+");
                sourceCategory = categories[0];
                destinationCategory = categories[1];
            }

            else if(line.contains("seeds"))
            {
                String[] seedsStringArr = line.substring(line.indexOf(":") + 1).trim().split("\\s+");
                seeds = Arrays.stream(seedsStringArr).map(Long::valueOf).toArray(Long[]::new);
            }

            else
            {
                Long[] mapData = Arrays.stream(line.split("\\s+")).map(Long::valueOf).toArray(Long[]::new);
                Range sourceRange = new Range(mapData[1], mapData[1] + mapData[2] - 1);
                Range destinationRange = new Range(mapData[0], mapData[0] + mapData[2] - 1);
                putRangeToMap(sourceCategory, destinationCategory, sourceRange, destinationRange);
            }
        }

        for(Long seed : seeds)
            find(seed);

        long answer = Collections.min(seedToLocationMap.values());
        System.out.println("D5P1 Answer: " + answer);
    }

    private static void partTwo(List<String> lines)
    {

    }

    private record Range(long start, long end)
    { /* hmm */
    }

    private static void find(Long seed)
    {
        long soil = findRange(seed, seedToSoilMap);
        long fertilizer = findRange(soil, soilToFertilizerMap);
        long water = findRange(fertilizer, fertilizerToWaterMap);
        long light = findRange(water, waterToLightMap);
        long temperature = findRange(light, lightToTemperatureMap);
        long humidity = findRange(temperature, temperatureToHumidityMap);
        long location = findRange(humidity, humidityToLocationMap);

        System.out.printf("seed: %d | soil: %d | fert: %d | water: %d | light: %d | temp: %d | hum: %d | loc: %d%n",
            seed, soil, fertilizer, water, light, temperature, humidity, location);

        seedToLocationMap.put(seed, location);
    }

    private static long findRange(Long num, Map<Range, Range> map)
    {
        for(Map.Entry<Range, Range> mapEntry : map.entrySet())
        {
            Range sourceRange = mapEntry.getKey();
            if(num >= sourceRange.start() && num <= sourceRange.end())
                return mapEntry.getValue().start() + num - sourceRange.start();
        }

        return num;
    }

    private static void putRangeToMap(String src, String dest, Range srcRange, Range destRange)
    {
        if(src.equals("seed") && dest.equals("soil"))
            seedToSoilMap.put(srcRange, destRange);
        else if(src.equals("soil") && dest.equals("fertilizer"))
            soilToFertilizerMap.put(srcRange, destRange);
        else if(src.equals("fertilizer") && dest.equals("water"))
            fertilizerToWaterMap.put(srcRange, destRange);
        else if(src.equals("water") && dest.equals("light"))
            waterToLightMap.put(srcRange, destRange);
        else if(src.equals("light") && dest.equals("temperature"))
            lightToTemperatureMap.put(srcRange, destRange);
        else if(src.equals("temperature") && dest.equals("humidity"))
            temperatureToHumidityMap.put(srcRange, destRange);
        else if(src.equals("humidity") && dest.equals("location"))
            humidityToLocationMap.put(srcRange, destRange);
        else
            System.out.printf("Warning: unknown map %s-to-%s", src, dest);
    }

    private static void initMaps()
    {
        seedToSoilMap = new HashMap<>();
        soilToFertilizerMap = new HashMap<>();
        fertilizerToWaterMap = new HashMap<>();
        waterToLightMap = new HashMap<>();
        lightToTemperatureMap = new HashMap<>();
        temperatureToHumidityMap = new HashMap<>();
        humidityToLocationMap = new HashMap<>();
        seedToLocationMap = new HashMap<>();
    }
}
