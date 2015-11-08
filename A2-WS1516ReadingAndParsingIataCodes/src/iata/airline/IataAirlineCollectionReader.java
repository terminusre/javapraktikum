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
		return readSingleCollection(getClass().getResource("iata_airline.htm"));
	}

	protected Collection<? extends Iata> readSingleCollection(URL url) {

		Scanner scanner = null;

		try {
			scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		String code = null;
		String name = null;
		String country = null;
		LinkedList airlines = new LinkedList();

		scanner.findWithinHorizon("<th>Bemerkung</th>", 0);
		Matcher matcher = null;

		String data = scanner.findWithinHorizon(Pattern.compile("<tr>"), 0);
		String line = scanner.nextLine();

//		while (!line.equals("</tr>")) {
//			System.out.println(line);
//			matcher = Pattern.compile("<td>([A-Z]{2}).*?</td>").matcher(line);
//			if (matcher.matches()){
//				code = matcher.group(1);
//				System.out.println(code);
//			} else
//				System.out.println("no match!");
//			name = "name";
//			country = "country";
//			airlines.add(new IataAirline(code, name, country));
//			line = scanner.nextLine();
//		}

		scanner.close();
		return airlines;
	}

}
