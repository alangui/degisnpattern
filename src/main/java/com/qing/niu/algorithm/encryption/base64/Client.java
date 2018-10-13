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

    }
}
