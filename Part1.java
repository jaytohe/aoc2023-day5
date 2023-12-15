import java.io.File;
import java.util.Map;
import java.util.List;

public class Part1 {

	public static void main(String[] args) {

		final String PUZZLE_FILENAME = "input.txt";


		File puzzleInputFile = new File(PUZZLE_FILENAME);
		AlmanacReader almanacReader = new AlmanacReader(puzzleInputFile);

		Almanac almanac = almanacReader.read();


		System.out.println(almanac);
		if (almanac == null) return;

		List<Long> seeds = almanac.getSeeds();
		long minLocation = Long.MAX_VALUE;
		for (long seed: seeds) {
			long iterLocation = findLocation(seed, almanac.getMaps());
			//System.out.println(iterLocation);
			if (iterLocation < minLocation) {
				minLocation = iterLocation;
			}
		}

		System.out.println("Minimum location number is :"+minLocation);
	}

	private static long findLocation(long seed, Map<AlmanacMapName, AlmanacMap> maps) {

		for (AlmanacMap almanacMap : maps.values()) {
			seed = almanacMap.getOrDefault(seed, seed);
		}

		return seed;
	} 
}
