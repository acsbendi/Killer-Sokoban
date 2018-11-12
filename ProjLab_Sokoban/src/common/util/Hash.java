package common.util;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.nio.ByteBuffer;

/**
 * Created by bendi1 on 4/22/2018.
 */
public class Hash {
    public static long GetHashFor(String password){
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();

        byte[] encrypted = digestSHA3.digest(password.getBytes());
        ByteBuffer bb = ByteBuffer.wrap(encrypted);
        return bb.getLong();
    }
}
