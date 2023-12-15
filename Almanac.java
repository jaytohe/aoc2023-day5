import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Almanac {

	private List<Long> seeds;
	private Map<AlmanacMapName, AlmanacMap> maps;

	public Almanac() {
		// init seeds
		this.seeds = new ArrayList<Long>();
		//Initialize the maps of the almanac.
		this.maps = new LinkedHashMap<AlmanacMapName, AlmanacMap>();


		for (AlmanacMapName almanacMapName : AlmanacMapName.values()) {
			this.maps.put(almanacMapName, new AlmanacMap());
		}
	
		/*
		this.maps.put(AlmanacMapName.SEED2SOIL, new AlmanacMap());
		this.maps.put(AlmanacMapName.SOIL2FERTILIZER, new AlmanacMap());
		this.maps.put(AlmanacMapName.FERTILIZER2WATER, new AlmanacMap());
		this.maps.put(AlmanacMapName.WATER2LIGHT, new AlmanacMap());
		this.maps.put(AlmanacMapName.LIGHT2TEMPERATURE, new AlmanacMap());
		this.maps.put(AlmanacMapName.TEMPREATURE2HUMIDITY, new AlmanacMap());
		this.maps.put(AlmanacMapName.HUMIDITY2LOCATION, new AlmanacMap());
		*/
	}

	public List<Long> getSeeds() {
		return seeds;
	}
	public Map<AlmanacMapName, AlmanacMap> getMaps() {
		return maps;
	}

	public AlmanacMap getMapByName(AlmanacMapName name) {
		return maps.get(name);
	}


	
	@Override
	public String toString() {
		return "seeds: " +  this.seeds.stream().map(Object::toString).collect(Collectors.joining(", ")) + "\n"
			+ "seed2soil" + "\n"
			+ maps.get(AlmanacMapName.SEED2SOIL).toString() + "\n\n"
			+ "soil2fertilizer" + "\n"
			+ maps.get(AlmanacMapName.SOIL2FERTILIZER).toString() + "\n\n"
			+ "fertilizer2water" + "\n"
			+ maps.get(AlmanacMapName.FERTILIZER2WATER).toString() + "\n\n"
			+ "water2light" + "\n"
			+ maps.get(AlmanacMapName.WATER2LIGHT).toString() + "\n\n"
			+ "light2temperature" + "\n"
			+ maps.get(AlmanacMapName.LIGHT2TEMPERATURE).toString() + "\n\n"
			+ "temperature2humidity" + "\n"
			+ maps.get(AlmanacMapName.TEMPREATURE2HUMIDITY).toString() + "\n\n"
			+ "humidity2location" + "\n"
			+ maps.get(AlmanacMapName.HUMIDITY2LOCATION).toString();
	}

}