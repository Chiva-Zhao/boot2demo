package com.zzh.rest.stream;

import java.io.IOException;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-11-30 15:46
 **/
public class DebugTest {
    public static void main(String[] args) throws IOException {
        //Stream<String> str = Stream.generate(() -> "ele").limit(10);
        //str.forEach(System.out::println);
        //Stream intStr = Stream.iterate(10, n -> n + 2).limit(20);
        //intStr.forEach(System.out::println);
        //Random random = new Random();
        //DoubleStream doubleStream = random.doubles(3);
        //doubleStream.forEach(System.out::println);
        //IntStream streamOfChars = "abc".chars();
        //Path path = Paths.get("C:\\file.txt");
        //Stream<String> streamOfStrings = Files.lines(path);
        //Stream<String> streamWithCharset = Files.lines(path, Charset.forName("UTF-8"));
        //List<String> elements =
        //        Stream.of("a", "b", "c").filter(element -> element.contains("b"))
        //                .collect(Collectors.toList());
        //Optional<String> anyElement = elements.stream().findAny();
        //Optional<String> firstElement = elements.stream().findFirst();

        //List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        //long size = list.stream().skip(1)
        //        .map(element -> element.substring(0, 3)).sorted().count();
        //System.out.println(size);

        //List<String> list = Arrays.asList("abc1", "abc2", "abc3");
        ////int counter = 0;
        //Stream<String> stream = list.stream().filter(element -> {
        //    wasCalled();
        //    return element.contains("2");
        //});

        //Optional<String> stream1 = list.stream().filter(element -> {
        //    System.out.println("filter() was called");
        //    return element.contains("2");
        //}).map(element -> {
        //    System.out.println("map() was called");
        //    return element.toUpperCase();
        //}).findFirst();

        OptionalInt reduced = IntStream.range(1, 4).reduce((a, b) -> a + b);
        int reducedTwoParams = IntStream.range(1, 4).reduce(10, (a, b) -> a + b);
        //int reducedParams = Stream.of(1,2,3).reduce(10,(a,b)->a+b,(a,b)->{
        //    System.out.println("combiner was called");
        //    return a+b;
        //});
        int reducedParallel = Arrays.asList(1, 2, 3).parallelStream().reduce(10, (a, b) -> a + b, (a, b) -> {
            System.out.println("combiner was called");
            return a + b;
        });
        System.out.println(reducedParallel);
    }

    private static long counter;

    private static void wasCalled() {
        counter = counter++;
    }
}
//