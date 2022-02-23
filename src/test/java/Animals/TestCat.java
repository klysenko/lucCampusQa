package Animals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestCat {

	@Test
	public void checkCatSound() {
		//GIVEN
		Cat cat = new Cat("testCat", null, "pet");
		String expectedSound = "meow meow";
		//WHEN
		String actualSound = cat.makeSound();
		//THEN
		org.assertj.core.api.Assertions.assertThat(cat.getAge()).isNotZero();
		Assertions.assertEquals(expectedSound, actualSound,
				String.format("Expected '%s', but was '%s'", expectedSound, actualSound));
	}

	@Test
	public void checkCatEats() {
		//GIVEN
		Cat cat = new Cat("testCat", null, "pet");

		//WHEN
		//THEN
		Assertions.assertThrows(IllegalArgumentException.class, () -> cat.eat(cat));
	}

	@Test
	public void checkCatClass() throws NoSuchFieldException {
		//GIVEN
		Cat cat = new Cat("testCat", 3, "pet");

		//WHEN
		Field[] fields = cat.getClass().getFields();

		List<String> actualFiledNames = new ArrayList<>();
		for (Field field : fields) {
			actualFiledNames.add(field.getName());
		}
	}

}