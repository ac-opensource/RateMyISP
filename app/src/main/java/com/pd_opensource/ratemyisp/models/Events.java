package com.pd_opensource.ratemyisp.models;

/**
 * Created by andrewconcepcion on 09/11/2015.
 */
public class Events {

    public static final String EVENT_ISP_SELECTED = "EVENT_ISP_SELECTED";
    public static final String EVENT_REVIEW_SUBMITTED = "EVENT_REVIEW_SUBMITTED";

    public String eventName;
    public String eventDescription;

    public Events(String eventName, String eventDescription) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }


}
