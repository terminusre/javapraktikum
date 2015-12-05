package de.hawhh.reisebuchung.flug;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeSet;

import de.hawhh.kosten.Euro;
import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.Ort;

class DirektFlugComp implements Comparator<DirektFlug> {

	@Override
	public int compare(DirektFlug direktFlug1, DirektFlug direktFlug2) {
		return direktFlug1.getBeginn().compareTo(direktFlug2.getBeginn());
		// TODO flug 1 und flug 2 muessen vielleicht getauscht werden
	}

}

public class StopOverFlug extends OneWayFlug {
	private TreeSet<DirektFlug> listeTeilfluege = new TreeSet<DirektFlug>(
			new DirektFlugComp());

	public boolean add(DirektFlug direktFlug) {
		DirektFlug vorgaenger = listeTeilfluege.lower(direktFlug);
		DirektFlug nachfolger = listeTeilfluege.higher(direktFlug);
		if (vorgaenger.getEnde().isAfter(direktFlug.getBeginn())
				&& direktFlug.getEnde().isBefore(nachfolger.getBeginn())) {
			listeTeilfluege.add(direktFlug);
			return true;
		}
		return false;
	}

	@Override
	public LocalDateTime getBeginn() {

		return listeTeilfluege.first().getBeginn();
	}

	@Override
	public LocalDateTime getEnde() {

		return listeTeilfluege.last().getEnde();
	}

	@Override
	public GeldBetrag getPreis() {
		Euro gesamtPreis = new Euro(0);
		listeTeilfluege.stream().forEach(
				preisTeilFlug -> gesamtPreis.add(preisTeilFlug.getPreis()));
		return gesamtPreis;
	}

	@Override
	public Ort getEndOrt() {
		return listeTeilfluege.last().getEndOrt();
	}

	@Override
	public Ort getStartOrt() {
		return listeTeilfluege.first().getStartOrt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listeTeilfluege == null) ? 0 : listeTeilfluege.hashCode());
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
		StopOverFlug other = (StopOverFlug) obj;
		if (listeTeilfluege == null) {
			if (other.listeTeilfluege != null)
				return false;
		} else if (!listeTeilfluege.equals(other.listeTeilfluege))
			return false;
		return true;
	}

}
