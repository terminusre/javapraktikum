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

	private IataAirport getAirport(Scanner scanner) {
		IataAirport airport = null;

		String code = null;
		String name = null;
		String country = null;

		Matcher matcher = null;
		String line = "";

		scanner.nextLine(); // <tr>

		line = scanner.nextLine(); // Code
		matcher = Pattern.compile("<td>(...)</td>").matcher(line);
		if (matcher.matches())
			code = matcher.group(1);
		else {
			System.out.println("code: " + line);
		}

		scanner.nextLine(); // zweiter code

		line = scanner.nextLine(); // Name
		matcher = Pattern.compile("<td>.*?>(.*)</a>.*</td>").matcher(line);
		if (matcher.matches())
			name = matcher.group(1);
		else {
			matcher = Pattern.compile("<td>(.*)</td>").matcher(line);
			if (matcher.matches())
				name = matcher.group(1);
			else {
				System.out.println("name: " + line);
			}
		}

		scanner.nextLine(); // Ort

		scanner.nextLine(); // Region

		line = scanner.nextLine(); // Land
		matcher = Pattern.compile("<td>.*title=\"(.*?)\".*.*</td>").matcher(
				line);
		if (matcher.matches())
			country = matcher.group(1);
		else {
			matcher = Pattern.compile("<td>(.*)</td>").matcher(line);
			if (matcher.matches())
				country = matcher.group(1);
			else {
				System.out.println("country: " + line);
			}
		}

		scanner.nextLine(); // </tr>

		if (code != null && name != null && country != null)
			airport = new IataAirport(code, name, country);

		return airport;
	}

	public Collection<? extends Iata> readLocalCollection() {
		Collection<IataAirport> airports = new LinkedList<IataAirport>();
		Collection<IataAirport> a;

		URL url = null;
		for (int i = 0; i < 1; i++) { // TODO i < 26
			url = getClass().getResource((char) (65 + i) + ".htm");
			a = (Collection<IataAirport>) readSingleCollection(url);
			airports.addAll(a);
		}

		return airports;
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {

		Scanner scanner = null;

		try {
			scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();

		scanner.findWithinHorizon("<th style=\"width:17%;\">Land</th>", 0);
		scanner.nextLine();
		scanner.nextLine();

		// for(int i = 0; i < 1; i++)
		// System.out.println(scanner.nextLine());

		IataAirport airport = null;

		do {
			airport = getAirport(scanner);
			if (airport != null)
				airports.add(airport);
			else
				break;
		} while (airport != null);

		scanner.close();
		return airports;
	}
}
