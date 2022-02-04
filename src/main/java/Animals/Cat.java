package Animals;

public class Cat extends Animal{

	public Cat(String name, int age, String type) {
		super(name, age, type);
	}

	@Override
	public String makeSound() {
		return "meow meow";
	}

	@Override
	public String eat(Animal animal) {
		String eatingMessage = String.format("I am cat '%s' and I am eating", animal);
		return eatingMessage;
	}
}
