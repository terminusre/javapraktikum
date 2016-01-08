package de.hawhh.kosten;

import java.util.Random;

public class Dollar extends AbstractGeldBetrag {

	private double dollar_to_euro = 0.92;

	public Dollar(int geldbetrag) {
		this.geldbetrag = geldbetrag;
	}

	public Dollar(int untere_Grenze, int obere_Grenze) {
		Random rand = new Random();
		this.geldbetrag = rand.nextInt((obere_Grenze - untere_Grenze) + 1)
				+ untere_Grenze;
	}
	
	@Override
	public double inBasis() {
		return geldbetrag * dollar_to_euro;
	}

	@Override
	public GeldBetrag ausBasis(double basis) {
		return new Dollar((int) (basis / dollar_to_euro));
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
		return "¢";
	}

	@Override
	public String getMajorSymbol() {
		return "$";
	}

}
