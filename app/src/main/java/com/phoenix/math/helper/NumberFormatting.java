package com.phoenix.math.helper;

/**
 * Created by dahlia on 8/14/16.
 */
public class NumberFormatting {
    public static String englishToArabic(String str){
        char[] arabicChars = {'٠','١','٢','٣','٤','٥','۶','٧','٨','٩'};
        StringBuilder builder = new StringBuilder();
        for(int i =0;i<str.length();i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                builder.append(arabicChars[(int)(str.charAt(i))-48]);
            }
            else
            {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }
}
