package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 24275 on 2016/6/29.
 */
public class EncryptUtils {

    private static final char[] HexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    public static String digest(String input, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] bytes = md.digest(input.getBytes("UTF-8"));
        return toHex(bytes);
    }

    public static String md5(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digest(input, "MD5");
    }

    public static String sha(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digest(input, "SHA");
    }

//    public static String sha1(String input) throws NoSuchAlgorithmException {
//        return digest(input, "SHA1");
//    }


    public static String sha256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digest(input, "SHA-256");
    }


    public static String sha512(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digest(input, "SHA-512");
    }


//    private static byte[] MD5(String input) throws NoSuchAlgorithmException {
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        return md.digest(input.getBytes());
//    }

//    public static String MD5Hex(String input, boolean sub16) {
//        return toHex(MD5(input))
//    }

//    public static String MD5Hex(String input) {
//        return MD5Hex(input, false);
//    }


    private static String toHex(byte[] bytes) {
        char[] chs = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            chs[i * 2] = HexDigits[bytes[i] >>> 4 & 0xf];
            chs[i * 2 + 1] = HexDigits[bytes[i] & 0xf];
        }
        return new String(chs);
    }


}