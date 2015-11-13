package iata.airport;

import iata.AbstractIataCollectionReader;
import iata.Iata;
import iata.airline.IataAirline;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IataAirportCollectionReader extends AbstractIataCollectionReader {

	public Collection<? extends Iata> readLocalCollection() {
		Collection<? extends Iata> airports = new LinkedList<IataAirport>();
		Collection<? extends Iata> a;

		URL url = null;
		for (int i = 0; i < 26; i++) {
			//url = getClass().getResource((char) (65 + i) + ".htm");
			url = getClass().getResource("A.htm");
			a = readSingleCollection(url);
			airports = a;
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

		String code = null;
		String name = null;
		String country = null;
		LinkedList<IataAirport> airports = new LinkedList<IataAirport>();

		scanner.findWithinHorizon("<th style=\"width:17%;\">Land</th>", 0);
		scanner.nextLine();
		scanner.nextLine();
		scanner.nextLine();

//		for(int i = 0; i < 1; i++)
//			System.out.println(scanner.nextLine());
		
		Matcher matcher = null;
		String line = "";

//		<tr>
//		<td>AAA</td>
//		<td>NTGA</td>
//		<td><a href="/wiki/Flughafen_Anaa" title="Flughafen Anaa">Flughafen Anaa</a></td>
//		<td><a href="/wiki/Anaa_(Gemeinde)" title="Anaa (Gemeinde)">Anaa</a></td>
//		<td><a href="/wiki/Tuamotu-Archipel" title="Tuamotu-Archipel">Tuamotu-Archipel</a></td>
//		<td><a href="/wiki/Franz%C3%B6sisch-Polynesien" title="Französisch-Polynesien">Französisch-Polynesien</a></td>
//		</tr>
		
		while (!line.matches("</table>")) {
			
			line = scanner.nextLine(); // Code
			matcher = Pattern.compile("<td>(...)</td>").matcher(line);
			if (matcher.matches())
				code = matcher.group(1);
			else {
				System.out.println("code: " + line);
				break;
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
					break;
				}
			}

			scanner.nextLine(); // Ort
			
			scanner.nextLine(); // Region
			
			line = scanner.nextLine(); // Land
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

//			line = scanner.nextLine();
//			if (!line.matches("</tr>"))
//				scanner.nextLine();

			airports.add(new IataAirport(code, name, country));

			scanner.nextLine(); // </tr>
			line = scanner.nextLine(); // <tr>
		}

		scanner.close();
		return airports;
	}
}
