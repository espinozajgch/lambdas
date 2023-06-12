package com.jgx.lamdas;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootApplication
public class LamdasApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		//SpringApplication.run(LamdasApplication.class, args);

		//Function -> recibe parametros y retorna algo
		//Consumer -> recibe parametros y no retorna nada
		//Supplier -> no recibe nada y retorna un objeto
		//Predicate -> recibe parametros y retorna true o false

		funcionesAnonimas();
		StringFunctions();
		SAM();
		operadorReferencia();
		lambdas();

		System.out.println("------------------------------------------------");
		usarStringOperation();
		System.out.println("------------------------------------------------");
		Chaining();
		System.out.println("------------------------------------------------");
		Compose();
		System.out.println("------------------------------------------------");
		stream();
		System.out.println("------------------------------------------------");
		intStream();
		System.out.println("------------------------------------------------");
		stairCase();
		System.out.println("------------------------------------------------");
		collector();
		System.out.println("------------------------------------------------");
		int[] arr = {-1,-2,0, 1,1,1, 2, 3, 4, 5 };
		solution(arr);
		System.out.println("------------------------------------------------");
		new Thread(() -> System.out.println("Java lambdas")).start();

	}

	static void funcionesAnonimas(){

		/*
		Function<Integer, Integer> cuadrado = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer integer) {
				return integer * integer;
			}
		};/**/

		Function<Integer, Integer> cuadrado = integer -> integer * integer;
		System.out.println("Function: " + cuadrado.apply(2));

		Function<Integer, Boolean> isOdd = x -> x % 2 == 1;
		System.out.println("Function: " + isOdd.apply(2));

		/*
		Consumer<Integer> consumer = new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println("Hola");
			}
		};
		/**/
		Consumer<Integer> consumer = integer -> System.out.println("Consumer: " + integer);
		consumer.accept(1);

		/*
		Supplier<Integer> supplier = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return 1;
			}
		};
		/**/

		Supplier<Integer> supplier = () -> 1;
		System.out.println("Supplier: " + supplier.get());

		Predicate<Integer> isEven = x -> x % 2 == 0 ;
		System.out.println("Predicate: " + isEven.test(1));

		System.out.println("------------------------------------------------");
	}

	static void StringFunctions(){
		UnaryOperator<String> quote = text -> "!" + text + "!";
		System.out.println("UnaryOperator: " + quote.apply("Hola Mundo"));

		BiFunction<Integer, Integer, Integer> multiplicacion = (x,y) -> x * y;
		System.out.println("BiFunction: " + multiplicacion.apply(4,2));

		BiFunction<String, Integer, String> leftPad = (text, number) -> String.format("%" + number + "s", text);
		System.out.println("BiFunction: " + leftPad.apply("Java", 10));

		System.out.println("------------------------------------------------");

	}

	static void SAM(){

		Function<Integer, String> addCeros = x -> x < 10? "0" + x : String.valueOf(x);
		System.out.println("Function: " + addCeros.apply(1));

		AgeUtils.TriFunction<String, String, String, LocalDate> parseDate =
				(day, month, year) -> LocalDate.parse(year+"-"+month+"-"+day);
		System.out.println("TriFunction: " + parseDate.apply("27","06","1989") );

		AgeUtils.TriFunction<String, String, String, Integer> calculateAge =
				(day, month, year) -> Period.between(
						parseDate.apply(day,month,year),LocalDate.now()
						).getYears();

		System.out.println("TriFunction: " + calculateAge.apply("27","06","1989"));

		System.out.println("------------------------------------------------");
		/**/
	}

	static <T> List<T> getList(T... elements){
		return Arrays.asList(elements);
	}

	static void operadorReferencia(){
		List<String> profesores = getList("1","2","3");
		profesores.forEach(name -> System.out.println(name));
		profesores.forEach(System.out::println);
	}

	static void lambdas(){

		usarZeroArguments(() -> 2);
		usarPredicado(text -> text.isEmpty());
		usarBifuction((x,y) -> x+y);
		usarBifuction((x,y) ->{
			System.out.println("pruebas");
			return x * y;
		});


		usarNada(() -> {});
	}

	static void usarZeroArguments(AgeUtils.ZeroArgumentos zeroArgumentos){

	}

	static void usarPredicado(Predicate<String> resultado){

	}

	static void usarBifuction(BiFunction<Integer, Integer, Integer> operacion){

	}

	static void usarNada(AgeUtils.nada nada){

	}

	static void usarStringOperation(){
		AgeUtils.StringOperation six = () -> 6;
		six.operate("Hola");
	}

	static void Chaining(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("a").append("b");

		Chainer chainer = new Chainer();
		chainer.sayHi().sayBye();
	}

	static void Compose(){

		Function<Integer,Integer> sum = (x) -> {
			System.out.println("deberia Sumar");
			return x + 1 ;
		};

		System.out.println("Compose Function 1: " + sum.apply(1));

		Function<Integer, Integer> restar =
			sum.compose(y -> {
					System.out.println("deberia Sumar y Restar");
					return y - 1;
			});

		System.out.println("Compose Function 2: " + restar.apply(1));

		Function<Integer, Integer> multiplicar =
				sum.andThen(z -> {
					System.out.println("deberia Multiplicar");
					return z * 1;
				});

		System.out.println("Compose Function 2: " + multiplicar.apply(1));

	}

	static void stream(){
		Stream<String> coursesStream = Stream.of("Java", "FrontEnd", "BackEnd");
		//coursesStream.forEach(System.out::println);

		/*
		coursesStream.map(y -> y + "!").filter(x -> x.contains("Java")).forEach(System.out::println);

		Stream<Integer> coursesStreamLength = coursesStream.map(x -> x.length());
		coursesStreamLength.forEach(System.out::println);

		Optional<Integer> longest = coursesStreamLength.max((x, y) -> y - x);

		Stream<String> courses = coursesStream.map(y -> y + "!");
		courses.forEach(System.out::println);

		Stream<String> justJavaCourses = coursesStream.filter(x -> x.contains("Java"));
		justJavaCourses.forEach(System.out::println);
		/* */



	}

	static void intStream(){
		IntStream infineteStream = IntStream.iterate(0, x -> x+1);

		infineteStream.limit(10)
				.parallel()
				.filter(x -> x % 2 == 0)
				.forEach(System.out::println);
	}

	static void collector(){
		IntStream infineteStream = IntStream.iterate(0, x -> x-1);
		List<Integer> integers = infineteStream.limit(10)
				.parallel()
				.filter(x -> x % 2 == 0)
				.peek(System.out::println)
				.boxed()
				.collect(Collectors.toList());
	}

	static void stairCase(){
		final int tam = 6;
		Stream<String> stairStream = Stream.iterate(String.format("%" + tam + "s", "#"), stair -> stair+"#");

		stairStream.limit(tam)
				.map(stair -> stair.substring((stair.length()-tam)))
				.forEach(System.out::println);

		//String.format("%" + number + "s", text);
	}

	static void solution(int[] A) {
		// write your code in Java SE 8
		List<Integer> list = Arrays.stream(A)
				.filter(e -> e > 0)
				.boxed()
				.distinct()
				.sorted()
				.peek(System.out::println)
				.collect(Collectors.toList());



		int notin = 0;
		for (int i = 0; i < list.size(); i++) {
			if((i+1) != list.get(i)) {
				notin = i+1;
				break;
			}
			else{
				notin = i+2;
			}
		}

		System.out.println("notin: " + notin);


	}


}






