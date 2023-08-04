package org.example.entities.concretes;

import org.example.entities.abstracts.Entity;

import java.util.Date;

public class Data implements Entity {
    private int location;
    private String msisdn;
    private int dataUsage;
    private int rGroup;
    private Date date;

    public Data(int location, String  msisdn,int dataUsage, int rGroup){
        this.location = location;
        this.msisdn = msisdn;
        this.rGroup = rGroup;
        this.dataUsage = dataUsage;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "{" +
                "\"location\" : " + location +
                ", \"msisdn\" : \"" + msisdn + '\"' +
                ", \"dataUsage\" : " + dataUsage +
                ", \"rGroup\" : " + rGroup +
                ", \"date\" : \"" + date + '\"' +
                '}';
    }
}
