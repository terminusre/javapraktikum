package de.hawhh.reisebuchung;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeSet;

import de.hawhh.kosten.Euro;
import de.hawhh.kosten.GeldBetrag;

class ReiseBausteinComp implements Comparator<ReiseBaustein> {

	@Override
	public int compare(ReiseBaustein reiseBaustein1,
			ReiseBaustein reiseBaustein2) {
		return reiseBaustein1.getBeginn().compareTo(reiseBaustein2.getBeginn());
		// TODO ReiseBaustein 1 und ReiseBaustein 2 muessen vielleicht getauscht
		// werden
	}

}

public abstract class AbstractReise implements Reise {
	TreeSet<ReiseBaustein> reiseBausteine = new TreeSet<ReiseBaustein>(
			new ReiseBausteinComp());

	protected void add(ReiseBaustein reiseBaustein) {
		reiseBausteine.add(reiseBaustein);
	}

	protected void remove(ReiseBaustein reiseBaustein) {
		reiseBausteine.remove(reiseBaustein);
	}

	@Override
	public LocalDateTime getBeginn() {
		return reiseBausteine.first().getBeginn();
	}

	@Override
	public LocalDateTime getEnde() {
		return reiseBausteine.last().getEnde();
	}

	@Override
	public GeldBetrag getPreis() {
		Euro gesamtPreis = new Euro(0);
		reiseBausteine.stream().forEach(
				reiseBaustein -> gesamtPreis.add(reiseBaustein.getPreis()));
		return gesamtPreis;
	}

	@Override
	public Duration getDauer() {
		Duration gesamtDauer = Duration.ZERO;
		reiseBausteine.stream().forEach(
				reiseBaustein -> gesamtDauer.plus(reiseBaustein.getDauer()));
		return gesamtDauer;
	}

	@Override
	public Ort getEndOrt() {
		return reiseBausteine.last().getEndOrt();
	}

	@Override
	public Ort getStartOrt() {
		return reiseBausteine.first().getStartOrt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((reiseBausteine == null) ? 0 : reiseBausteine.hashCode());
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
		AbstractReise other = (AbstractReise) obj;
		if (reiseBausteine == null) {
			if (other.reiseBausteine != null)
				return false;
		} else if (!reiseBausteine.equals(other.reiseBausteine))
			return false;
		return true;
	}

}
