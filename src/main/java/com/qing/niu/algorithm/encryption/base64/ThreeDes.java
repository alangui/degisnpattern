package com.qing.niu.algorithm.encryption.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * <p>
 *     ThreeDes加密
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/14 12:33
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
public class ThreeDes {

    private static final String IV = "1234567-";

    private ThreeDes(){}

    /**
     * CBC模式DES加密
     *
     * @param src 待加密字符串
     * @param key 密钥key (密钥字符串长度最低8)
     * @return 加密结果
     * @throws Exception 异常
     */
    public static String encryptDESCBC(final String src, final String key) throws Exception {
        // --生成key,同时制定是des还是DESede,两者的key长度要求不同
        final DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        final SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // --加密向量
        final IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

        // --通过Chipher执行加密得到的是一个byte的数组,Cipher.getInstance("DES")就是采用ECB模式,
        // cipher.init(Cipher.ENCRYPT_MODE,secretKey)就可以了.
        final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        final byte[] b = cipher.doFinal(src.getBytes("UTF-8"));

        // --通过base64,将加密数组转换成字符串
        final BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b);
    }

    /**
     * CBC模式DES解密
     *
     * @param src 待解密密文
     * @param key 加密密钥key
     * @return 解密后字符串
     * @throws Exception 异常
     */
    public static String decryptDESCBC(final String src, final String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        final BASE64Decoder decoder = new BASE64Decoder();
        final byte[] bytesrc = decoder.decodeBuffer(src);

        // --解密的key
        final DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        final SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        // --向量
        final IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));

        // --Chipher对象解密Cipher.getInstance("DES")就是采用ECB模式,
        // cipher.init(Cipher.DECRYPT_MODE,secretKey)就可以了.
        final Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }

    /**
     * 3DESECB加密,key必须是长度大于等于 3*8 = 24个字符
     *
     * @param src 待加密字符串
     * @param key 密码key （密钥字符串长度最低24）
     * @return 解密字符串
     * @throws Exception 异常
     */
    public static String encryptThreeDESECB(final String src, final String key) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);
        //src.getBytes()默认使用UTF-8编码
        final byte[] b = cipher.doFinal(src.getBytes());

        final BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 3DES解密
     *
     * @param src 加密密文
     * @param key 加密密钥
     * @return 加密后字符串
     * @throws Exception 异常
     */
    public static String decryptThreeDESECB(final String src, final String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        final BASE64Decoder decoder = new BASE64Decoder();
        final byte[] bytesrc = decoder.decodeBuffer(src);
        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }

}
