package de.hawhh.reisebuchung;

public class Ort {

	private String name;

	public Ort() {
		this.name = "$UNKNOWN$";
	}

	public Ort(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Ort [" + (name != null ? name : "") + "]";
	}

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
		Ort other = (Ort) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}