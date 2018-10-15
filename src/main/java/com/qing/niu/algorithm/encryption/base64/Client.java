package com.qing.niu.algorithm.encryption.base64;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * <p>
 *     客户端测试
 * </p>
 *
 * @Author Gui Jin Kang
 * @Date 2018/10/13 15:26
 * @ProjectName degisnpattern
 * @Version 1.0.0
 */
@Slf4j
public class Client {

    public static void main(String[] args) throws Exception{
        log.info("JVM默认字符编码:{}",Charset.defaultCharset());
        System.out.println("系统默认编码:"+System.getProperty("file.encoding"));
        System.out.println("系统默认语言:"+ System.getProperty("user.language"));
        String waitBase64Str = "hockey boy 桂";
        String encodeStr = Base64.encode(waitBase64Str,"UTF-8",true);
        log.info("Base64编码后的字符串：{}",encodeStr);
        byte[] decodeBytes = Base64.decode(encodeStr);
        String decodeStr = new String(decodeBytes,"UTF-8");
        log.info("Base64解码后的字符串：{}",decodeStr);
        log.info("===============================================================");

        String waitEnc = "hockey boy 桂";
        //3des加密
        String key = "cf410f84904a44cc8a7f4811";
        log.info("密钥长度:{},待加密字符串:{}",key.length(),waitEnc);
        String threeDesEncStr = ThreeDes.encryptThreeDESECB(waitEnc,key);
        log.info("3des加密结果:{}",threeDesEncStr);
        String threeDesDecStr = ThreeDes.decryptThreeDESECB(threeDesEncStr,"cf410f84904a44cc8a7f4811");
        log.info("3des解密结果:{}",threeDesDecStr);

        //des加密
        String desKey = "cf410f80";
        log.info("密钥长度:{},待加密字符串:{}",key.length(),waitEnc);
        String desEncStr = ThreeDes.encryptDESCBC(waitEnc,desKey);
        log.info("des加密结果:{}",desEncStr);
        String desDecStr = ThreeDes.decryptDESCBC(desEncStr,"cf410f80");
        log.info("des解密结果:{}",desDecStr);
    }
}
