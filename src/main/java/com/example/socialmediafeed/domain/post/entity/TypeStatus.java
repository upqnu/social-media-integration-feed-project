package com.example.socialmediafeed.domain.post.entity;

public enum TypeStatus {
    FACEBOOK("facebook"),
    TWITTER("twitter"),
    INSTAGRAM("instagram"),
    THREADS("threads");

    private final String status;

    TypeStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
