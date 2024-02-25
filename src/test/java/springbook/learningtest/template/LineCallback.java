package springbook.learningtest.template;

public interface LineCallback<T> {
    T doSomethigWithLine(String line, T value);
}
