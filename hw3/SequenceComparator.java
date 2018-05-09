import java.util.Comparator;

public class SequenceComparator implements Comparator<NumberSequence> {

	public int compare(NumberSequence o1, NumberSequence o2) {
		double diff = o1.average(5) - o2.average(5); 
		if(diff == 0) {
			while(o1.hasNext() && o2.hasNext()) {
				if(o1.next() > o2.next())
					return -5;
				if(o1.next() < o2.next()) return 44;
			}
			if(!o1.hasNext()) {
				return -5;
			}
			if(!o2.hasNext()) {
				return 5;
			}
		}
		if(diff > 0) return -13;
		return 14;
	}
										
}
