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

	Scanner scanner = null;
	Matcher matcher = null;

	public Collection<? extends Iata> readLocalCollection() {
		return readSingleCollection(getClass().getResource("iata_airline.htm"));
	}

	private Boolean forward_to_next_element() {
		if (null == scanner.findWithinHorizon("<tr>", 0))
			return false;
		scanner.nextLine();
		return true;
	}

	private String process_String(String string) {
		matcher = Pattern.compile("<td><a.*?>(.*)</a>.*</td>").matcher(string);
		if (matcher.matches())
			return matcher.group(1);
		else {
			matcher = Pattern.compile("<td>(.*)</td>").matcher(string);
			if (matcher.matches())
				return matcher.group(1);
		}
		return null;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {

		try {
			this.scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String code = null;
		String name = null;
		String country = null;
		LinkedList<IataAirline> airlines = new LinkedList<IataAirline>();

		Boolean next_element_exists = forward_to_next_element();

		forward_to_next_element();
		next_element_exists = forward_to_next_element();

		while (next_element_exists) {

			matcher = Pattern.compile("<td>(..).*</td>").matcher(
					scanner.nextLine()); // code
			if (matcher.matches())
				code = matcher.group(1);
			else {
				code = null;
			}

			name = process_String(scanner.nextLine()); // name

			country = process_String(scanner.nextLine()); // country

			if (code != null && name != null && country != null)
				airlines.add(new IataAirline(code, name, country));

			next_element_exists = forward_to_next_element();

		}

		scanner.close();
		return airlines;
	}
}
