package com.ide.automation.utils;

import java.util.Random;

public class Utils {

    public final static String CONTROL_CHARACTERS_REGEX="[^\\p{ASCII}\\p{C}]|[^\\x00-\\x7F]";


    public String nameImagen(){
        char [] chars="0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
        int charsLenght = chars.length;
        Random random = new Random();

        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<10;i++){
            buffer.append(chars[random.nextInt(charsLenght)]);
        }
        return buffer.toString();
    }
}
