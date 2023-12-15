import java.util.Map;

public class ThreadedLocationFinder extends Thread {

    private static final Object lock = new Object();
    private final long[] seeds;
    private final Map<AlmanacMapName, AlmanacMap> maps;
    private int threadIdx;
    private long[] localMinima;
   
    public ThreadedLocationFinder( Map<AlmanacMapName, AlmanacMap> maps, long[] seeds, long[] localMinima, int threadIdx) {
        this.seeds = seeds;
        this.maps = maps;
        this.localMinima = localMinima;
        this.threadIdx = threadIdx;
    }

    @Override
	public void run() {
        long range = seeds[1];
        long minLocation = Long.MAX_VALUE;
        for (long seed=seeds[0]; seed<seeds[0]+range; seed++) {
            long iterLocation = findLocation(seed, maps);
            if (iterLocation < minLocation) {
                minLocation = iterLocation;
            }
        }

        synchronized(lock) {
            localMinima[threadIdx] = minLocation;
        }
    }

	private long findLocation(long seed, Map<AlmanacMapName, AlmanacMap> maps) {

		for (AlmanacMap almanacMap : maps.values()) {
			seed = almanacMap.getOrDefault(seed, seed);
		}

		return seed;
	} 
}