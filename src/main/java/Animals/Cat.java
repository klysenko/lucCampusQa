package Animals;

public class Cat extends Animal{
public String weight;
public String category;
	public Cat(String name, Integer age, String type) {
		super(name, age, type);
	}

	@Override
	public String makeSound() {
		return "meow meow";
	}

	@Override
	public String eat(Animal animal) {
		String eatingMessage;

		if (animal.getAge() == null) {
			throw new NumberFormatException();
		}

		if (animal.getAge() < 1) {
			eatingMessage = String.format("I am young cat '%s' and I am eating", animal);
		} else {

		eatingMessage = String.format("I am adult cat '%s' and I am eating", animal);
		}

		return eatingMessage;
	}
}
