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
		System.out.println("Error at " + string);
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

		String line = null;

		Boolean next_element_exists = forward_to_next_element();

		forward_to_next_element();
		next_element_exists = forward_to_next_element();

		while (next_element_exists) {

			code = null;
			name = null;
			country = null;

			line = scanner.nextLine(); // code
			matcher = Pattern.compile("<td>(..).*</td>").matcher(line);
			if (matcher.matches())
				code = matcher.group(1);
			else {
				System.out.println("code: " + line);
				break;
			}

			line = scanner.nextLine(); // name
			name = process_String(line);

			line = scanner.nextLine(); // country
			country = process_String(line);

			if (code != null && name != null && country != null)
				airlines.add(new IataAirline(code, name, country));

			next_element_exists = forward_to_next_element();

		}

		scanner.close();
		return airlines;
	}
}
