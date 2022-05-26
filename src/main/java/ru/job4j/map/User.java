package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    @Override
    public String toString() {
        return "User{"
                +
                "name='" + name + '\''
                +
                ", children=" + children
                +
                ", birthday=" + birthday
                +
                '}';
    }

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;

    }

    public static void main(String[] args) {
        User userFirst = new User("Max", 2, new GregorianCalendar(1993, 7, 15));
        User userSecond = new User("Max", 2, new GregorianCalendar(1993, 7, 15));
        Map<User, Object> map = new HashMap<>();
        map.put(userFirst, new Object());
        map.put(userSecond, new Object());
        BiConsumer<User, Object> consumer = (o1, o2) -> System.out.println("Key: " + o1 + "Value: " + o2);
        map.forEach(consumer);
    }
}
