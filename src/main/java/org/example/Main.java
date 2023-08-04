package org.example;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import netscape.javascript.JSObject;
import org.example.entities.abstracts.Entity;
import org.example.entities.abstracts.PostEntity;
import org.example.entities.concretes.Data;
import org.example.entities.concretes.Sms;
import org.example.entities.concretes.Voice;
import org.example.hazelcast_operations.HazelcastHelper;
import org.example.repo.RandomValues;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int requestIndex = random.nextInt(3);
        if (requestIndex == 0) {
            System.out.println("data");
            PostEntity entity = new PostEntity("data", getDataRequest());
            requestDGW("data",getDataRequest());
        } else if (requestIndex == 1) {
            System.out.println("voice");
            PostEntity entity = new PostEntity("voice", getVoiceRequest());
            requestDGW("voice",getVoiceRequest());

        } else {
            System.out.println("sms");
            PostEntity entity = new PostEntity("sms", getSmsRequest());
            requestDGW("sms",getSmsRequest());
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
        while (true) {
            bMsisdn = HazelcastHelper.getRandomSubscriber();
            if (!msisdn.equals(bMsisdn)) {
                break;
            }
        }
        return new Sms(location, msisdn, bMsisdn);
    }

    public static Voice getVoiceRequest() {
        String msisdn = HazelcastHelper.getRandomSubscriber();
        int location = RandomValues.getRandomLocation();
        int duration = RandomValues.getRandomDuration();
        String bMsisdn;
        while (true) {
            bMsisdn = HazelcastHelper.getRandomSubscriber();
            if (msisdn.equals(bMsisdn)) {
                break;
            }
        }
        return new Voice(location, msisdn, duration, bMsisdn);
    }

    public static void requestDGW(String type, Entity entity) {
        try {
            // Create a JSONObject object
            //JSONObject jsonObject = new JSONObject();
            //jsonObject.put("type", type);
            //jsonObject.put("attributes", new JSONObject().put("city", "New York").put("state", "NY"));

            HttpResponse<JsonNode> addSubscriberResponse = Unirest.post("http://34.105.150.121:8080/api/generateTraffic")
                    .header("Content-Type", "application/json")
                    .body("{\"type\" : \"" +
                           type +
                            "\", \"attributes\" : \"" +
                            entity.toString() +
                            "\"}")
                    //.body(jsonObject)
                    .asJson() ;
            /*
            HttpResponse response = Unirest.post("http://34.105.150.121:8080/api/generateTraffic")
                    .header("Content-Type", "application/json")
                    .body(jsonObject)
                    .asString();*/
                    //.body(object).asJson();
                    //.body(jsonArray).asJson();

            if (addSubscriberResponse.getStatus() != 200) {
                System.out.println(addSubscriberResponse.getStatus());
                System.out.println("Something went wrong.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}