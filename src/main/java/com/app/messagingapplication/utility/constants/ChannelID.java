package com.app.messagingapplication.utility.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChannelID {
    Web("WEB"),
    Android("ANDROID"),
    iOS("IOS");

    private final String value;

    public String getValue() {
        return value;
    }
}
