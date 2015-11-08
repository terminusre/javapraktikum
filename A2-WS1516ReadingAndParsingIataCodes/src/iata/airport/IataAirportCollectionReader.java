package iata.airport;

import iata.AbstractIataCollectionReader;
import iata.Iata;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class IataAirportCollectionReader extends AbstractIataCollectionReader {

	public Collection<? extends Iata> readLocalCollection() {
		URL url = null;
		for(int i = 0; i < 26; i++){
			System.out.println("" + );
			readSingleCollection(getClass().getResource(((char) (65 + i)) + ".htm"));
		}
		return url;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {
		
		Scanner scanner = null;

		try {
			scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList airports = new LinkedList();

		IataAirport a = null;

		scanner.close();
		return airports;
	}
}
