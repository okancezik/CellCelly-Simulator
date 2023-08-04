package org.example.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomValues {
    private static  Random random = new Random();
    private static List<Integer> locations = new ArrayList<>(Arrays.asList(90,49));


    public static int getRandomDataUsageMB(){
        return random.nextInt(750);
    }

    public static int getRandomDuration(){
        int duration = random.nextInt(350);
        return duration;
    }

    public static int getRandomLocation(){
        int index = random.nextInt(2);
        return locations.get(index);
    }

    public static int getRandomBNumber(){
        return random.nextInt(16);
    }

}
