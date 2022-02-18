package format;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExampleFile {

	public static void main(String[] args) throws IOException {
		Person p = new Person("name", "location");






		/*try (Writer writer = new FileWriter("Output.json")) {

			Gson gson = new GsonBuilder().create();
			gson.toJson(p, writer);
		}*/
	}
}
