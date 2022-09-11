package utilities;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomGenerator {
    /*THIS WILL GENERATE RANDOM INTEGER  BETWEEN SPECIFIED RANGE
     *
     */
    public static int randomNumberInGivenRange(int number){
        Random r = new Random();
        int RandomNumber=r.nextInt(number);
        return RandomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     *
     */
    public static String randomAlphabet(int minRange, int maxRange){
        String randomAlphabet= RandomStringUtils.randomAlphabetic(minRange,maxRange);
        return randomAlphabet;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     *
     */
    public static String randomAlphabet(int range){
        String randomAlphabet=RandomStringUtils.randomAlphabetic(range);
        return randomAlphabet;
    }


    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     *
     */
    public static String randomNumber(int range){
        String randomNumber=RandomStringUtils.random(range);
        return randomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     * AND CHARACTERS
     */
    public static String randomNumber(int range, String characters){
        String randomNumber=RandomStringUtils.random(range,characters);
        return randomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     * AND BOOLEAN VALUE
     */
    public static String randomNumber(int range,int start, int end,boolean letters,boolean number){
        String randomNumber=RandomStringUtils.random(range,start,end,letters,number);
        return randomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     *
     */
    public static String randomAlphaNumeric(int range){
        String randomNumber=RandomStringUtils.randomAlphanumeric(range);
        return randomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED MINIMUN RANGE AND MAXIMUM RANGE
     * IT WILL CONTAIN ONLY CHARACTER
     */
    public static String randomAlphaNumeric(int minRange, int maxRange){
        String randomNumber=RandomStringUtils.randomAlphanumeric(minRange,maxRange);
        return randomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED MINIMUN RANGE AND MAXIMUM RANGE
     * IT WILL CONTAIN ONLY NUMBER
     */
    public static String randomNumeric(int minRange, int maxRange){
        String randomNumber=RandomStringUtils.randomNumeric(minRange,maxRange);
        return randomNumber;
    }

    /*THIS WILL GENERATE RANDOM STRING  BETWEEN SPECIFIED RANGE
     * IT WILL CONTAIN ONLY NUMBER
     */
    public static String randomNumeric(int range){
        String randomNumber=RandomStringUtils.randomNumeric(range);
        return randomNumber;
    }

    public static String randomAllowedChars(int length, String allowedChar) {
        return RandomStringUtils.random(length, allowedChar);
    }

    public static String randomEmail(int length) {
        String allowedChars="abcdefghijklmnopqrstuvwxyz"+"1234567890";
        String email="";
        String temp=RandomStringUtils.random(length, allowedChars);
        email=temp.substring(0, temp.length()-3)+"@test.org";
        return email;
    }

    public static String randomUrl(int length) {
        String allowedChars="abcdefghijklmnopqrstuvwxyz"+"1234567890";
        String url="";
        String temp=RandomStringUtils.random(length, allowedChars);
        url=temp.substring(0, 3)+"."+temp.substring(4, temp.length()-3)+"."+temp.substring(temp.length()-2);
        return url;
    }
}
