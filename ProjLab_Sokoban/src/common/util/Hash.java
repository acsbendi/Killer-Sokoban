package common.util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by bendi1 on 4/22/2018.
 */
public class Hash {
    public static long GetHashFor(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] encrypted = messageDigest.digest(password.getBytes());
            ByteBuffer bb = ByteBuffer.wrap(encrypted);
            return bb.getLong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
