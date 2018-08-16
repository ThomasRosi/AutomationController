package com.thomas.informatique.heh.be.projectandroid17_18.Models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class used to secure passwords in the database.
 *
 * @author Thomas Rosi
 */

public class HashPassword {

    public static String hashPassword(String pwd) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b1 : b){
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return sb.toString();
    }

}
