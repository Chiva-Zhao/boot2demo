package com.zzh.rest.stream;

import org.jooq.lambda.Seq;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2019-3-22 10:38
 **/
public class MergeStreams {
    private static Stream<Integer> stream1 = Stream.of(1, 3, 5);
    private static Stream<Integer> stream2 = Stream.of(2, 4, 6);
    private static Stream<Integer> stream3 = Stream.of(18, 15, 36);
    private static Stream<Integer> stream4 = Stream.of(99);

    public static void main(String[] args) {
        //Plan java8
        //Stream<Integer> merged = Stream.concat(stream1, stream2);
        //merged.collect(Collectors.toList()).forEach(System.out::println);

        //Stream<Integer> merged1 = Stream.concat(Stream.concat(stream1, stream2), stream3);
        //merged1.collect(Collectors.toList()).forEach(System.out::println);

        //Stream<Integer> merged2 = Stream.of(stream1, stream2, stream3, stream4).flatMap(e -> e);
        //merged2.collect(Collectors.toList()).forEach(System.out::println);

        //StreamEx
        //Stream<Integer> merged3 = StreamEx.of(stream1).append(stream2).append(stream3).append(stream4);
        //merged3.collect(Collectors.toList()).forEach(System.out::println);

        //StreamEx<Integer> merged4 = StreamEx.of(stream2).append(stream3).prepend(stream1);
        //merged4.collect(Collectors.toList()).forEach(System.out::println);

        //Seq<Integer> merged5 = Seq.ofType(stream1, Integer.class).append(stream2);
        //merged5.collect(Collectors.toList()).forEach(System.out::println);

        Seq<Integer> merged6 = Seq.ofType(stream2, Integer.class).prepend(stream1).append(stream3);
        merged6.collect(Collectors.toList()).forEach(System.out::println);
    }
}
