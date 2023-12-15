import java.util.ArrayList;
import java.util.List;

class AlmanacMap {

	private class AlmanacMapRange {

		private long srcStart;
		private long destStart;
		private long range;
		public AlmanacMapRange(long srcStart, long destStart, long range) {
			this.srcStart = srcStart;
			this.destStart = destStart;
			this.range = range;
		}

		public Long get(long key) {
			if (key >= srcStart && key < srcStart + range) {
				return destStart + (key-srcStart);
			}
			return null;
		}

		public String toString() {
			return "srcStart \t destStart \t range\n"
				+ srcStart + "\t" + destStart + "\t" + range + "\n";
		}
	}

	private List<AlmanacMapRange> almanacMapRanges;
    
	public AlmanacMap() {  
		this.almanacMapRanges = new ArrayList<AlmanacMapRange>();
	}

	public void addRange(long srcStart, long destStart, long range) {
		almanacMapRanges.add(new AlmanacMapRange(srcStart, destStart, range));
	}

	public Long getOrDefault(long key, long defaultValue) {
		Long value = null;
		for (AlmanacMapRange range: almanacMapRanges) {
			value = range.get(key);
			if (value != null) break;
		}

		return (value != null) ? value : defaultValue;
	}

	public String toString() {
		String s = "";
		for (AlmanacMapRange range : almanacMapRanges) {
			s += range.toString();
		}
		return s;
	}
}