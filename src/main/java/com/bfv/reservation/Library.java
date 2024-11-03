package com.bfv.reservation;

import java.util.UUID;

public class Library {
    public static final String RESERVATION = "Reservation";
    public final static String HOTEL = "Hotel";
    public final static String CAR = "Car";
    public final static String FLIGHT = "Flight";
    public final static String USER = "User";
    public final static String AIRPORT = "Airport";
    public final static String CITY = "City";

    public static String generateID() {
        return UUID.randomUUID().toString();
    }
}
