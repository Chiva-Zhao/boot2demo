package com.zzh.rest.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2019-3-21 19:43
 **/
public class FilterOptionalStream {
    private static List<Optional<String>> optionals = Arrays.asList(Optional.empty(), Optional.of("张三"), Optional.empty(), Optional.of("李四"));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<String> result = optionals.stream().filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        System.out.println(result);

        List<String> result1 = optionals.stream().flatMap(o -> !o.isPresent() ? Stream.empty() : Stream.of(o.get())).collect(Collectors.toList());
        System.out.println(result1);

        List<String> result2 = optionals.stream().flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty)).collect(Collectors.toList());
        System.out.println(result2);

        long firstNum = 1;
        long lastNum = 1_000_000;

        List<Long> aList = LongStream.rangeClosed(firstNum, lastNum).boxed()
                .collect(Collectors.toList());

        ForkJoinPool customThreadPool = new ForkJoinPool(4);
        long actualTotal = customThreadPool.submit(
                () -> aList.parallelStream().reduce(0L, Long::sum)).get();

        System.out.println((lastNum + firstNum) * lastNum / 2);
        System.out.println(actualTotal);
    }
}
