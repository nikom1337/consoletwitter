package com.thecookiezen.infrastructure;

import com.thecookiezen.bussiness.control.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestFactory {

    private static final String USER_NAME = "userName";
    private static final String COMMAND = "command";
    private static final String PARAMETER = "parameter";
    private static final Pattern REQUEST_PATTERN = Pattern.compile("^(?<" + USER_NAME + ">\\w+)\\s?(?<" + COMMAND +
            ">\\S*)\\s?(?<" + PARAMETER + ">.*)$");

    public Request createRequestFromString(String requestStringify) {
        final Matcher requestMatcher = REQUEST_PATTERN.matcher(requestStringify);
        if (requestMatcher.find()) {
            return new Request(requestMatcher.group(USER_NAME), requestMatcher.group(COMMAND), requestMatcher.group(PARAMETER));
        }
        return Request.EMPTY_REQUEST;
    }
}
