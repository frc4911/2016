
public class Main {

	// Runs the parser
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.ParseToCsv("logfile.txt", "logAsCsv.csv");
	}
}
