package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.example.entities.abstracts.Entity;
import org.example.entities.abstracts.PostEntity;
import org.example.entities.concretes.Data;
import org.example.entities.concretes.Sms;
import org.example.entities.concretes.Voice;
import org.example.hazelcast_operations.HazelcastHelper;
import org.example.repo.RandomValues;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        while (true) {
            int requestIndex = random.nextInt(3);
            if (requestIndex == 0) {
                System.out.println("data");
                new PostEntity("data", getDataRequest());
                requestDGW("data",getDataRequest());
            } else if (requestIndex == 1) {
                System.out.println("voice");
                new PostEntity("voice", getVoiceRequest());
                requestDGW("voice",getVoiceRequest());

            } else {
                System.out.println("sms");
                new PostEntity("sms", getSmsRequest());
                requestDGW("sms",getSmsRequest());
            }
            Thread.sleep(10000);
        }
    }

    public static Data getDataRequest() {
        String msisdn = HazelcastHelper.getRandomSubscriber();
        int dataMb = RandomValues.getRandomDataUsageMB();
        int location = RandomValues.getRandomLocation();
        int rGroup = RandomValues.getRandomBNumber();
        return new Data(location, msisdn, dataMb, rGroup);
    }

    public static Sms getSmsRequest() {
        String msisdn = HazelcastHelper.getRandomSubscriber();
        int location = RandomValues.getRandomLocation();
        String bMsisdn;
        do {
            bMsisdn = HazelcastHelper.getRandomSubscriber();
        } while (msisdn.equals(bMsisdn));
        return new Sms(location, msisdn, bMsisdn);
    }

    public static Voice getVoiceRequest() {
        String msisdn = HazelcastHelper.getRandomSubscriber();
        int location = RandomValues.getRandomLocation();
        int duration = RandomValues.getRandomDuration();
        String bMsisdn;
        do {
            bMsisdn = HazelcastHelper.getRandomSubscriber();
        } while (!msisdn.equals(bMsisdn));
        return new Voice(location, msisdn, duration, bMsisdn);
    }

    public static void requestDGW(String type, Entity entity) {
        try {

            HttpResponse<String> addSubscriberResponse = Unirest.post("http://localhost:8080/api/generateTraffic")
                    .header("Content-Type", "application/json")
                    .body("{\"type\" : \"" +
                           type +
                            "\", \"attributes\" : " +
                            entity.toString() +
                            "}")
                    .asString() ;

            if (addSubscriberResponse.getStatus() != 200) {
                System.out.println(addSubscriberResponse.getStatus());
                System.out.println("Something went wrong.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}