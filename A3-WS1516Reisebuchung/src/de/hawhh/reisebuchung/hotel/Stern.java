package de.hawhh.reisebuchung.hotel;

public enum Stern {
	EINS("*"), ZWEI("**"), DREI("***"), VIER("****"), FUENF("*****");
	
	private String stern;

	private Stern(String stern) {
		this.stern = stern;
	}

	@Override
	public String toString() {
		return stern;
	}
}
