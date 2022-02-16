package format;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonToJava {

	public static void main(String[] args) throws IOException {
		Person person = new Person("name", "location", 34, Arrays.asList("dir1", "dir2", "dir3"));
		try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/server1.json"), StandardCharsets.UTF_8)){

			Gson gson = new GsonBuilder().create();

			Person p = gson.fromJson(reader, Person.class);
			System.out.println(p);
			System.out.println(p.getAge());


			String s = gson.toJson(p);
			System.out.println(s);

		}


	}

}
