package utils;

import java.security.SecureRandom;

public class UUID22 {


//    /**
//     * This array is a lookup table that translates 6-bit positive integer
//     * index values into their "Base64 Alphabet" equivalents as specified
//     * in "Table 1: The Base64 Alphabet" of RFC 2045 (and RFC 4648).
//     */
//    private static final char[] toBase64 = {
//            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
//            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
//            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
//            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
//    };

    /**
     * It's the lookup table for "URL and Filename safe Base64" as specified
     * in Table 2 of the RFC 4648, with the '+' and '/' changed to '-' and
     * '_'. This table is used when BASE64_URL is specified.
     */
    private static final char[] toBase64URL = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'
    };


    private byte[] data;

    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
    }


    private UUID22(byte[] data) {
        this.data = data;
    }

    public static UUID22 random() {
        SecureRandom ng = Holder.numberGenerator;

        byte[] randomBytes = new byte[16];
        ng.nextBytes(randomBytes);
        randomBytes[6] &= 0x0f;  /* clear version        */
        randomBytes[6] |= 0x40;  /* set to version 4     */
        randomBytes[8] &= 0x3f;  /* clear variant        */
        randomBytes[8] |= 0x80;  /* set to IETF variant  */
        return new UUID22(randomBytes);
    }

    public static void main(String[] args) {


        long startMs = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
//            UUID22.random().toString();
//            encode64(bytes);
//            byte[] bytes = UUID22.random().data;
//            System.out.println(new String(encode64(bytes)));
//            System.out.println(new String(OUUID22.encode(bytes)));
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
        long disMs = System.currentTimeMillis() - startMs;
        System.out.println("disMs:" + disMs);
//        UUID22 uuidHelper = UUID22.random();
//        System.out.println(new String(UUID22.encode(uuidHelper.data)));
//        System.out.println(uuidHelper.toString());
    }


    private static char[] encode64(byte[] src) {
        char[] dst = new char[22];
        for (int b = 0, c = 0; b < 16; b += 3, c += 4) {
            dst[c] = toBase64URL[src[b] >> 2 & 0x3f];
            if (c == 20) {
                dst[21] = toBase64URL[(src[b] & 0x3) << 4];
                break;
            }
            dst[c + 1] = toBase64URL[(src[b] & 0x3) << 4 | src[b + 1] >> 4 & 0xf];
            dst[c + 2] = toBase64URL[(src[b + 1] & 0xf) << 2 | src[b + 2] >> 6 & 0x3];
            dst[c + 3] = toBase64URL[src[b + 2] & 0x3f];
        }
        return dst;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return new String(encode64(data));
    }


//    private static char[] encode64(byte[] src) {
//        char[] dst = new char[22];
//        for (int i = 0, j = 0, max = 16; i < max; i += 3, j += 4) {
//            int start = i * 4 / 3;
//            dst[start] = toBase64URL[src[i] >> 2 & 0x3f];
//            if (start == 20) {
//                dst[21] = toBase64URL[(src[i] & 0x3) << 4];
//                break;
//            }
//            dst[start + 1] = toBase64URL[(src[i] & 0x3) << 4 | src[i + 1] >> 4 & 0xf];
//            dst[start + 2] = toBase64URL[(src[i + 1] & 0xf) << 2 | src[i + 2] >> 6 & 0x3];
//            dst[start + 3] = toBase64URL[src[i + 2] & 0x3f];
//        }
//        return dst;
//    }
    //    private char[] encode64(byte[] src) {
//        int bitLen = src.length * 8;
//        int rest = bitLen % 6;
//        int dstLen = bitLen / 6 + (rest == 0 ? 0 : 1);
//        char[] dst = new char[dstLen];
//        int end = dstLen - (rest == 0 ? 0 : 1);
//
//        for (int i = 0; i < end; i++) {
//            int start = i * 3 / 4;
//            dst[start] = toBase64URL[src[start] >> 2 & 0x3f];
//            dst[start + 1] = toBase64URL[(src[start] & 0x3) << 4 | src[start + 1] >> 4 & 0xf];
//            dst[start + 2] = toBase64URL[(src[start + 1] & 0xf) << 4 | src[start + 2] >> 6 & 0x3];
//            dst[start + 3] = toBase64URL[src[start] & 0x3f];
//        }
//        if (rest != 0) {
//
//        }
//        return dst;
//    }

//    private int desLen(int srcLen) {
//
//    }

}
