package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Driver"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        Role result = store.findById("99");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.add(new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Driver"));
    }

    @Test
    public void whenReplaceThenRoleNameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Programmer"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Programmer"));
        store.replace("10", new Role("10", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getName(), is("Programmer"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Driver"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName(), is("Driver"));
    }
}

