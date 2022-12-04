package com.example.sociopinia.util;

public interface EventCallback<T> {
    void onResult(T result);
    default void onError(Throwable t) {
    }
}
