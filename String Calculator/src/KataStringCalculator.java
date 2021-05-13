import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KataStringCalculator {
	
	private String delimiter;
	private String numbers;
	
	private KataStringCalculator(String delimiter, String numbers){
		this.delimiter = delimiter;
		this.numbers = numbers;
	}
	
	private int sum(){
		if(getNumber().anyMatch(n -> n <0)){
			throw new IllegalArgumentException("negatives not allowed");
		}
		return getNumber().sum();
	}
	
	private IntStream getNumber(){
		return Arrays.stream(numbers.split(delimiter))
				.mapToInt(Integer::parseInt)
				.map(n -> n % 1000);
	}
	
	public static int sum(String input){
		if(input.isEmpty()){
			return 0;
		}
		return parseInput(input).sum();
	}
	
	private static KataStringCalculator parseInput(String input){
		if(input.contains("//")){
			String[] parts = input.split("\n", 2);
			return new KataStringCalculator(parseDelimiter(parts[0]), parts[1]);
		}else{
			return new KataStringCalculator(",|\n", input);
		}
	}
	
	private static String parseDelimiter(String header){
		String delimiter = header.substring(2);
		if(delimiter.startsWith("[")){
			delimiter = delimiter.substring(1, delimiter.length()-1);
		}
		return Stream.of(delimiter.split("]\\[")).map(Pattern::quote).collect(Collectors.joining("|"));
	}

}
