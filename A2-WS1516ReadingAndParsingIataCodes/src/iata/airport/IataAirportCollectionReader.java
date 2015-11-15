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
	Matcher matcher = null;

	public Collection<? extends Iata> readLocalCollection() {
		Collection<IataAirport> airports = new LinkedList<IataAirport>();
		Collection<IataAirport> a;

		URL url = null;
		for (int i = 0; i < 26; i++) {
			url = getClass().getResource((char) (65 + i) + ".htm");
			a = (Collection<IataAirport>) readSingleCollection(url);
			airports.addAll(a);
		}

		return airports;
	}

	private Boolean forward_to_next_element() {
		if (null == scanner.findWithinHorizon("<tr>", 0))
			return false;
		scanner.nextLine();
		return true;
	}

	private String replace_tokens(String line) {

		matcher = Pattern.compile("<td>").matcher(line);
		line = matcher.replaceAll("");
		matcher = Pattern.compile("<a.*?>").matcher(line);
		line = matcher.replaceAll("");
		matcher = Pattern.compile("</a>").matcher(line);
		line = matcher.replaceAll("");
		matcher = Pattern.compile("<i>").matcher(line);
		line = matcher.replaceAll("");
		matcher = Pattern.compile("</i>").matcher(line);
		line = matcher.replaceAll("");
		matcher = Pattern.compile("</td>").matcher(line);
		return matcher.replaceAll("");
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) { // TODO
																			// public
																			// ???

		try {
			this.scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();

		String code = null;
		String name = null;
		String country = null;

		String line = null;

		Boolean next_element_exists = forward_to_next_element();

		while (next_element_exists) {

			code = null;
			name = "";

			line = scanner.nextLine(); // Code
			matcher = Pattern.compile("<td>(<s>)?(...)(</s>)?</td>").matcher(
					line);
			if (matcher.matches())
				code = matcher.group(2);
			else {
				next_element_exists = forward_to_next_element();
				continue;
			}

			line = scanner.nextLine(); // zweiter code
			if (line == null) {
				next_element_exists = forward_to_next_element();
				continue;
			}
			
			line = scanner.nextLine(); // Name
			if (line == null) {
				next_element_exists = forward_to_next_element();
				continue;
			}
			name = replace_tokens(line);

			line = scanner.nextLine(); // Ort
			if (line == null) {
				next_element_exists = forward_to_next_element();
				continue;
			}

			line = scanner.nextLine(); // Region
			if (line == null) {
				next_element_exists = forward_to_next_element();
				continue;
			}

			line = scanner.nextLine(); // Land
			if (line == null) {
				next_element_exists = forward_to_next_element();
				continue;
			}
			country = replace_tokens(line);

			line = scanner.nextLine(); // </tr>
			if (line == null) {
				next_element_exists = forward_to_next_element();
				continue;
			}

			if (code != null && !name.matches(""))
				airports.add(new IataAirport(code, name, country));

			next_element_exists = forward_to_next_element();

		}

		scanner.close();
		return airports;
	}
}
