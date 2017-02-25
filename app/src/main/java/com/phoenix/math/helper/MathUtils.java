package com.phoenix.math.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dahlia on 2/24/17.
 */
public class MathUtils {
    public static int rand(int min, int max){
        return min + (int)Math.round(Math.random() * max);
    }


    public static <T> List<T> shuffleArray(T[] ar){
        List<T> myArray = new ArrayList<T>(Arrays.asList(ar));
        Collections.shuffle(myArray);
        return myArray;
    }

}
