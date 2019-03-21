package com.zzh.rest.stream;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2019-3-19 15:07
 **/
public class GroupingByTest {
    private static List<BlogPost> posts = Arrays.asList(
            new BlogPost("News item 1", "Author 1", BlogPostType.NEWS, 15),
            new BlogPost("Tech review 1", "Author 2", BlogPostType.REVIEW, 5),
            new BlogPost("Programming guide", "Author 1", BlogPostType.GUIDE, 20),
            new BlogPost("News item 2", "Author 2", BlogPostType.NEWS, 35),
            new BlogPost("Tech review 2", "Author 1", BlogPostType.REVIEW, 15));

    public static void main(String[] args) {
        //Map<BlogPostType, List<BlogPost>> byType = posts.stream().collect(groupingBy(BlogPost::getType));
        //byType.forEach((k, v) -> printMap(k, v));

        //Map<Tuple, List<BlogPost>> byTypeAndAuthor = posts.stream().collect(groupingBy(e -> new Tuple(e.getType(), e.getAuthor())));
        //byTypeAndAuthor.forEach((k, t) -> printMap(k, t));

        //Map<BlogPostType, Set<BlogPost>> byType2Set = posts.stream().collect(groupingBy(BlogPost::getType, toSet()));
        //byType2Set.forEach((k, v) -> printMap(k, v));

        //Map<String, Map<BlogPostType, List<BlogPost>>> byAuthorAndType = posts.stream().collect(groupingBy(BlogPost::getAuthor, groupingBy(BlogPost::getType)));
        //byAuthorAndType.forEach((k, v) -> {
        //    System.out.println("key:" + k);
        //    v.forEach((k1, v1) -> printMap(k1, v1));
        //    System.out.println("~~~~~~~~~~~~~~~~~");
        //});

        //Map<BlogPostType, Double> likeAvg = posts.stream().collect(groupingBy(BlogPost::getType, averagingInt(BlogPost::getLikes)));
        //System.out.println(likeAvg);

        //Map<BlogPostType, Integer> likeSum = posts.stream().collect(groupingBy(BlogPost::getType, summingInt(BlogPost::getLikes)));
        //System.out.println(likeSum);

        //Map<BlogPostType, Optional<BlogPost>> likeMax = posts.stream().collect(groupingBy(BlogPost::getType, maxBy(comparing(BlogPost::getLikes))));
        //Map<BlogPostType, Optional<BlogPost>> likeMin = posts.stream().collect(groupingBy(BlogPost::getType, minBy(comparing(BlogPost::getLikes))));
        //System.out.println(likeMax);
        //System.out.println(likeMin);

        //Map<BlogPostType, DoubleSummaryStatistics> summarizeByType = posts.stream().collect(groupingBy(BlogPost::getType, summarizingDouble(BlogPost::getLikes)));
        //System.out.println(summarizeByType);

        //Map<BlogPostType, String> titlesByType = posts.stream().collect(groupingBy(BlogPost::getType, mapping(BlogPost::getTitle, joining(",", "标题：【", "】"))));
        //System.out.println(titlesByType);

        //EnumMap<BlogPostType, List<BlogPost>> enumMap = posts.stream().collect(groupingBy(BlogPost::getType, () -> new EnumMap<>(BlogPostType.class), toList()));
        //System.out.println(enumMap);
        //ConcurrentMap<BlogPostType, List<BlogPost>> paraByType = posts.parallelStream().collect(groupingByConcurrent(BlogPost::getType));
        //System.out.println(paraByType);

        //List<BlogPost> blogs = posts.stream().collect(collectingAndThen(toList(), ImmutableList::copyOf));
        //List<BlogPost> blogs = posts.stream().collect(collectingAndThen(toList(), Collections::unmodifiableList));
        //System.out.println(blogs.getClass());
        //System.out.println(posts.stream().collect(ImmutableList.toImmutableList()).getClass());

        //List<String> givenList = Arrays.asList("a", "b", "c", "d");
        //List<String> result = givenList.stream().collect(toImmutableList());
        //List<String> result1 = givenList.stream().collect(toImmutableList(LinkedList::new));
        //System.out.println(result.getClass());
        //System.out.println(result1.getClass());

        //Function<Integer, BlogPost[]> blogPostCreator = BlogPost[]::new;
        //BlogPost[] arrs = blogPostCreator.apply(5);
    }

    public static <T> Collector<T, List<T>, List<T>> toImmutableList() {
        return Collector.of(ArrayList::new, List::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collections::unmodifiableList);
    }

    public static <T, A extends List<T>> Collector<T, A, List<T>> toImmutableList(Supplier<A> supplier) {
        return Collector.of(supplier, List::add, (left, right) -> {
            left.addAll(right);
            return left;
        }, Collections::unmodifiableList);
    }

    public static <K, T> void printMap(K k, Collection<T> t) {
        System.out.println(k);
        t.stream().forEach(System.out::println);
    }
}
