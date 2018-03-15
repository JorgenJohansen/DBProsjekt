package com.group23.program;

public class Util {

    /**
     * Strips a trailing comma from string
     * @param String where we want to remove a comma
     * @return String where trailing comma is removed
     */
    public static String stripTrailingComma(String s) {
        if(s.endsWith(",")) {
            return  s.substring(0,s.length() - 1);
        } else {
            return s;
        }
    }
}
