package iata.airport;

import iata.AbstractIataCollectionReader;
import iata.Iata;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IataAirportCollectionReader extends AbstractIataCollectionReader {

	public Collection<? extends Iata> readLocalCollection() {
		Scanner scanner = null;

		try {
			URL url = getClass().getResource("A.htm");
			scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaaab");
		boolean b = m.matches();

		LinkedList airports = new LinkedList();

		IataAirport a = null;

		scanner.close();
		return airports;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {
		return readLocalCollection();
	}
}
