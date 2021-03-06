package com.thecookiezen.infrastructure.command;

import com.thecookiezen.bussiness.boundary.Executable;
import com.thecookiezen.bussiness.boundary.Storage;
import com.thecookiezen.bussiness.entity.Tweet;

public class CreateNewTweet implements Executable {

    public static final String HANDLER_KEY = "->";

    private final String userName;
    private final String tweetMessage;

    public CreateNewTweet(String userName, String tweetMessage) {
        this.userName = userName;
        this.tweetMessage = tweetMessage;
    }

    @Override
    public void execute(Storage store) {
        store.storeTweet(new Tweet(userName, tweetMessage));
    }
}
