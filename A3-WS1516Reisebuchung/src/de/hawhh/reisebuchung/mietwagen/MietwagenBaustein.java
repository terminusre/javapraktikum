package de.hawhh.reisebuchung.mietwagen;

import java.time.Duration;

import de.hawhh.kosten.GeldBetrag;
import de.hawhh.reisebuchung.ReiseBaustein;

public abstract class MietwagenBaustein extends ReiseBaustein  {
	private Mietwagen wagen;
	private Uebergabe abholung;
	private Uebergabe abgabe;
	
	public MietwagenBaustein(Mietwagen wagen, Uebergabe abholung, Uebergabe abgabe){
		this.wagen = wagen;
		this.abholung = abholung;
		this.abgabe = abgabe;
	}
	
	public Duration getDauer(){
		return Duration.between(abholung.getZeitpunkt(), abgabe.getZeitpunkt());
	}
	
	public GeldBetrag getPreis(){
		return wagen.getMietwagenAnbieter().getPreis(wagen.getWagenTyp(), getDauer());
	}
	
}
