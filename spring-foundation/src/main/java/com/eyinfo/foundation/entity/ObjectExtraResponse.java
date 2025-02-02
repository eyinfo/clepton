package com.eyinfo.foundation.entity;

public class ObjectExtraResponse<T, E> extends ObjectResponse<T> {

    private E extra;

    public E getExtra() {
        return extra;
    }

    public void setExtra(E extra) {
        this.extra = extra;
    }
}
