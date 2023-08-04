package org.example.entities.concretes;

import org.example.entities.abstracts.Entity;

import java.util.Date;

public class Voice implements Entity {
    private int location;
    private String msisdn;
    private int duration;
    private String bMsisdn;
    private Date date;
    public Voice(int location, String msisdn, int duration, String bMsisdn){
        this.location = location;
        this.msisdn = msisdn;
        this.duration = duration;
        this.bMsisdn = bMsisdn;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "{" +
                "\"location\" : " + location +
                ", \"msisdn\" : \"" + msisdn + '\"' +
                ", \"duration\" : " + duration +
                ", \"bMsisdn\" : \"" + bMsisdn + '\"' +
                ", \"date\" : \""+ date + '\"' +
                '}';
    }
}
