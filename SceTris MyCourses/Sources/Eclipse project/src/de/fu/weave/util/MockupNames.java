/* Names.java / 2:10:48 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.util;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import de.fu.junction.annotation.meta.Author;

/**
 * 
 */
@Author("Julian Fleischer")
public class MockupNames {
	public static class Name implements Comparable<Name> {
		final public String firstName;
		final public String lastName;

		Name(final String first, final String last) {
			firstName = first;
			lastName = last;
		}

		@Override
		public int compareTo(final Name name) {
			if (name.firstName.equals(firstName)) return name.lastName.compareTo(lastName);
			return name.firstName.compareTo(firstName);
		}

		public boolean equals(final Name obj) {
			return obj.firstName.equals(firstName) && obj.lastName.equals(lastName);
		}
	}

	private final Set<Name> names = new TreeSet<Name>();

	private final Random rand;

	public static final String[] firstNames = {
			"André", "Andrej", "Miroslav", "Sasha", "Sascha",
			"Michel", "Michael", "Miriam", "Heinz", "Judith", "Albertine",
			"Angela", "Elisabeth", "Alexander", "Georg", "Gregor", "Philip",
			"Philipp", "Markus", "Marcus", "Hartmuth", "Dagobert", "Kristin",
			"Alexej", "Mira", "Sandra", "Ludmilla", "Darina", "Arielle", "Mercedes",
			"Dimitri", "Olga", "Sara", "Sarah", "Hanna", "Hannah", "Alina",
			"Sophokles", "Rita", "Nora", "Linda", "Alicia", "Parmenides",
			"Anton", "Nicolai", "Christian", "Kunigunde", "Tusnelda", "Katja",
			"Ida", "Siegfried", "Frithjof", "Baldur", "Karol", "Axel", "Tanja",
			"Benedict", "Konrad", "Hagen", "Peter", "Rudolph", "Björn", "Darja",
			"Ada", "Alan", "Donald", "Bill", "Al", "Jim", "Jack", "John",
			"Marcellus", "Kurt", "Nero", "Christina", "Julia", "Juliana",
			"Momo", "Hera", "Darius", "Sauron", "Ivo", "Ole", "Ronja", "Dina",
			"Tom", "Hinrich", "Heinrich", "Benjamin", "Shandor", "Kai", "Diane",
			"Thomas", "Lilo", "Harry", "Oliver", "Malina", "Mara", "Kostja",
			"Tim", "Stanlislaw", "Marc", "Mark", "Marccella", "Paul", "Diana",
			"Krishan", "Cengiz", "Dennis", "Karoline", "Carolin", "Janik",
			"Anna", "Janis", "Katrin", "Hillary", "Lena", "Leon", "Anabelle",
			"Frankegon", "Frank", "Franziska", "Christoph", "Franz", "Audrey",
			"Justin", "Britney", "Rachel", "Woicjiech", "Jan", "Janko",
			"Maren", "Svenja", "Johannes", "Jonathan", "Jördis", "Jennifer",
			"Jolke", "Maria", "Raoul", "Fritz", "Friedrich", "Wilhelm", "Luise",
			"Abraham", "Isaac", "Anneliese", "Douglas", "Lothar", "Ulrich", "Uwe",
			"Angelina", "Amanda", "Kingda", "Indira", "Walt", "Duy", "Minh", "Clara",
			"Karl", "Viet", "Max", "Maximilian", "Mirco", "Thilo", "Bruce", "Nick",
			"Elaine", "Alice", "Bob", "Mallory", "Cedrick", "Joseph", "Josef", "Samson",
			"Astrid", "Napoleon", "Aurel", "August", "Julius", "Mathilda", "Matt",
			"Mona", "Lisa", "Oscar", "Norah", "Oskar", "Gloria", "Volker", "Xavier",
			"Yann", "Janick", "Yoko", "Connor", "Esmeralda", "Irmgard", "Adelheid",
			"Julie", "Yvonne", "Bertold", "Bono", "Heienr", "Jonas", "Alfred",
			"Albert", "Robin", "Raymond", "Reinhard", "Reinhardt", "Julien", "Julian",
			"Julio", "Juan", "Don", "Homer", "Marge", "Bart", "Marvin", "Manuel", "Aron",
			"Theresa", "William", "Jean", "Jean-Baptiste", "Quentin", "Fridolin", "Zoe",
			"Jerome", "Dy", "Helena", "Marius", "Terence", "Bud", "Cherokee",
			"Beatrice", "Beatrix", "Sebastian", "Timothy", "Scala", "Cedric", "Ansgar",
			"Hermione", "Claudia", "Claude", "Gregory", "Massimo", "Mahmud", "Mahatma",
			"Henry"
	};

	public static final String[] lastNames = {
			"Müller", "Meier", "Meyer", "Speer", "Mord", "Reintjes", "Nienhaus",
			"Abdallah", "Schäfer", "Hirte", "Lem", "Rudolph", "Metz", "Morgenstern",
			"Abendland", "Obama", "Fetcher", "Pitt", "Olafson", "Hooth", "Zorn",
			"Bieda", "Schopenhauer", "Hegel", "Heidegger", "Kant", "Berlin",
			"Feuerbach", "Yorck", "van Bebber", "Tietz", "Terwege", "Murnau", "Lang",
			"Leibniz", "Einstein", "Schwarzstein", "Hammersmark", "Smith", "Schmidt",
			"Washington", "Adenauer", "Schultheiß", "Silver", "Goldstein", "Friedmann",
			"Kavka", "Kafka", "Kriegel", "Kornhuber", "Fynn", "Mann", "Cooper", "Molliere",
			"Kane", "de Maizière", "Westerwelle", "Riemann", "Turing", "Palmer", "Viglione",
			"Leibniz", "Newton", "Neumann", "Lewis", "Todd", "Martin", "Wittgenstein",
			"Urlaub", "Karg", "Holthausen", "Tenbeck", "Bombardier", "Siemens", "Krupp",
			"Schindler", "Schicklgruber", "Treppenbauer", "Mahnke", "von Tronje", "Xanten",
			"Schütze", "Stier", "Löwe", "Steinbock", "Heide", "Merkel", "Depp", "Hubertus",
			"Landa", "Lindgren", "Drachentöter", "Waage", "Bertram", "Schlaf",
			"Monroe", "Midgard", "Kalari", "Karan", "Zoufahl", "Krieg", "Bürgel", "Zufall",
			"Reiche", "Eiche", "Bialik", "Hörbelt", "Wagenknecht", "Rosing", "Ghandi",
			"Knopf", "Ping", "Xao", "Han", "Matsumotu", "Disney", "Mertesacker",
			"Jericho", "Buchbinder", "Fassbinder", "König", "Antonius", "Thalmann",
			"Brützel", "Eggbert", "Fleischer", "Klimek", "Huberty", "Winzmann", "Furtmann",
			"Balke", "Förster", "Kuhlmann", "Kuhmann", "Formeny", "Chopin", "Bach", "Mozart",
			"Beethoven", "Händel", "Gauss", "Humboldt", "Gauß", "Geiz", "Do", "Tran", "Rottmann",
			"Terror", "Angst", "Behme", "Wilkat", "Pietsch", "Buhlmann", "Scholz", "Schulz",
			"Essing", "Aalten", "Aalen", "Schlag", "Schuhmacher", "Twain", "Freitag",
			"Freytag", "Sondermann", "von Fuss", "Veldboer", "Tausch", "Thyss", "Borgers",
			"Vaseva", "Michels", "Naphtali", "Schall", "Rauch", "Mantis", "Groch",
			"Huberty", "Keller", "Zuckerberg", "Berg", "Tackmann", "Schaffhausen",
			"Kohl", "Kopf", "Kohlkopf", "Gegensatz", "Zwetschgenbrot", "Künast", "Wowereit",
			"McCarthy", "Lincoln", "Silberstein", "Silberbach", "Silver", "Long", "Brandt",
			"Leipzig", "Spiering", "Goethe", "Schiller", "Lessing", "Marx", "Engels", "Engel",
			"Teufel", "Anders", "Bohlen", "Spears", "Apple", "Gates", "Jobs", "Ballmer", "Packard",
			"Hollerith", "Zuse", "Armstrong", "Lee", "Taylor", "Dylan", "Marley", "Sinatra",
			"Kennedy", "Miller", "Estefan", "Knef", "Weil", "Brecht", "Manson", "Jondo", "Goldt",
			"Widmann", "Zimmer", "Panzer", "Kuttner", "Kutter", "Wismar", "Bismarck", "Nash",
			"Forbes", "Panizza", "Schwarz", "Schwartz", "Tiefschwarz", "Gysi", "Jones", "Oberst",
			"Badun", "Beirut", "Bonaparte", "Dust", "Myriad", "Norad", "Thiel", "Dulles", "Foster",
			"Rojás", "Bruns", "Kissinger", "Kissingen", "Krieger", "Mörder", "Stahl", "Steel",
			"Hood", "Shakespeer", "Molinar", "Grenouille", "Fourier", "Dernier", "Mourier",
			"Lamarck", "Dilbert", "Munroe", "Ewing", "Travolta", "Tarantino", "Spencer", "Hill",
			"Tempo", "Tallahassee", "Pollock", "Michaels", "Williams", "Wallace", "Hitchcock",
			"Dreyfuss", "Dreyfuß", "Dreistein", "Zweistein", "Zweifuss", "Zweifuß", "Auge",
			"van Damme", "Gosling", "Johanson", "Riddle", "Young", "Jung", "Marachi",
			"Elend", "Sorge", "Sierpinski"
	};

	public MockupNames() {
		rand = new Random();
	}

	public MockupNames(final Random rand) {
		this.rand = rand;
	}

	public Name nextName() {
		if (names.size() < firstNames.length * lastNames.length) {
			Name name;
			do
				name = new Name(firstNames[rand.nextInt(firstNames.length)],
							lastNames[rand.nextInt(lastNames.length)]);
			while (names.contains(name));
			names.add(name);
			return name;
		}
		throw new RuntimeException(
				"Can’t produce any unique names anymore, known names are too little");
	}
}
