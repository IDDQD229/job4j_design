package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> l = list.listIterator(index);
        l.add(value);

    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> l = list.listIterator(index + 1);
        l.add(value);
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> l = list.listIterator();
        while (l.hasNext()) {
            if (filter.test(l.next())) {
                l.remove();
            }
        }
    }


    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> l = list.listIterator();
        while (l.hasNext()) {
            if (filter.test(l.next())) {
                l.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> l = list.listIterator();
        while (l.hasNext()) {
            T o = l.next();
            if (elements.contains(o)) {
                l.remove();
            }
        }
    }
}
