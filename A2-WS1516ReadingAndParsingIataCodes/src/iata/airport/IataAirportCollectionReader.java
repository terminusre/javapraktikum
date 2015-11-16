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

	Scanner scanner = null;

	public Collection<? extends Iata> readLocalCollection() {
		Collection<IataAirport> airports = new LinkedList<IataAirport>();
		Collection<IataAirport> airports_Teilliste;

		URL url = null;
		for (int i = 0; i < 26; i++) {
			url = getClass().getResource((char) (65 + i) + ".htm");
			airports_Teilliste = (Collection<IataAirport>) readSingleCollection(url);
			airports.addAll(airports_Teilliste);
		}

		return airports;
	}

	private Boolean forward_to_next_element() {
		if (null == scanner.findWithinHorizon("<tr>", 0))
			return false;
		scanner.nextLine();
		return true;
	}

	public Collection<? extends Iata> readSingleCollection(URL url) {

		try {
			this.scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();

		Matcher matcher = null;

		String code = null;
		String name = null;
		String country = null;

		Boolean next_element_exists = forward_to_next_element();

		while (next_element_exists) {

			matcher = Pattern.compile("<td>(<s>)?(...)(</s>)?</td>").matcher(
					scanner.nextLine()); // code
			if (matcher.matches())
				code = matcher.group(2);
			else {
				next_element_exists = forward_to_next_element();
				continue;
			}

			scanner.nextLine(); // 2nd code

			matcher = Pattern.compile("<.*?>").matcher(scanner.nextLine()); // name
			name = matcher.replaceAll("");
			if (name.matches("")) {
				next_element_exists = forward_to_next_element();
				continue;
			}

			scanner.nextLine(); // location

			scanner.nextLine(); // region

			matcher = Pattern.compile("<.*?>").matcher(scanner.nextLine()); // country
			country = matcher.replaceAll("");
			if (country.matches("")) {
				next_element_exists = forward_to_next_element();
				continue;
			}

			airports.add(new IataAirport(code, name, country));

			next_element_exists = forward_to_next_element();

		}

		scanner.close();
		return airports;
	}
}
