package org.example.entities.concretes;


import org.example.entities.abstracts.Entity;

import java.util.Date;

public class Sms implements Entity {
    private final int location;
    private final String msisdn;
    private final String bMsisdn;
    private final Date date;

    public Sms(int location, String msisdn, String bMsisdn) {
        this.location = location;
        this.msisdn = msisdn;
        this.bMsisdn = bMsisdn;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "{" +
                "\"location\" : " + location +
                ", \"msisdn\" : \"" + msisdn + '\"' +
                ", \"bMsisdn\" : \"" + bMsisdn + '\"' +
                ", \"date\" : \"" + date + '\"' +
                '}';
    }
}
