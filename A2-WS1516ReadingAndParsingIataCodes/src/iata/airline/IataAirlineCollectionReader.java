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

		LinkedList airlines = new LinkedList();
		//
		// Pattern airline_pattern = Pattern.compile("<tr>.*?</tr>");
		//
		// scanner.findWithinHorizon(airline_pattern, 0);
		//
		// String data = scanner.findWithinHorizon(airline_pattern, 0);
		// if (data == null)
		// System.out.println("fail");
		// while (data != null) {
		// airlines.add(new IataAirline("a", "b", "c"));
		// System.out.println(data);
		// data = scanner.findWithinHorizon(airline_pattern, 0);
		// }

		String tst = "<tst> Daten1 \n Daten2 </tst>";
		Pattern p = Pattern.compile("<tst>(.*)</tst>");
		Matcher m = p.matcher(tst);
		String res = "false";
		if (m.matches())
			res = m.group(1);
		System.out.println(res);

		String code = null;
		String name = null;
		String country = null;

		Matcher matcher = null;
		
		if(matcher.matches()){
			code = matcher.group(1);
		}
		code = 
		scanner.findWithinHorizon("<th>Bemerkung</th>", 0);

		String line = null;
		// String data = scanner.findWithinHorizon("<tr>", 0);

		// while (data != null) {
		// line = scanner.nextLine();
		// data = data + " " + line;
		// System.out.println(data);
		// data = scanner.findWithinHorizon("<tr>", 0);
		// }
		airlines.add(new IataAirline(code, name, country));

		scanner.close();
		return airlines;
	}

}
