package com.example.trikh.myapplication.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String mJoke;

    public String getData() {
        return mJoke;
    }

    public void setData(String joke) {
        mJoke = joke;
    }
}