package com.thecookiezen.infrastructure.command;

import com.thecookiezen.bussiness.boundary.Executable;
import com.thecookiezen.bussiness.boundary.Storage;

public class FollowUser implements Executable {

    public static final String HANDLER_KEY = "follows";

    private final String userName;
    private final String followUser;

    public FollowUser(String userName, String followUser) {
        this.userName = userName;
        this.followUser = followUser;
    }

    @Override
    public void execute(Storage store) {
        store.follow(userName, followUser);
    }
}
