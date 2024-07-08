package vsu.cs.kattsyn.tasks.task4;

public interface Sendable<T> {

    String getFrom();
    String getTo();
    T getContent();
}
