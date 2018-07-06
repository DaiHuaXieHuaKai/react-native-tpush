package com.witty.tpushlibrary;

/**
 * Created by asus on 2018-07-06.
 */

public class TPushUtils {

    public static boolean isEmpty(String s){
        if(s==null){
            return true;
        }
        if(s.length()==0){
            return true;
        }
        if(s.trim().length()==0){
            return true;
        }
        return false;
    }
}
