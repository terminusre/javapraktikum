package de.hawhh.reisebuchung.mietwagen;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import de.hawhh.kosten.GeldBetrag;
import de.hawhh.kosten.RabattModell;

public class MietwagenAnbieter {
	private String name;
	private RabattModell rabattModell;
	private Map<WagenTyp, GeldBetrag> tagesPreisTabelle = new HashMap<WagenTyp, GeldBetrag>();

	public MietwagenAnbieter(String name, RabattModell rabattModell,
			WagenTyp[] wagentypen, GeldBetrag[] tagesPreise) {
		this.name = name;
		this.rabattModell = rabattModell;
		for (int i = 0; i < wagentypen.length; i++) {
			tagesPreisTabelle.put(wagentypen[i], tagesPreise[i]);
		}
	}

	public GeldBetrag getPreis(WagenTyp typ, Duration mietdauer) {
		return tagesPreisTabelle.get(typ).mult(mietdauer.toDays())
				.mult(rabattModell.get((long) (mietdauer.toDays() / 7.0)));
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((rabattModell == null) ? 0 : rabattModell.hashCode());
		result = prime
				* result
				+ ((tagesPreisTabelle == null) ? 0 : tagesPreisTabelle
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
		MietwagenAnbieter other = (MietwagenAnbieter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rabattModell == null) {
			if (other.rabattModell != null)
				return false;
		} else if (!rabattModell.equals(other.rabattModell))
			return false;
		if (tagesPreisTabelle == null) {
			if (other.tagesPreisTabelle != null)
				return false;
		} else if (!tagesPreisTabelle.equals(other.tagesPreisTabelle))
			return false;
		return true;
	}

}
