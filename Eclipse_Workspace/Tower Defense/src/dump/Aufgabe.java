package dump;

/**
 * Aufgaben der Form int op int, wobei der Operator einer der 5 Grundrechenarten
 * ist. Alle Aufgaben sind ganzzahlig.
 * 
 * @author kbovi
 * @version 1.0 vom 11.09.2017
 *
 */
public class Aufgabe {

	public static final String OPS = "+-*/%";

	private int erster;
	private int zweiter;
	private int typ;

	/**
	 * @param erster
	 * @param zweiter
	 * @param typ
	 */
	public Aufgabe(int erster, int zweiter, int typ) {
		this.erster = erster;
		this.zweiter = zweiter;
		this.typ = typ;
	}

	/**
	 * @return the erster
	 */
	public int getErster() {
		return erster;
	}

	/**
	 * @return the zweiter
	 */
	public int getZweiter() {
		return zweiter;
	}

	/**
	 * @return the typ
	 */
	public int getTyp() {
		return typ;
	}

	public String toString() {
		return ("" + this.erster + " " + this.typ + " " + this.zweiter);
	}
}
