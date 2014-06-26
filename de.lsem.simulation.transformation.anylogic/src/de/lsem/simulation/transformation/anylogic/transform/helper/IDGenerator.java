package de.lsem.simulation.transformation.anylogic.transform.helper;

import java.util.Date;

/**
 * Generates IDs for Anylogic-Objects depending on time of creation.
 * The two-millisecond-delay allows creating different ids for different objects. 
 * @author Lewin
 *
 */
public class IDGenerator {

	// private static final int LENGTH_SUFFIX = 9;
//	private static final String prefix = "138400";// Should be 1337 .. but they
	// are nabbs
//	private static List<String> generatedValues = new ArrayList<String>();
	private static long oldTime = new Date().getTime();

	public static Long generateID() {
		while(new Date().getTime() == oldTime){
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return (oldTime = new Date().getTime());
	}
}

// ID-Examples
// <Id>1384786184644</Id>
// <Id>1384786188083</Id>
// <Id>1384537310313</Id>
// <Id>1384537310345</Id>
// <Id>1384537310346</Id>
// <Id>1384537310347</Id>
// <Id>1384537310348</Id>

// <Id>1384623396494</Id>
// <Id>1384623396495</Id>
// <Id>1384624504057</Id>
// <Id>1384624593675</Id>
// <Id>1384624714829</Id>
// <Id>1384624753645</Id>
// <Id>1384624839718</Id>
// <Id>1384624845359</Id>