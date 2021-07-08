package com.lockwise.crypto;

import static com.lockwise.crypto.argonPassword.generatePasswdForStorage;
import static com.lockwise.crypto.baseencode.argonifiedSalt;
import static com.lockwise.crypto.baseencode.gettingfullsalt;
import static com.lockwise.crypto.baseencode.readconfig;
import com.opencsv.exceptions.CsvValidationException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Blowfish {

    public static String encrypt(String password, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, KS);
        String encryptedtext = Base64.getEncoder().encodeToString(cipher.doFinal(password.getBytes("UTF-8")));
        return encryptedtext;
    }

    public static String decrypt(String encryptedtext, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        byte[] ecryptedtexttobytes = Base64.getDecoder().decode(encryptedtext);
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, KS);
        byte[] decrypted = cipher.doFinal(ecryptedtexttobytes);
        String decryptedString = new String(decrypted, Charset.forName("UTF-8"));
        return decryptedString;
    }
    
    public static String callencrypt(String mail, String plaintext) throws Exception, CsvValidationException
    {
        String argonifiedsalt = argonifiedSalt(mail);
        final String password = argonifiedsalt.substring(0, argonifiedsalt.length()/2)+plaintext+argonifiedsalt.substring(argonifiedsalt.length()/2);   
        final String key = readconfig("secretKey");
        String enc_ = encrypt(password, key);
        return enc_;
    }

    public static String calldecrypt(String cipher) throws Exception, CsvValidationException
    {
        final String key = readconfig("secretKey");
        String dec_ = decrypt(cipher, key);
        return dec_;
    }
    
    public static void main(String[] args) throws Exception {

    }
}