package de.hawhh.kosten;

import java.util.Random;

public class Euro extends AbstractGeldBetrag {

	public Euro(int geldbetrag) {
		this.geldbetrag = geldbetrag;
	}

	public Euro(int untere_Grenze, int obere_Grenze) {
		Random rand = new Random();
		this.geldbetrag = rand
				.nextInt((obere_Grenze - untere_Grenze) + 1000000)
		// TODO
		// this.geldbetrag = rand.nextInt((obere_Grenze - untere_Grenze) + 1)
				+ untere_Grenze;
	}

	@Override
	public double inBasis() {
		return (double) geldbetrag;
	}

	@Override
	public GeldBetrag ausBasis(double basis) {
		return new Euro(geldbetrag);
	}

	@Override
	public int getMajor() {
		return geldbetrag / 100;
	}

	@Override
	public int getMinor() {
		return geldbetrag % 100;
	}

	@Override
	public String getMinorSymbol() {
		return "ct";
	}

	@Override
	public String getMajorSymbol() {
		return "€";
	}

}
