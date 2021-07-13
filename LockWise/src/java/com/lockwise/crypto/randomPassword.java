package com.lockwise.crypto;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

 
public class randomPassword {
 
    // SecureRandom() constructs a secure random number generator (RNG) implementing the default random number algorithm.
    private final SecureRandom crunchifyRandomNo = new SecureRandom();
 
    private final List<Character> crunchifyValueObj;
 
    // Just initialize ArrayList crunchifyValueObj and add ASCII Decimal Values
    public randomPassword() {
 
        crunchifyValueObj = new ArrayList<>();
 /*
        // Adding ASCII Decimal value between 33 and 53
        for (int i = 33; i < 53; i++) {
            crunchifyValueObj.add((char) i);
        }
 
        // Adding ASCII Decimal value between 54 and 85
        for (int i = 54; i < 85; i++) {
            crunchifyValueObj.add((char) i);
        }
 
        // Adding ASCII Decimal value between 86 and 128
        for (int i = 86; i < 127; i++) {
            crunchifyValueObj.add((char) i);
        }
        
        // crunchifyValueObj.add((char) 64);
 
        Collections.rotate(crunchifyValueObj, 5);
   */ }
    
    // Get Char value from above added Decimal values
    // Enable Logging below if you want to debug
    public  char crunchifyGetRandom() {
 
        char crunchifyChar = this.crunchifyValueObj.get(crunchifyRandomNo.nextInt(this.crunchifyValueObj.size()));
 
        //log(String.valueOf(crunchifyChar));
        return crunchifyChar;
    }
      
    // Strong Password Generator
    public String getpass(int length, String lower, String upper, String number, String special) 
    {
        randomPassword passwordGenerator = new randomPassword();
        if(lower != null)
        {
            for (int i = 97; i < 123; i++) {
                passwordGenerator.crunchifyValueObj.add((char) i);
            }
        }
        if(upper != null)
        {
            for (int i = 65; i < 91; i++) {
                passwordGenerator.crunchifyValueObj.add((char) i);
            } 
        }
        if(number != null)
        {
             for (int i = 48; i < 58; i++) {
                passwordGenerator.crunchifyValueObj.add((char) i);
            }
        }
        if(special != null)
        {
             for (int i = 97; i < 123; i++) {
                passwordGenerator.crunchifyValueObj.add((char) i);
            }
        }
        
        StringBuilder crunchifyBuilder = new StringBuilder();
        for (int len = 0; len < length ; len++) 
        {
            crunchifyBuilder.append(passwordGenerator.crunchifyGetRandom());
        }
 
        log(crunchifyBuilder.toString());
        return crunchifyBuilder.toString();
    }
 
    public static void main(String[] args) {
        /*
        randomPassword passwordGenerator = new randomPassword();
        
        for (int i = 97; i < 123; i++) {
            passwordGenerator.crunchifyValueObj.add((char) i);
        }
        
        log("Crunchify Password Generator Utility: \n");
        StringBuilder crunchifyBuilder = new StringBuilder();
 
            for (int length = 0; length < 42; length++) {
                crunchifyBuilder.append(passwordGenerator.crunchifyGetRandom());
            }
 
        log(crunchifyBuilder.toString());  */
        
    }
 
    // Simple log util
    private static void log(String string) {
 
        System.out.println(string);
 
    }
 
}