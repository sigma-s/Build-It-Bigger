package com.example.Goodbox.myapplication.backend;

import com.example.MyJokes;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private MyJokes myJokes;

    public MyBean(){
        myJokes = new MyJokes();
    }

    public String getJokes(){
        return myJokes.getJoke();
    }
}