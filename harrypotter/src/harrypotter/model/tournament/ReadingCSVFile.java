package harrypotter.model.tournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingCSVFile {
	public static void readFile(String path) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(path);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
		System.out.println(currentLine);
		// Parsing the currentLine String
		}
		br.close();

	}
	public static void main(String[] args) throws IOException{
		readFile("Potions.csv");
		}
}
