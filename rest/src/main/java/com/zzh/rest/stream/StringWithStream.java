package com.zzh.rest.stream;

import io.vavr.collection.Stream;
import one.util.streamex.EntryStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2019-3-22 16:44
 **/
public class StringWithStream {

    public static void main(String[] args) {
        String[] names = {"张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十"};
        List<String> evenNames = IntStream.range(0, names.length).filter(i -> i % 2 == 0).mapToObj(i -> names[i]).collect(Collectors.toList());
        System.out.println(evenNames);

        List<String> evenNames1 = EntryStream.of(names).filterKeyValue((index, name) -> index % 2 == 0).values().toList();
        System.out.println(evenNames1);

        List<String> evenNames2 = Stream.of(names).zipWithIndex().filter(tuple -> tuple._2 % 2 == 0).map(e -> e._1).toJavaList();
        System.out.println(evenNames2);
    }
}
