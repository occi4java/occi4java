package occi.http.helper;

import java.util.Random;

public class Generator {

	private final static String[] pool = { "a", "b", "c", "d", "e", "f", "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };

	public static String generateMacAdress() {
		// initialize integer generator
		Random generator = new Random();
		String mac = "";
		for (int i = 0; i < 12; i++) {
			if (i % 2 != 0) {
				mac += pool[generator.nextInt(16)];
			} else {
				mac += "-";
			}
		}
		return mac;
	}
}