package files;

import java.io.FileInputStream;
import java.io.IOException;

public class FileRead {

	public static void main(String[] args) {

		//put path to your file here
		try(FileInputStream fin=new FileInputStream("/Users/kate/Documents/Tes.txt"))
		{
			System.out.println("File size: " + fin.available() + " bytes");

			int i;
			while ((i = fin.read())!=-1) {

				System.out.print((char)i);
			}
		}
		catch(IOException ex){

			System.out.println(ex.getMessage());
		}

	}
}
