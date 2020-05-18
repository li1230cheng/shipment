package com.infosys.util;

import java.util.Arrays;

public class VerifyUtil {

    public static void isNotNull(String msg,Object... os) {
        Arrays.stream(os).forEach(o -> {
            if(o==null){
                throw new RuntimeException(msg);
            }
        });

    }

    public static void verify(String msg,boolean flag) {
        if(!flag){
            throw new RuntimeException(msg);
        }

    }
}
