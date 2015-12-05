package de.hawhh.reisebuchung;

import java.util.Collection;

public class RundReise extends AbstractReise {

	public RundReise(Collection<ReiseBaustein> rbs) {
		reiseBausteine.addAll(rbs);
	}

	public RundReise(ReiseBaustein... rbs) {
		for (int i = 0; i < rbs.length; i++) {
			reiseBausteine.add(rbs[i]);
		}
	}

}
