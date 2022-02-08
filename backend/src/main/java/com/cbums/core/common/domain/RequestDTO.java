package com.cbums.core.common.domain;

public interface RequestDTO<T> {
    public T toEntity(T t);
}
