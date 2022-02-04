package Animals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestCat {

	@Test
	public void checkCatSound() {
		//GIVEN
		Cat cat = new Cat("testCat", 3, "pet");
		String expectedSound = "meow meow";
		//WHEN
		String actualSound = cat.makeSound();
		//THEN
		Assertions.assertEquals(expectedSound, actualSound,
				String.format("Expected '%s', but was '%s'", expectedSound, actualSound));
	}

	@Test
	public void checkCatClass() {
		//GIVEN
		Cat cat = new Cat("testCat", 3, "pet");
		String expectedSimpleName = "Cat";

		//WHEN
		String actualSimpleName = cat.getClass().getSimpleName();

		//THEN
		Assertions.assertEquals(expectedSimpleName, actualSimpleName);
	}

}