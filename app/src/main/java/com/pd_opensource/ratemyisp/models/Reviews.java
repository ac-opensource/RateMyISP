package com.pd_opensource.ratemyisp.models;

import android.location.Location;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by andrewconcepcion on 09/11/2015.
 */
@ParseClassName("Reviews")
public class Reviews extends ParseObject {

    public Reviews() {
    }

    public void setISP (String isp) {
        put("isp", isp);
    }

    public void setRate (Float rate) {
        put("rate", rate);
    }

    public void setComment (String comment) {
        put("comment", comment);
    }

    public void setLocation (ParseGeoPoint location) {
        put("location", location);
    }

    public void setLocation (Location location) {
        if(location != null) {
            put("location", new ParseGeoPoint(location.getLatitude(), location.getLongitude()));
        }
    }

}
