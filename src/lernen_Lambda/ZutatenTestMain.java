package lernen_Lambda;

/* Tutorial: http://www.codeadventurer.de/?p=3166
Weitere Informationen:
https://softech.cs.uni-kl.de/se1/ws16/_media/offiziell/26_JavaLambdas.pdf
http://javatricks.de/tricks/anonyme-klassen
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ZutatenTestMain {

	public static void ausreichendZutaten(Predicate<List<Zutat>> p, List<Zutat> zutaten) {
		if (p.test(zutaten)) {
			System.out.println("Ausreichend Zutaten vorhanden.");
		} else {
			System.out.println("Nicht genügend Zutaten vorhanden.");
		}
	}

	public static void main(String[] args) {

		Zutat z = new Zutat();

		// Lambda Ausdrücke
		final int ANZ_ZWIEBEL = 10;
		// Lambda Ausdruck
		ZutatVerarbeiten zv = (a) -> {
			if (a <= ANZ_ZWIEBEL) {
				System.out.println(a + " Zwiebel verarbeitet!");
				return true;
			} else {
				System.out.println("Nicht genügend Zwiebel auf Lager");
				return false;
			}
		};
		// Aufruf der Klassenmethode "zubereiten" mit Lambda Ausdruck
		boolean erfolg = z.zubereiten(10, zv);

		// Zutaten
		List<Zutat> zutaten = new ArrayList<>();

		zutaten.add(new Zutat("Paprika"));
		zutaten.add(new Zutat("Tomaten"));
		zutaten.add(new Zutat("Schinken"));
		zutaten.add(new Zutat("Salami"));
		zutaten.add(new Zutat("Peperoni"));
		zutaten.add(new Zutat("Zwiebel"));

		zutaten.forEach(zutat -> System.out.println(zutat.getName()));

		// Lambda Ausdruck
		Predicate<List<Zutat>> pred = (zL) -> zL.size() > 5;
		// Methoden-Aufruf mit Lambda Ausdruck
		ausreichendZutaten(pred, zutaten);

		// Lambda Ausdruck als Filter
		Predicate<Zutat> p = (y) -> (y.getName().equals("Zwiebel") || y.getName().equals("Tomaten"));
		// Stream mit Lambda als Filter
		zutaten.stream().filter(p).forEach((xy) -> System.out.println(xy.getName()));
		// Stream mit Lambda Ausdruck als Filter + Map
		zutaten.stream().filter(p).map((xyz) -> {
			xyz.setName(xyz.getName() + " verarbeitet");
			return xyz;
		}).forEach((xyz) -> System.out.println(xyz.getName()));

		// Von: http://javatricks.de/tricks/anonyme-klassen
		// Anonyme Klasse
		final List<String> names = Arrays.asList("Thomas", "Anja", "Heinz");
		List<String> loggingList = new ArrayList<String>(names) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			private void log(String s) {
				System.out.println(this.toString() + " + " + s);
			}

			@Override
			public boolean add(String s) {
				log(s);
				return super.add(s);
			}
		};
		loggingList.add("Maria");
		loggingList.add("Susi");

		// Anonyme Klasse
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("I run!");
			}
		}).start();
		// Lambda Ausdruck
		new Thread(() -> System.out.println("I run, too!")).start();

	}

}
