package com.thecookiezen.infrastructure.printer;

import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.entity.Tweet;

import java.util.Comparator;
import java.util.List;

public class SystemOutPrinter implements Printer {

    @Override
    public void print(List<Tweet> tweets) {
        tweets.stream()
                .sorted(Comparator.comparing(Tweet::getCreatedAt).reversed())
                .forEach(System.out::println);
    }

    @Override
    public void printMultipleUsersTweets(List<Tweet> tweets) {
        tweets.stream()
                .sorted(Comparator.comparing(Tweet::getCreatedAt).reversed())
                .forEach(tweet -> System.out.println(tweet.getUserName() + " - " + tweet));
    }
}
