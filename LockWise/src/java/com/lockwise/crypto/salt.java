package com.lockwise.crypto;

import java.util.HashMap;

public class salt {
    
    static HashMap<Integer, String> brokensalt = new HashMap<Integer, String>();
   
    public static void addSalt(String half, int index)
    {
        brokensalt.put(index, half);
    }
    
    public static String getSalt(int index)
    {
        return brokensalt.get(index);
    }
    
    public static void getCollection()
    {
      for (Integer i : brokensalt.keySet()) 
      {
        System.out.println("key: " + i + " value: " + brokensalt.get(i));
      }  
    }
  public static void main(String[] args) {

  }
}
