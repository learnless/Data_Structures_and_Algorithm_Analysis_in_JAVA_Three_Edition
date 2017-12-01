package org.learnless.utils;

/**
 * Created by learnless on 17.11.28.
 * 简单泛型工具类
 */
public class MemoryCell<T> {
    private T t;

    public T read() {
        return t;
    }

    public void write(T t) {
        this.t = t;
    }
}
