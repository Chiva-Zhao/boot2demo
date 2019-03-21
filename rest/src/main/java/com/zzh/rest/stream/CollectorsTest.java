package com.zzh.rest.stream;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collectors.toList;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2019-3-5 15:23
 **/
public class CollectorsTest {

    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
        List<String> result = givenList.stream().collect(toList());
        //result.forEach(System.out::println);

        //List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        //Set<String> result1 = listWithDuplicates.stream().collect(Collectors.toSet());
        //System.out.println(result1.size());

        //List<String> result2 = givenList.stream().collect(Collectors.toCollection(LinkedList::new));
        //result2.forEach(System.out::println);

        //Map<String, Integer> result3 = givenList.stream().collect(Collectors.toMap(Function.identity(), String::length));
        //result3.forEach((k, v) -> System.out.println(k + ":" + v));

        //List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        //listWithDuplicates.stream().collect(Collectors.toMap(Function.identity(), String::length));

        //Map<String, Integer> result4 = givenList.stream().collect(Collectors.toMap(Function.identity(), String::length, (item, identicalItem) -> item));
        //result4.forEach((k, v) -> System.out.println(k + ":" + v));

        //List<String> result5 = givenList.stream().collect(Collectors.collectingAndThen(toList(), ImmutableList::copyOf));

        //String result6 = givenList.stream().collect(Collectors.joining(" ", "$", "#"));
        //System.out.println(result6);

        //System.out.println(givenList.stream().collect(Collectors.counting()));
        //DoubleSummaryStatistics result7 = givenList.stream().collect(Collectors.summarizingDouble(String::length));
        //System.out.println(result7);

        //Double result8 = givenList.stream().collect(Collectors.averagingDouble(String::length));
        //System.out.println(result8);

        //Double result9 = givenList.stream().collect(Collectors.summingDouble(String::length));
        //System.out.println(result9);

        //Optional<String> result10 = givenList.stream().collect(Collectors.maxBy(Comparator.naturalOrder()));
        //System.out.println(result10.get());
        //Map<Integer, Set<String>> result11 = givenList.stream().collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        //System.out.println(result11);

        //Map<Boolean, List<String>> result12 = givenList.stream().collect(Collectors.partitioningBy(s -> s.length() > 2));
        //System.out.println(result12);
        ImmutableSet<String> result13 = givenList.stream().collect(new ImmutableSetCollector<String>());
        System.out.println(result13);
    }

    private static class ImmutableSetCollector<T> implements Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> {

        @Override
        public Supplier<ImmutableSet.Builder<T>> supplier() {
            return ImmutableSet::builder;
        }

        @Override
        public BiConsumer<ImmutableSet.Builder<T>, T> accumulator() {
            return ImmutableSet.Builder::add;
        }

        @Override
        public BinaryOperator<ImmutableSet.Builder<T>> combiner() {
            return (left, right) -> left.addAll(right.build());
        }

        @Override
        public Function<ImmutableSet.Builder<T>, ImmutableSet<T>> finisher() {
            return ImmutableSet.Builder::build;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Sets.immutableEnumSet(Characteristics.UNORDERED);
        }
    }


}


