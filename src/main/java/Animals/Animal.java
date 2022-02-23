package Animals;

public abstract class Animal {
	private String name;
	private Integer age;
	private String type;

	public Animal(String name, Integer age, String type) {
		this.name = name;
		this.age = age;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Animal animal = (Animal) o;

		if (age != animal.age) {
			return false;
		}
		if (!name.equals(animal.name)) {
			return false;
		}
		return type.equals(animal.type);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + age;
		result = 31 * result + type.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Animal{" +
				"name='" + name + '\'' +
				", age=" + age +
				", type='" + type + '\'' +
				'}';
	}

	public abstract String makeSound();

	public abstract String eat(Animal animal);

}
