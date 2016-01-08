package iata.airline;

import iata.AbstractIataCollectionReader;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IataAirlineCollectionReader extends AbstractIataCollectionReader {
	public Collection<? extends IataAirline> readLocalCollection() {
		return readSingleCollection(getClass().getResource("iata_airline.htm"));
	}

	private Boolean forward_to_next_element(Scanner scanner) {
		if (scanner.findWithinHorizon("<tr>", 0) == null)
			return false;
		scanner.nextLine();
		return true;
	}

	private String process_String(String string) {
		Matcher matcher = null;
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

	protected Collection<? extends IataAirline> readSingleCollection(URL url) {

		LinkedList<IataAirline> airlines = new LinkedList<IataAirline>();

		try (Scanner scanner = new Scanner(url.openStream(), "UTF-8")) {
			String code = null;
			String name = null;
			String country = null;
			Matcher matcher = null;

			Boolean next_element_exists = forward_to_next_element(scanner);

			while (next_element_exists) {

				matcher = Pattern.compile("<td>([0-9].).*</td>").matcher(
						scanner.nextLine()); // code
				if (matcher.matches())
					code = matcher.group(1);
				else {
					next_element_exists = forward_to_next_element(scanner);
					continue;
				}

				name = process_String(scanner.nextLine()); // name
				if (name == null) {
					next_element_exists = forward_to_next_element(scanner);
					continue;
				}

				country = process_String(scanner.nextLine()); // country
				if (country == null) {
					next_element_exists = forward_to_next_element(scanner);
					continue;
				}

				airlines.add(new IataAirline(code, name, country));

				next_element_exists = forward_to_next_element(scanner);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return airlines;
	}
}
