package com.thecookiezen.infrastructure.command;

import com.thecookiezen.bussiness.boundary.Storage;
import org.junit.Test;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CreateNewTweetTest {

    private final Storage store = mock(Storage.class);

    @Test
    public void should_add_new_tweet_from_request_to_storage() {
        // when
        new CreateNewTweet("Alice", "Some tweet message :)").execute(store);

        // then
        verify(store, times(1)).storeTweet(anyObject());
    }
}