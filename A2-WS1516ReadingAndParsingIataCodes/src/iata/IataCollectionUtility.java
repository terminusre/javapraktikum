package iata;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

public class IataCollectionUtility {

	public static final void toFile(Collection<? extends Iata> iataCollection, String filename) throws IOException {
		try (PrintWriter writer = new PrintWriter(new File(filename),"UTF-8")) {
			for (Iata iata : iataCollection) {
				writer.println(iata);
			}
		}
	}

}
