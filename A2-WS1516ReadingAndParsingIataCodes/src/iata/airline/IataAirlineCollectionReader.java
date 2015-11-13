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
		LinkedList<IataAirline> airlines = new LinkedList<IataAirline>();

		scanner.findWithinHorizon("<th>Bemerkung</th>", 0);
		scanner.nextLine();
		scanner.nextLine();
		scanner.nextLine();

		Matcher matcher = null;
		String line = "";

		while (!line.matches("</table>")) {
			line = scanner.nextLine();
			matcher = Pattern.compile("<td>(..).*</td>").matcher(line);
			if (matcher.matches())
				code = matcher.group(1);
			else {
				System.out.println("code: " + line);
				break;
			}

			line = scanner.nextLine();
			matcher = Pattern.compile("<td>.*?>(.*)</a>.*</td>").matcher(line);
			if (matcher.matches())
				name = matcher.group(1);
			else {
				matcher = Pattern.compile("<td>(.*)</td>").matcher(line);
				if (matcher.matches())
					name = matcher.group(1);
				else {
					System.out.println("name: " + line);
					break;
				}
			}

			line = scanner.nextLine();
			matcher = Pattern.compile("<td>.*title=\"(.*?)\".*.*</td>")
					.matcher(line);
			if (matcher.matches())
				country = matcher.group(1);
			else {
				matcher = Pattern.compile("<td>(.*)</td>").matcher(line);
				if (matcher.matches())
					country = matcher.group(1);
				else {
					System.out.println("country: " + line);
					break;
				}
			}

			line = scanner.nextLine(); // Bemerkung
			if (!line.matches("</tr>"))
				scanner.nextLine();

			airlines.add(new IataAirline(code, name, country));

			line = scanner.nextLine(); // <tr>
		}

		scanner.close();
		return airlines;
	}
}
