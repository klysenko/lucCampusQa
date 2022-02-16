package format;

import java.util.List;

public class Person {

	private String NAME;
	private String LOCATION;
	private int age;
	private List<String> directions;

	public Person(String NAME, String LOCATION) {
		this.NAME = NAME;
		this.LOCATION = LOCATION;
	}

	public Person(String NAME, String LOCATION, int age, List<String> directions) {
		this.NAME = NAME;
		this.LOCATION = LOCATION;
		this.age = age;
		this.directions = directions;
	}

	@Override
	public String toString() {
		return NAME + " - " + LOCATION;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getLOCATION() {
		return LOCATION;
	}

	public void setLOCATION(String LOCATION) {
		this.LOCATION = LOCATION;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getDirections() {
		return directions;
	}

	public void setDirections(List<String> directions) {
		this.directions = directions;
	}
}
