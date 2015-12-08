package de.hawhh.akteure;

import de.hawhh.kosten.RabattModell;

public class Anbieter {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Anbieter other = (Anbieter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private String name;
	private RabattModell rabattModell;

	public Anbieter(String name, RabattModell rabattModell) {
			this.name = name;
		this.rabattModell = rabattModell;
	}

	public double rabatt(long tage){
		return rabattModell.get(tage/7);
	}

	@Override
	public String toString() {
		return "Anbieter [name=" + name + ", rabattModell=" + rabattModell
				+ "]";
	}
}
