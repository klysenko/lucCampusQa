package calculator;
import java.text.DecimalFormat;
import java.util.List;

public class Calculator {
	private static final List<String> SUPPORTED_OPERATIONS = List.of("+", "/");

	public String calculate(int first, int second, String operation){

		if (SUPPORTED_OPERATIONS.contains(operation)) {
			double result = calculationStep(first, second, operation);

			return new DecimalFormat("#,##0.00").format(result);
		} else {
			throw new IllegalStateException(String.format("Operation '%s' is not supported", operation));
		}
	}

	public double calculationStep(int first, int second, String operation) {

		return performOperation(first, second, operation);
	}

	private double performOperation(int firstNumber, int secondNumber, String operation) {
		double result;

		switch (operation){
			case "+":
				result = plus(firstNumber, secondNumber);
				break;
			case "/":
				result = divide(firstNumber, secondNumber);
				break;
			default:
				result = 0;
				break;
		}

		return result;
	}

	private int plus(int numberOne, int numberTwo){
		return numberOne + numberTwo;
	}

	private double divide(int numberOne, double numberTwo){
		if (numberTwo == 0) {
			throw new IllegalArgumentException("I can not divide by 0.");
		}
		return numberOne / numberTwo;
	}

}

