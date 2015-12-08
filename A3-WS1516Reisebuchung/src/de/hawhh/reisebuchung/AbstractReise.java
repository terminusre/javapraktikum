package de.hawhh.reisebuchung;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.TreeSet;

import de.hawhh.kosten.Euro;
import de.hawhh.kosten.GeldBetrag;

public abstract class AbstractReise implements Reise {
	private TreeSet<ReiseBaustein> reiseBausteine = new TreeSet<ReiseBaustein>(
			new Comparator<ReiseBaustein>() {
				@Override
				public int compare(ReiseBaustein o1, ReiseBaustein o2) {
					return o1.getBeginn().compareTo(o2.getBeginn());
				}
			}
	);

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
		reiseBausteine
				.stream()
				.map(reisebaustein -> reisebaustein.getEnde())
				.max((reiseBaustein1, reiseBaustein2) -> reiseBaustein1
						.compareTo(reiseBaustein2));

		return reiseBausteine.last().getEnde();
	}

	@Override
	public GeldBetrag getPreis() {
		return reiseBausteine.stream()
				.map(reiseBaustein -> reiseBaustein.getPreis())
				.reduce(new Euro(0), (preis1, preis2) -> preis1.add(preis2));
	}

	@Override
	public Duration getDauer() {
		return Duration.between(getBeginn(), getEnde());
	}

	@Override
	public Ort getEndOrt() {
		ReiseBaustein maxi = reiseBausteine.first();
		for (ReiseBaustein reiseBaustein : reiseBausteine) {
			if (reiseBaustein.getEnde().isAfter(maxi.getEnde()))
				maxi = reiseBaustein;
		}
		return maxi.getEndOrt();
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

	@Override
	public String toString() {
		return "ReiseBausteine = " + reiseBausteine;
	}

}
