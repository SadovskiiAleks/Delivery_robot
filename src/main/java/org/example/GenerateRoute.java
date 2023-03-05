package org.example;

import java.util.Random;
import java.util.concurrent.Callable;

public class GenerateRoute implements Callable {

    @Override
    public Integer call() throws Exception {
        int result = 0;

        //Считать количесво повтора R

        String resultOfGenerate = generateRoute("RLRFR", 100);
        char[] arrayOfString = resultOfGenerate.toCharArray();
        for (char charOfString: arrayOfString) {
            if (charOfString == 'R') {
                result++;
            }
        }

        return result;
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}
