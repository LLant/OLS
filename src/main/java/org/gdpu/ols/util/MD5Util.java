package org.gdpu.ols.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static final String[] hexDigits={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};

    private static String bypeToHexString(byte b){
        int n=b;
        if(n<0)
            n+=256;
        int d1=n/16;
        int d2=n%16;

        return hexDigits[d1]+hexDigits[d2];
    }

    private static String bypeArrayToHexString(byte[] bytes){
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i <bytes.length ; i++) {
            sb.append(bypeToHexString(bytes[i]));
        }

        return sb.toString();
    }

    public static String MD5Encode(String origin,String charsetName){
        String resultString=new String(origin);

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            if(charsetName==null || "".equals(charsetName)){
                resultString=bypeArrayToHexString(md.digest(origin.getBytes()));
            }else{
                resultString=bypeArrayToHexString(md.digest(origin.getBytes(charsetName)));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally {
            return resultString;
        }
    }
}
