package format;

import java.io.File;
import java.io.IOException;

import java.util.Collections;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ExampleXml {

	public static void main(String[] args) throws IOException {

		Person person = new Person("name", "location", 34, Collections.EMPTY_LIST);

		XmlMapper xmlMapper = new XmlMapper();
		//xmlMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

		String xml = xmlMapper.writeValueAsString(person);

		System.out.println("xml generated: " + xml);
		System.out.println("person original: " + person);

		xmlMapper.writeValue(new File("simple_person.xml"), person);
	}
}
