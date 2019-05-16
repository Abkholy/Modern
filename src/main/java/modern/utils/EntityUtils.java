/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modern.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author sergeykargopolov
 */
public class EntityUtils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 10000;
    private final int KEY_LENGTH = 256;


    /*A UUID (Universal Unique Identifier) is a 128-bit number used to 
    uniquely identify some object or entity on the Internet. 
    UUID is either guaranteed to be different or is, at least, 
    extremely likely to be different from any other UUID generated. */
    public String generateUUID() {
        String returnValue = UUID.randomUUID().toString().replaceAll("-", "");
        return returnValue;
    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public String generateEntityId(int length) {
        return generateRandomString(length);
    }

    public String generateEmailverificationToken(int length) {
        return generateRandomString(length);
    }


    public String getSalt(int length) {
        return generateRandomString(length);
    }

    public String generateSecurePassword(String password, String salt) {
        String returnValue = null;

        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = java.util.Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    public byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public byte[] encrypt(String securePassword, String accessTokenMaterial) throws InvalidKeySpecException {
        return hash(securePassword.toCharArray(), accessTokenMaterial.getBytes());
    }

    public Date ConvertDateString(String date) throws ParseException {
        Date convertedDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return convertedDate;
    }


    public String decode(String s) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(s));
    }

    public String encode(String s) {
        return Base64.encodeBase64String(StringUtils.getBytesUtf8(s));
    }
}
