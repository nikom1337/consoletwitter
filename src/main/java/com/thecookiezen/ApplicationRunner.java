package com.thecookiezen;

import com.google.common.collect.ImmutableMap;
import com.thecookiezen.bussiness.boundary.Printer;
import com.thecookiezen.bussiness.boundary.RequestFactory;
import com.thecookiezen.bussiness.boundary.RequestHandler;
import com.thecookiezen.bussiness.boundary.TweetsStore;
import com.thecookiezen.bussiness.control.Request;
import com.thecookiezen.infrastructure.handlers.CreateNewTweet;
import com.thecookiezen.infrastructure.handlers.FollowUser;
import com.thecookiezen.infrastructure.handlers.GetUserTweets;
import com.thecookiezen.infrastructure.handlers.GetUserWall;
import com.thecookiezen.infrastructure.printer.SystemOutPrinter;
import com.thecookiezen.infrastructure.store.InMemoryTweets;

import java.util.Map;
import java.util.Scanner;

public class ApplicationRunner {

    private static final TweetsStore store = new InMemoryTweets();

    private static final Printer printer = new SystemOutPrinter();

    private static final Map<String, RequestHandler> requestHandlers = ImmutableMap.<String, RequestHandler>builder()
            .put(CreateNewTweet.HANDLER_KEY, new CreateNewTweet(store))
            .put(GetUserTweets.HANDLER_KEY, new GetUserTweets(store, printer::print))
            .put(FollowUser.HANDLER_KEY, new FollowUser(store))
            .put(GetUserWall.HANDLER_KEY, new GetUserWall(store, printer::printMultipleUsersTweets))
            .build();

    private final static RequestFactory requestFactory = new RequestFactory();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            final Request requestFromString = requestFactory.createRequestFromString(scanner.nextLine());
            requestHandlers.get(requestFromString.getCommand()).execute(requestFromString);
        }
    }
}
