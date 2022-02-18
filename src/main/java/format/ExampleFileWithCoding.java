package format;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExampleFileWithCoding {

	public static void main(String[] args) throws IOException {
		try(Writer writer = new OutputStreamWriter(new FileOutputStream("Output.json") , StandardCharsets.US_ASCII)){
			Gson gson = new GsonBuilder().create();
			gson.toJson("Hello", writer);
			gson.toJson(123, writer);
		}
	}
}
