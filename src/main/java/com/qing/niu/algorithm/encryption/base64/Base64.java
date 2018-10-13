package com.qing.niu.algorithm.encryption.base64;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 通用base64实现 ，基于ASCII字符集
 * 3*8 = 4*6
 * 每三个字节（3*8=24bit）转换成四个字符(每6个bit表示一个base64字符)
 * </p>
 *
 * @author huqingniu
 * @version 1.0.0
 * @date 2018/10/12
 */
public class Base64 {

    private static final int[] DECODE_CHARSET = new int[128];

    private Base64(){}

    /**
     * base64 定义的字符集，
     * 不同的base64实现，字符集定义会不同
     * MIME的Base64实现选用了大写字母、小写字母和0~9的数字作为前62个字符。
     * 其他实现通常会沿用MIME的这种方式，而仅仅在最后2个字符上有所不同，例如UTF-7编码
     */
    private static final char[] CHARSET = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };

    static {
        for (int i = 0; i < 64; i++) {
            DECODE_CHARSET[CHARSET[i]] = i;
        }
    }

    /**
     * base64编码
     *
     * @param inputStr 待base64编码的字符串
     * @param charset  待base64编码的字符串是采用什么字符集编码的，取值可参考charsets.jar包，一般取UTF-8、GBK
     * @param padding  通过不断将每3个字节转换为4个Base64字符之后，是否在末尾添加字符“=”
     * @return base64编码后字符串
     * @throws UnsupportedEncodingException 不支持的编码异步，charset值填的不合法或不支持
     */
    public static String encode(String inputStr, String charset, boolean padding) throws UnsupportedEncodingException {
        String encodeStr = null;
        byte[] bytes = inputStr.getBytes(charset);

        encodeStr = encode(bytes, padding);
        return encodeStr;
    }

    /**
     * base64编码
     *
     * @param bytes 待base64编码的字节数组
     * @param padding 通过不断将每3个字节转换为4个Base64字符之后，是否在末尾添加字符“=”
     * @return base64编码后字符串
     */
    public static String encode(byte[] bytes, boolean padding) {
        // 4 6-bit groups
        int group_6bit_1,group_6bit_2,group_6bit_3,group_6bit_4;
        // 3 8-bits group
        int byte_1,byte_2,byte_3;
        // number of 3-byte groups
        int groups = bytes.length / 3;
        // 通过不断将每3个字节转换为4个Base64字符之后，最后可能会出现以下3种情况之一：
        // 1. 没有字节剩下
        // 2. 还剩下1个字节
        // 3. 还剩下2个字节
        int tail = bytes.length % 3;
        StringBuilder sb = new StringBuilder(groups * 4 + 4);
        // handle each 3-byte group
        for (int i = 0; i < groups; i++) {
            byte_1 = bytes[3 * i] & 0xFF;
            byte_2 = bytes[3 * i + 1] & 0xFF;
            byte_3 = bytes[3 * i + 2] & 0xFF;

            group_6bit_1 = byte_1 >>> 2;
            group_6bit_2 = (byte_1 & 0x03) << 4 | byte_2 >>> 4;
            group_6bit_3 = (byte_2 & 0x0F) << 2 | byte_3 >>> 6;
            group_6bit_4 = byte_3 & 0x3F;

            sb.append(CHARSET[group_6bit_1])
                    .append(CHARSET[group_6bit_2])
                    .append(CHARSET[group_6bit_3])
                    .append(CHARSET[group_6bit_4]);
        }
        // handle last 1 or 2 byte(s)
        if (tail == 1) {
            byte_1 = bytes[bytes.length - 1] & 0xFF;

            group_6bit_1 = byte_1 >>> 2;
            group_6bit_2 = (byte_1 & 0x03) << 4;

            sb.append(CHARSET[group_6bit_1])
                    .append(CHARSET[group_6bit_2]);

            if (padding) {
                sb.append('=').append('=');
            }
        } else if (tail == 2) {
            byte_1 = bytes[bytes.length - 2] & 0xFF;
            byte_2 = bytes[bytes.length - 1] & 0xFF;

            group_6bit_1 = byte_1 >>> 2;
            group_6bit_2 = (byte_1 & 0x03) << 4 | byte_2 >>> 4;
            group_6bit_3 = (byte_2 & 0x0F) << 2;

            sb.append(CHARSET[group_6bit_1])
                    .append(CHARSET[group_6bit_2])
                    .append(CHARSET[group_6bit_3]);

            if (padding) {
                sb.append('=');
            }
        }
        return sb.toString();
    }

    /**
     * 对base64编码后的字符串进行base64解码
     *
     * @param code 经base64编码后的字符串
     * @return base64解码后的字节数组
     */
    public static byte[] decode(String code) {
        char[] chars = code.toCharArray();
        // 4 6-bit groups
        int group_6bit_1,group_6bit_2,group_6bit_3,group_6bit_4;
        // 3 8-bits group
        int byte_1,byte_2,byte_3;
        int len = chars.length;
        // ignore last '='s
        if (chars[chars.length - 1] == '=') {
            len--;
        }
        if (chars[chars.length - 2] == '=') {
            len--;
        }
        int groups = len / 4;
        int tail = len % 4;
        // each group of characters (4 characters) will be converted into 3 bytes,
        // and last 2 or 3 characters will be converted into 1 or 2 byte(s).
        byte[] bytes = new byte[groups * 3 + (tail > 0 ? tail - 1 : 0)];
        int byteIdx = 0;
        // decode each group
        for (int i=0; i<groups; i++) {
            group_6bit_1 = DECODE_CHARSET[chars[4*i]];
            group_6bit_2 = DECODE_CHARSET[chars[4*i + 1]];
            group_6bit_3 = DECODE_CHARSET[chars[4*i + 2]];
            group_6bit_4 = DECODE_CHARSET[chars[4*i + 3]];

            byte_1 =  group_6bit_1         << 2 | group_6bit_2 >>> 4;
            byte_2 = (group_6bit_2 & 0x0F) << 4 | group_6bit_3 >>> 2;
            byte_3 = (group_6bit_3 & 0x03) << 6 | group_6bit_4;

            bytes[byteIdx++] = (byte) byte_1;
            bytes[byteIdx++] = (byte) byte_2;
            bytes[byteIdx++] = (byte) byte_3;
        }

        // decode last 2 or 3 characters
        if (tail == 2) {
            group_6bit_1 = DECODE_CHARSET[chars[len - 2]];
            group_6bit_2 = DECODE_CHARSET[chars[len - 1]];

            byte_1 = group_6bit_1 << 2 | group_6bit_2 >>> 4;
            bytes[byteIdx] = (byte) byte_1;
        } else if (tail == 3) {
            group_6bit_1 = DECODE_CHARSET[chars[len - 3]];
            group_6bit_2 = DECODE_CHARSET[chars[len - 2]];
            group_6bit_3 = DECODE_CHARSET[chars[len - 1]];

            byte_1 =  group_6bit_1         << 2 | group_6bit_2 >>> 4;
            byte_2 = (group_6bit_2 & 0x0F) << 4 | group_6bit_3 >>> 2;

            bytes[byteIdx++] = (byte) byte_1;
            bytes[byteIdx]   = (byte) byte_2;
        }
        return bytes;
    }
}
