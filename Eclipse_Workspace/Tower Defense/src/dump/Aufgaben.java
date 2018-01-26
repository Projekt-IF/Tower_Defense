package dump;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hier werden viele Aufgaben verwaltet, die aus einer TXT-Datei eingelesen
 * werden.
 * 
 * @author kbovi
 * @version 1.1 vom 11.09.2017
 *
 */
public class Aufgaben {

	private ArrayList<Aufgabe> dieAufgaben;
	private Random zufall;

	public static final String KOMMENTAR_BEGINN = "/";

	/**
	 * Der Konstruktor erzeugt eine leere Sammlung vn Aufgaben.
	 */
	public Aufgaben() {
		this.dieAufgaben = new ArrayList<>();
		this.zufall = new Random();
	}

	/**
	 * Eine zufaellige Aufgabe wird aus der Sammlung geliefert.
	 * 
	 * @return
	 */
	public Aufgabe getRandomAufgabe() {
		int anzahl = this.dieAufgaben.size();
		int zuf = this.zufall.nextInt(anzahl);
		return this.dieAufgaben.get(zuf);
	}

	/**
	 * Die Sammlung wird aufgebaut aus den Textzeilen der angegebenen Datei. Zeilen
	 * der Datei, die mit "/" beginnen, werden ignoriert. Erlaubte Rechenzeichen
	 * sind+, -, *, / und % Ist die Zeile nicht korrekt aufgebaut, wird sie nicht in
	 * die Sammlung uebernommen.
	 * 
	 * @param pFilename
	 * @throws IOException
	 */
	public void fileToAufgaben(String pFilename) throws IOException {
		BufferedReader brAufgaben = new BufferedReader(new FileReader(pFilename));
		String line = brAufgaben.readLine();
		while (line != null) {
			if (!line.startsWith(Aufgaben.KOMMENTAR_BEGINN)) {
				//System.out.println (line);
				aufgabeHinzu(line);
			}
			line = brAufgaben.readLine();
		}
		brAufgaben.close();
	}

	private void aufgabeHinzu(String str) {
		String regex = "(\\d+)\\s*([\\+\\-\\*/%])\\s*(\\d+)";
		// Backslash im Java Sourcecode verdoppeln!
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.matches()) {
			int erster = Integer.valueOf(matcher.group(1));
			int typ = Aufgabe.OPS.indexOf(matcher.group(2));

			int zweiter = Integer.valueOf(matcher.group(3));
			Aufgabe neue = new Aufgabe(erster, zweiter, typ);

//			System.out.println(neue);

			this.dieAufgaben.add(neue);
		}
	}
}
