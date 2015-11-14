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

	public Collection<? extends Iata> readLocalCollection() {
		Collection<IataAirport> airports = new LinkedList<IataAirport>();
		Collection<IataAirport> a;

//		URL url = null;
//		for (int i = 0; i < 26; i++) {
//			url = getClass().getResource((char) (65 + i) + ".htm");
//			a = (Collection<IataAirport>) readSingleCollection(url);
//			airports.addAll(a);
//			System.out.println("url: " + url);
//		}

		 airports = (Collection<IataAirport>) readSingleCollection(getClass()
		 .getResource("E.htm"));

		return airports;
	}

	public Collection<? extends Iata> readSingleCollection(URL url) {

		Scanner scanner = null;

		try {
			scanner = new Scanner(url.openStream(), "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}

		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();

		String code = null;
		String name = null;
		String country = null;

		Matcher matcher = null;
		String line = null;

		scanner.findWithinHorizon("<table.*>", 0);
		for (int i = 0; i < 9; i++)
			scanner.nextLine();

		while (true) {

			line = scanner.nextLine(); // <tr>
			if (line.matches("</table>")) {
				scanner.nextLine();
				if (scanner.nextLine().matches("<table.*>")) {
					for (int i = 0; i < 9; i++)
						scanner.nextLine();
				} else
					break;
			}

			line = scanner.nextLine(); // Code
			matcher = Pattern.compile("<td>(...)</td>").matcher(line);
			if (matcher.matches())
				code = matcher.group(1);
			else {
				System.out.println("code: " + line);
			}

			scanner.nextLine(); // zweiter code

			line = scanner.nextLine(); // Name
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
			name = matcher.replaceFirst("");

			scanner.nextLine(); // Ort

			scanner.nextLine(); // Region

			line = scanner.nextLine(); // Land
			matcher = Pattern.compile("<td><a.*?>").matcher(line);
			line = matcher.replaceAll("");
			matcher = Pattern.compile("</a>").matcher(line);
			line = matcher.replaceAll("");
			matcher = Pattern.compile("<i>").matcher(line);
			line = matcher.replaceAll("");
			matcher = Pattern.compile("</i>").matcher(line);
			line = matcher.replaceAll("");
			matcher = Pattern.compile("</td>").matcher(line);
			country = matcher.replaceFirst("");

			scanner.nextLine(); // </tr>

			if (code != null && !name.matches(""))
				airports.add(new IataAirport(code, name, country));

		}

		scanner.close();
		return airports;
	}
}
