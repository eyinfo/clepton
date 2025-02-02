package com.eyinfo.foundation.events;

public interface Action<T> {
    public void call(T t1);
}
