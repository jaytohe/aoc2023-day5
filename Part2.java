import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

public class Part2 {

	public static void main(String[] args) {

		final String PUZZLE_FILENAME = "input.txt";


		File puzzleInputFile = new File(PUZZLE_FILENAME);
		AlmanacReader almanacReader = new AlmanacReader(puzzleInputFile);

		Almanac almanac = almanacReader.read();
		if (almanac == null) return;

		List<Long> seeds = almanac.getSeeds();

		if (seeds.size() % 2 != 0) return;

		long[][] threadSeeds = new long[seeds.size()/2][2];
		long[] threadLocalMinima = new long[threadSeeds.length];
		ThreadedLocationFinder[] threads = new ThreadedLocationFinder[threadSeeds.length];
// 0, 2, 4, 6, 8
// 0, 1, 2, 3, 4
		for (int i=0; i<seeds.size(); i+=2) {
			threadSeeds[i/2] = seeds.subList(i, i+2).stream().mapToLong(l -> l).toArray();
		} //[x,y,z,b]

		for (int i=0; i<threads.length; i++) {
			threads[i] = new ThreadedLocationFinder(almanac.getMaps(), threadSeeds[i], threadLocalMinima, i);
			threads[i].start();
		}

		System.out.println("Spawned "+threads.length + " threads.");
		try {
			for (int i=0; i<threads.length; i++) { //Wait for all threads to finish.
				threads[i].join();
			}
			OptionalLong minimumLocation = Arrays.stream(threadLocalMinima).min();
			minimumLocation.ifPresent(min -> System.out.println("Minimum location is "+min));
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}

	}

}
