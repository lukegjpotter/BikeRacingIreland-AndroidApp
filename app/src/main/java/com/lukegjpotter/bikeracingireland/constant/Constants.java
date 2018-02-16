package com.lukegjpotter.bikeracingireland.constant;


public class Constants {
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String UI_DATE_FORMAT = "dd/MM/yy";
    public static final String REST_BASE_URL_LOCAL = "http://localhost:8080/";
    public static final String REST_BASE_URL_REMOTE = "http://cyclingirelandevents.herokuapp.com/";

    /**
     * Preventing Instancing with a Private Constructor.
     */
    private Constants() {
        throw new AssertionError("Class should not be instantiated");
    }
}
