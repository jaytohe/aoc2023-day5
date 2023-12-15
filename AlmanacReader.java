import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class AlmanacReader {
	private File puzzleInputFile;

	public AlmanacReader(File puzzleInputFile) {
		this.puzzleInputFile = puzzleInputFile;
	}


	public Almanac read() {
		try (BufferedReader br = new BufferedReader(new FileReader(puzzleInputFile))) {
			Almanac almanac = new Almanac();
			String line;
			AlmanacMapName almanacMapName = null;
			while((line = br.readLine()) != null) {
				if (line.startsWith("seeds:")) {
					String[] s = line.split(" ");
					for (int i=1; i<s.length; i++) {
						Long seedNum = Long.parseLong(s[i]);
						almanac.getSeeds().add(seedNum);
					}
				}
				else if (line.startsWith("seed-to-soil map:")) {
					almanacMapName = AlmanacMapName.SEED2SOIL;
				}
				else if (line.startsWith("soil-to-fertilizer map:")) {
					almanacMapName = AlmanacMapName.SOIL2FERTILIZER;
				}
				else if (line.startsWith("fertilizer-to-water map:")) {
					almanacMapName = AlmanacMapName.FERTILIZER2WATER;
				}
				else if (line.startsWith("water-to-light map:")) {
					almanacMapName = AlmanacMapName.WATER2LIGHT;
				}
				else if (line.startsWith("light-to-temperature map:")) {
					almanacMapName = AlmanacMapName.LIGHT2TEMPERATURE;
				}
				else if (line.startsWith("temperature-to-humidity map:")) {
					almanacMapName = AlmanacMapName.TEMPREATURE2HUMIDITY;
				}
				else if (line.startsWith("humidity-to-location map:")) {
					almanacMapName = AlmanacMapName.HUMIDITY2LOCATION;
				}
				String[] lineSplits = line.split(" ");
				if (lineSplits.length == 3 && almanacMapName != null) {
					long destStart = Long.parseLong(lineSplits[0]);
					long srcStart = Long.parseLong(lineSplits[1]);
					long range = Long.parseLong(lineSplits[2]);
					almanac.getMapByName(almanacMapName).addRange(srcStart, destStart, range);
				}

			}
			return almanac;
		}
		catch (NumberFormatException ne) {
			ne.printStackTrace();
		}

		catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		return null;
	}

}