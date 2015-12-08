package de.hawhh.reisebuchung.hotel;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import de.hawhh.akteure.Anbieter;
import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.Ort;

public class Hotel {

	private Anbieter anbieter;
	private String name;
	private Stern stern;
	private Ort ort;
	private Map<ZimmerTyp, GeldBetrag> zimmerPreisTabelle = new HashMap<ZimmerTyp, GeldBetrag>();
	private Map<Verpflegung, GeldBetrag> verpflegungsPreisTabelle = new HashMap<Verpflegung, GeldBetrag>();

	public Hotel(Anbieter anbieter, String name, Stern stern, Ort ort,
			ZimmerTyp[] zimmerTypen, GeldBetrag[] zimmerPreise,
			Verpflegung[] verpfls, GeldBetrag[] verpflPreise) {
		this.anbieter = anbieter;
		this.name = name;
		this.stern = stern;
		this.ort = ort;
		for (int i = 0; i < zimmerTypen.length; i++) {
			zimmerPreisTabelle.put(zimmerTypen[i], zimmerPreise[i]);
		}
		for (int i = 0; i < verpfls.length; i++) {
			verpflegungsPreisTabelle.put(verpfls[i], verpflPreise[i]);
		}
	}

	public GeldBetrag getPreis(ZimmerTyp zimmer, Verpflegung verpfl,
			Duration dauer) {
		return zimmerPreisTabelle.get(zimmer)
				.add(verpflegungsPreisTabelle.get(verpfl)).mult(dauer.toDays())
				.mult(anbieter.rabatt(dauer.toDays()));
	}

	public Ort getOrt() {
		return ort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anbieter == null) ? 0 : anbieter.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((stern == null) ? 0 : stern.hashCode());
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
		Hotel other = (Hotel) obj;
		if (anbieter == null) {
			if (other.anbieter != null)
				return false;
		} else if (!anbieter.equals(other.anbieter))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		} else if (!ort.equals(other.ort))
			return false;
		if (stern != other.stern)
			return false;
		return true;
	}

}
