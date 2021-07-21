
package com.lockwise.services;

public class util {
    public int id;
    public String tag;
    public String name;
    
    //Passwords
    public String url;
    public String website;
    public String username;
    public String password;
    public String notes;
    //Addresses
    public String first;
    public String last;
    public String gender;
    public String addr_1;
    public String addr_2;
    public String city;
    public String country;
    public String state;
    public String zip;
    public String email;
    public String phone;
    //PaymentCards
    public String cardname;
    public String type;
    public String cardno;
    public String exmonth;
    public String exyear;
    public String cvv;
    //Bank Accounts
    public String bankname;
    public String ifsc;
    public String acholder;
    public String acnumber;
    public String brname;
    public String braddr;
    public String brphone;
    public String acdate;
    
util()
{
    
}
// Passwords
util(int i, String a, String b, String c, String d, String e, String f){
    id = i;
    tag = a;
    url = b;
    website = c;
    username = d;
    password = e;
    notes = f;
}

// Addresses
util(int x, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l, String m)
{
    id = x;
    name = a;
    tag = b;
    first = c;
    last = d;
    gender = e;
    addr_1 = f;
    addr_2 = g;
    city = h;
    country = i;
    state = j;
    zip = k;
    email = l;
    phone = m;
}

//Notes
util(int i, String a, String b, String c)
{
    id = i;
    tag = a;
    name = b;
    notes = c;
}

//Payment Cards
util(int x, String a, String b, String c, String d, String e, String f, String g, String h, String i)
{
    id = x;
    tag = a;
    name = b;
    cardname = c;
    type = d;
    cardno = e;
    exmonth = f;
    exyear = g;
    cvv = h;
    notes = i;
}

//Bank Accounts
util(int x, String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k, String l)
{
    id = x;
    tag = a;
    name= b;
    bankname = c;
    ifsc = d;
    type = e;
    acholder = f;
    acnumber = g;
    brname = h;
    braddr = i;
    brphone = j;
    acdate = k;
    notes = l;
}
}
