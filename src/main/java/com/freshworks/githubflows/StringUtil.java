package com.freshworks.githubflows;

public class StringUtil {

    public boolean isEmpty(String s) {
        if(s == null || s.isEmpty())
            return true;
        else
            return false;
    }

}
