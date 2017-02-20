package com.thecookiezen.infrastructure.handlers;

import com.google.common.collect.Lists;
import com.thecookiezen.bussiness.boundary.TweetsStore;
import com.thecookiezen.bussiness.control.Query;
import com.thecookiezen.bussiness.entity.Tweet;
import com.thecookiezen.infrastructure.printer.SystemOutPrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetUserTweetsTest {

    private final TweetsStore store = mock(TweetsStore.class);

    private final GetUserTweets sut = new GetUserTweets(store, list -> new SystemOutPrinter().print(list));

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void should_print_users_tweets() {
        // given
        when(store.getTweetsByUserName("Bob")).thenReturn(Lists.newArrayList(
                new Tweet("Bob", "It's my first tweet"),
                new Tweet("Bob", "I love the weather today"),
                new Tweet("Bob", "Good game though.")
        ));

        // when
        sut.execute(new Query("Bob", ""));

        // then
        assertThat(outContent.toString()).contains("It's my first tweet (0 seconds ago)",
                "I love the weather today (0 seconds ago)", "Good game though. (0 seconds ago)");
    }
}