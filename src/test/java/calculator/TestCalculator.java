package calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class TestCalculator {

	private Calculator calculator;

	@BeforeAll
	static void initBeforeAll() {
		System.out.println("Before all called");
	}

	@BeforeEach
	void init() {
		System.out.println("before each called");
		calculator = new Calculator();
	}

	@Test
	@DisplayName("test for sum")
	void testSum() {
		//GIVEN
		int first = 4;
		int second = 5;
		String operation = "+";

		String expectedResult = "9.00";

		//WHEN
		String actualResult = calculator.calculate(first, second, operation);

		//THEN
		assertEquals(expectedResult, actualResult);
		//MatcherAssert.assertThat(actualResult, Matchers.is(expectedResult));
	}

	@Test
	void testDivision() {
		//GIVEN
		int first = 1;
		int second = 6;
		String operation = "/";
		String expectedResult = "0.17";
		//WHEN
		String actualResult = calculator.calculate(first, second, operation);
		//THEN
		assertEquals(expectedResult, actualResult);
	}

@ParameterizedTest
@ValueSource(ints = {1,3,Integer.MAX_VALUE})
	void testSum_param(int first) {
		//GIVEN
		int second = 6;
		String operation = "+";
		String expectedResult = prepareExpectedResult(first, second);

		//WHEN
		String actualResult = calculator.calculate(first, second, operation);
		//THEN
		assertEquals(expectedResult, actualResult);
	}

	private String prepareExpectedResult(int first, int second) {
		double expectedResultDouble = first + second;
		String expectedResult = String.format("%1$,.2f", expectedResultDouble);
		return expectedResult;
	}

	@ParameterizedTest
	//@CsvSource(value = {"+:12.00", "/:1.00"}, delimiter = ':')
	@CsvFileSource(resources = "/testData.csv")
	void testSum_paramCsv(String operation, String expectedResult) {
		//GIVEN
		int second = 6;
		int first = 6;

		//WHEN
		String actualResult = calculator.calculate(first, second, operation);
		//THEN
		assertEquals(expectedResult, actualResult);
	}

	@ParameterizedTest
	@MethodSource("provideValidData")
	void testSum_paramMethod(int first, int second, String operation, String expectedResult) {
		//WHEN
		String actualResult = calculator.calculate(first, second, operation);
		//THEN
		assertEquals(expectedResult, actualResult);
	}

	private static Stream<Arguments> provideValidData() {
		return Stream.of(
				Arguments.of(1, 2, "+", "3.00"),
				Arguments.of(2,2,"/","1.00")
		);
	}

	@TestFactory
	Iterable<DynamicTest> dynamicTestsWithIterable() {
		return Arrays.asList(
				DynamicTest.dynamicTest("Sum test",
						() -> assertEquals("2.00", calculator.calculate(1, 1, "+"))),
				DynamicTest.dynamicTest("Divide Test",
						() -> assertEquals("0.00", calculator.calculate(0, 1, "/"))));
	}
}