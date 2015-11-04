package iata.airline;

import iata.AbstractIataCollectionReader;
import iata.Iata;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IataAirlineCollectionReader extends AbstractIataCollectionReader {

	public Collection<? extends Iata> readLocalCollection() {

		Scanner scanner = null;

		try {
			URL url = getClass().getResource("iata_airline.htm");
			scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(scanner.next());

		Pattern p = Pattern.compile("a*b");
		Matcher m = p.matcher("aaaaab");
		boolean b = m.matches();

		LinkedList airlines = new LinkedList();

		IataAirline a = null;

		scanner.close();
		return airlines;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {
		return readLocalCollection();
	}

}
