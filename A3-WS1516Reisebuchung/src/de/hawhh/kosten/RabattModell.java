package de.hawhh.kosten;

import java.util.HashMap;
import java.util.Map;

public class RabattModell {
	private Map<Long, Double> rabattPreisTabelle = new HashMap<Long, Double>();

	public RabattModell(long[] wochen, double[] rabatte) {
		for (int i = 0; i < wochen.length; i++) {
			rabattPreisTabelle.put(wochen[i], rabatte[i]);
		}
	}

	public double get(long wochen) {
		return rabattPreisTabelle.get(wochen);
	}

	@Override
	public String toString() {
		String rabattListe = "";
		for (long key : rabattPreisTabelle.keySet())
			rabattListe += "Bei " + key
					+ " Wochen Aufenthalt bezahlen Sie nur noch "
					+ rabattPreisTabelle.get(key) + "% des Preises/n";
		return rabattListe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((rabattPreisTabelle == null) ? 0 : rabattPreisTabelle
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RabattModell other = (RabattModell) obj;
		if (rabattPreisTabelle == null) {
			if (other.rabattPreisTabelle != null)
				return false;
		} else if (!rabattPreisTabelle.equals(other.rabattPreisTabelle))
			return false;
		return true;
	}
}
