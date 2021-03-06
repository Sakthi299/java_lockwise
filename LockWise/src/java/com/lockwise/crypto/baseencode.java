package com.lockwise.crypto;
import static com.lockwise.crypto.Blowfish.calldecrypt;
import static com.lockwise.crypto.Blowfish.callencrypt;
import static com.lockwise.crypto.argonPassword.generatePasswdForStorage;
import com.lockwise.services.dbconnect;
import com.opencsv.CSVReader;
import java.util.Base64;
import java.io.File;  // Import the File class
import java.io.IOException; 
import java.io.FileWriter;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class baseencode {
    
    public static String baseencode(String text)
    {
      String encrypt = Base64.getEncoder().encodeToString(text.getBytes());
      String encrypted = Base64.getEncoder().encodeToString(encrypt.getBytes());
      return encrypted;
    }
    public static String basedecode(String cipher)
    {
      byte[] actualByte = Base64.getDecoder().decode(Base64.getDecoder().decode(cipher));
      String decrypted = new String(actualByte);
      return decrypted;
    }  
    
    public static void spliting(String text, String userid, String cipher)
    {
      System.out.println(text.length());
      String temp = cipher.substring(0, text.length()/2);  //csv
      System.out.println(temp);
      File file = new File("C:\\Users\\HP\\Music\\IS_Lab_Project_LockWise\\brokensalt.csv");
      try {
        FileWriter outputfile = new FileWriter(file, true);
        CSVWriter writer = new CSVWriter(outputfile);
        String[] record = { userid, temp };
        writer.writeNext(record);
        writer.close();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
      
      temp = cipher.substring(text.length()/2);  // database 
      System.out.println(temp);
      
       try(Connection conn = dbconnect.initializeDatabase())
           {
            String query= "INSERT INTO salt(userid, salt) VALUES (?, ?)" ;
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, userid);
            stat.setString(2, temp);
            int result = stat.executeUpdate();
            if(result > 0)
                    {  
                    }
           }
         catch(Exception e)
           {
            System.out.println(e);
           }   
    }
    
    public static String gettingfullsalt(String userid) throws CsvValidationException
    {
        String firsthalf="", lasthalf="";
        try {
        FileReader filereader = new FileReader("C://Users//HP//Music//IS_Lab_Project_LockWise//brokensalt.csv");
        CSVReader reader = new CSVReader(filereader);
        StringBuffer buffer = new StringBuffer();
        String line[];
        while ((line = reader.readNext()) != null) {
            String user = line[0];
            String salt = line[1];
            if (user.equals(userid))
            {
            firsthalf = salt;
            break;    
            }
      }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
        try(Connection conn = dbconnect.initializeDatabase())
           {
            String q = "SELECT salt FROM salt where userid = ?";
            PreparedStatement s = conn.prepareStatement(q);
            s.setString(1, userid);
            ResultSet res= s.executeQuery();
            while(res.next())
            {
            lasthalf = res.getString("salt");
           }
          }
          catch(Exception e)
           {
            System.out.println(e);
           } 
     return firsthalf+lasthalf;   
    }
    
    public static String strtohex(String text)
    {
      String str = text;
      StringBuffer sb = new StringBuffer();
      char ch[] = str.toCharArray();
      for(int i = 0; i < ch.length; i++) {
         String hexString = Integer.toHexString(ch[i]);
         sb.append(hexString);
      }
      String result = sb.toString();
      return result;
    }
    
    public static String hextostr(String hex)
    {
      String str = hex;
      String result = new String();
      char[] charArray = str.toCharArray();
      for(int i = 0; i < charArray.length; i=i+2) {
         String st = ""+charArray[i]+""+charArray[i+1];
         char ch = (char)Integer.parseInt(st, 16);
         result = result + ch;
      }
      return result;  
    }
    
    public static String readconfig(String key)
    {
    String gotvalue = "";
    File configFile = new File("C:\\Users\\HP\\Documents\\NetBeansProjects\\lockWise\\src\\java\\com\\lockwise\\crypto\\config.properties");
    try {
        try (FileReader read = new FileReader(configFile)) {
            Properties props = new Properties();
            props.load(read);
            gotvalue = props.getProperty(key);
        }
    } catch (FileNotFoundException ex) {
  
    } catch (IOException ex) {
  
    }
        return gotvalue;
    }
    
    public static String argonifiedSalt(String mail) throws Exception
    {
        String head = gettingfullsalt(mail);
        String argonifiedsalt = generatePasswdForStorage(readconfig("secretKey") ,head);
        return argonifiedsalt;
    }
    
    public static String replacesalt(String firstsalt, String hash, String lastsalt)
    {
        String temp = hash.replace(firstsalt, "");
        return temp.replace(lastsalt, "");
    }
    
    public static void main(String[] args) throws CsvValidationException, Exception
    {
        System.out.println(baseencode("sai@gmail.com"));
        //System.out.println(basedecode("sai@gmail.com"));
        System.out.println(strtohex(baseencode("sai@gmail.com")));
        System.out.println(hextostr("597a4a47634646485a48525a5632787a5447314f646d4a525054303d"));
        
        //System.out.println(basedecode(baseencode("sai@gmail.com")));
        //tripleDES td= new tripleDES();
        //System.out.println(strtohex(td.encrypt(baseencode("sai@gmail.com")+"sai@gmail.com"+readconfig("pepper"))));
        //System.out.println(td.decrypt(hextostr(strtohex(td.encrypt(baseencode("sai@gmail.com")+"sai@gmail.com"+readconfig("pepper"))))));
        
        
        //spliting("sai@gmail.com", "1" ,baseencode("sai@gmail.com"));
        //System.out.println(gettingfullsalt("1"));
     
        //System.out.println(callencrypt("sai@gmail.com", "google"));
        //System.out.println(calldecrypt(callencrypt("sai@gmail.com", "google")));
    }
}
//31???????O???+???????????b?????????MA7sakthi??? ???Z???yug???????????????C???
//sainadhakula@gmail.com