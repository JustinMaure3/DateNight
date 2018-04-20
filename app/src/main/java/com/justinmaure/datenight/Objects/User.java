package com.justinmaure.datenight.Objects;

import java.util.ArrayList;

/**
 * Created by IceOf on 2018-03-27.
 */

public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Boolean isLoggedIn = false;

    private ArrayList<Date> favoritedDates = new ArrayList<Date>();

    public User(){

    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLoggedInStatus() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public ArrayList<Date> getFavoritedDates(){
        return this.favoritedDates;
    }

    public void addToFavorites(Date date) {
        this.favoritedDates.add(date);
    }

    public void removeFromFavorites(Date date) {
        this.favoritedDates.remove(date);
    }
}
