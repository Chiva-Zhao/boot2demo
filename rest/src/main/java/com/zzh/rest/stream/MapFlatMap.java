package com.zzh.rest.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2019-3-22 14:35
 **/
public class MapFlatMap {

    public static void main(String[] args) {
        Optional<String> s = Optional.of("hello");
        System.out.println(s.map(String::toUpperCase));

        Optional<Optional<String>> s1 = Optional.of(Optional.of("world"));
        Optional<Optional<String>> s2 = Optional.of("world").map(Optional::of);
        System.out.println(s1);
        System.out.println(s2);

        Optional<String> s3 = Optional.of("foo").flatMap(o -> Optional.of("FOO"));

        List<String> list = Stream.of("a", "b").map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(list);
        List<List<String>> listList = Arrays.asList(
                Arrays.asList("a"),
                Arrays.asList("b"));
        System.out.println(listList);

        System.out.println(listList.stream().flatMap(Collection::stream).collect(Collectors.toList()));

        String s4 = "hello,world,nihao,nizaigan,shenme";
        List<String> list2 = Stream.of(s4.split(",")).collect(Collectors.toList());
        System.out.println(list2);
        List<Character> chars = s4.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        System.out.println(chars);

        String[] mapStr = {"name:赵振华", "age:22", "job:Java Architecture"};
        Map<String, String> rst = Stream.of(mapStr).map(ele -> ele.split(":")).collect
                (Collectors.toMap(str -> str[0], str -> str[1]));
        System.out.println(rst);
    }
}
