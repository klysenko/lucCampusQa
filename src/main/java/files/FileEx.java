package files;

import java.io.FileInputStream;
import java.io.IOException;

public class FileEx {
	public static void main(String[] args) throws IOException {

		//put path to your file here
		FileInputStream fin = new FileInputStream("/Users/kate/Documents/Test.txt");

		System.out.println("Channel: " + fin.getChannel());

		System.out.println("File description: " + fin.getFD().toString());

		System.out.println("Available bytes:" + fin.available());

		long skip = fin.skip(10);
		System.out.println("skip" + skip);
		System.out.println("Number of remaining bytes:" + fin.available());
		System.out.println("FileContents :");

		int ch;
		while ((ch = fin.read()) != -1) {
			System.out.print( (char) ch);
		}

		fin.close();

	}
}
