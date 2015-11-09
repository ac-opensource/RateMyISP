package com.pd_opensource.ratemyisp;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by andrewconcepcion on 09/11/2015.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VoNanpzSFJDZcpKn9S1MWaYJYfpZQmLgFIfFtEjn", "BZb7P1mGvZ97dGEBreQ9rgwvYOraNhgixwrtZms4");
    }
}
