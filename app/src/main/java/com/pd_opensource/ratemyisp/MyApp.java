package com.pd_opensource.ratemyisp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.pd_opensource.ratemyisp.models.Reviews;

/**
 * Created by andrewconcepcion on 09/11/2015.
 */
public class MyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(Reviews.class);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "VoNanpzSFJDZcpKn9S1MWaYJYfpZQmLgFIfFtEjn", "BZb7P1mGvZ97dGEBreQ9rgwvYOraNhgixwrtZms4");
        ParseUser.enableAutomaticUser();
        ParseACL defaultAcl = new ParseACL();
        defaultAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultAcl, true);
    }
}
