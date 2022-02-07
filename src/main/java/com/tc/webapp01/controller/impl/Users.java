package com.tc.webapp01.controller.impl;

import java.util.Objects;

public class Users {
    String login;
    String password;
    String name;

    public Users(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Users() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(login, users.login) && Objects.equals(password, users.password) && Objects.equals(name, users.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name);
    }
}
