package ru.job4j.map;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleMapTest {

    @Test
    public void whenPutAndGet() {
        SimpleMap<String, String> table = new  SimpleMap<>();
        table.put("Sobolev", "Maxim");
        assertThat(table.get("Sobolev"), is("Maxim"));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, String> table = new SimpleMap<>();
        table.put("Sb", "Mx");
        table.remove("Sb");
        assertNull(table.get("Sb"));
    }

    @Test
    public void whenCantRemove() {
        SimpleMap<String, String> table = new SimpleMap<>();
        table.put("Sb", "Mx");
        assertFalse(table.remove("SS"));
    }

    @Test
    public void whenNotGet() {
        SimpleMap<String, String> table = new  SimpleMap<>();
        table.put("Ivanov", "Maxim");
        assertNull(table.get("Sobolev"));

    }
}