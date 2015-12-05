package iata.airport;

import iata.AbstractIataCollectionReader;
import iata.Iata;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IataAirportCollectionReader extends AbstractIataCollectionReader {

	public LinkedList<IataAirport> readLocalCollection() {
		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();
		LinkedList<IataAirport> airports_Teilliste;

		URL url = null;
		for (char c = 'A'; c <= 'Z'; c++) {
			url = getClass().getResource(c + ".htm");
			airports_Teilliste = readSingleCollection(url);
			airports.addAll(airports_Teilliste);
		}

		return airports;
	}

	private Boolean forward_to_next_element(Scanner scanner) {
		if (null == scanner.findWithinHorizon("<tr>", 0))
			return false;
		scanner.nextLine();
		return true;
	}

	public LinkedList<IataAirport> readSingleCollection(URL url) {

		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();

		try (Scanner scanner = new Scanner(url.openStream(), "UTF-8")) {

			Matcher matcher = null;

			String code = null;
			String name = null;
			String country = null;
			String location = null;

			Boolean next_element_exists = forward_to_next_element(scanner);

			while (next_element_exists) {

				matcher = Pattern.compile("<td>(<s>)?(...)(</s>)?</td>")
						.matcher(scanner.nextLine()); // code
				if (matcher.matches())
					code = matcher.group(2);
				else {
					next_element_exists = forward_to_next_element(scanner);
					continue;
				}

				scanner.nextLine(); // 2nd code

				matcher = Pattern.compile("<.*?>").matcher(scanner.nextLine()); // name
				name = matcher.replaceAll("");
				if (name.matches("")) {
					next_element_exists = forward_to_next_element(scanner);
					continue;
				}

				matcher = Pattern.compile("<.*?>").matcher(scanner.nextLine()); // location
				location = matcher.replaceAll("");

				scanner.nextLine(); // region

				matcher = Pattern.compile("<.*?>").matcher(scanner.nextLine()); // country
				country = matcher.replaceAll("");
				if (country.matches("")) {
					next_element_exists = forward_to_next_element(scanner);
					continue;
				}

				airports.add(new IataAirport(code, name, country, location));

				next_element_exists = forward_to_next_element(scanner);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return airports;
	}
}
