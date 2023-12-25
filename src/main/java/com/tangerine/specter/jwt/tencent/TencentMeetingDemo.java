package com.tangerine.specter.jwt.tencent;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TencentMeetingDemo {

    private static final String host = "https://api.meeting.qq.com";

    public static void main(String[] args) throws Exception {
//        getRequest("/v1/users/list?page=1&pageSize=10");
        postRequest("/v1/meetings/mtn85a4f53640bb4c85aafc9deb41756bbf/enroll/ids");
    }

    public static void getRequest(String apiUrl) throws Exception {
        HttpGet request = new HttpGet(host + apiUrl);
        setHeaders(request, apiUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
    }

    public static void postRequest(String apiUrl) throws Exception {
        HttpPost request = new HttpPost(host + apiUrl);
        setHeaders(request, apiUrl);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);
    }

    private static void setHeaders(HttpGet request, String url) throws Exception {
        //生成随机正整数
        String nonce = String.valueOf(Math.abs(new SecureRandom().nextInt()));
        //当前时间秒数
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("X-TC-Key", WemeetingConfig.secretId);
        request.setHeader("X-TC-Timestamp", timestamp);
        request.setHeader("X-TC-Nonce", nonce);
        request.setHeader("AppId", WemeetingConfig.appId);
        request.setHeader("SdkId", WemeetingConfig.skdId);
        String signFormat = "%s\nX-TC-Key=%s&X-TC-Nonce=%s&X-TC-Timestamp=%s\n%s\n";
        String tobeSign = String.format(signFormat, "GET", WemeetingConfig.secretId, nonce, timestamp,
                url);
        System.out.println("tobeSign ==>" + tobeSign);
        request.setHeader("X-TC-Signature", getSign(tobeSign, WemeetingConfig.secretKey));
    }

    private static void setHeaders(HttpPost request, String url) throws Exception {
        //生成随机正整数
        String nonce = String.valueOf(Math.abs(new SecureRandom().nextInt()));
        //当前时间秒数
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        request.setHeader("Content-Type", "application/json");
        request.setHeader("X-TC-Key", WemeetingConfig.secretId);
        request.setHeader("X-TC-Timestamp", timestamp);
        request.setHeader("X-TC-Nonce", nonce);
        request.setHeader("AppId", WemeetingConfig.appId);
        request.setHeader("SdkId", WemeetingConfig.skdId);
        String signFormat = "%s\nX-TC-Key=%s&X-TC-Nonce=%s&X-TC-Timestamp=%s\n%s\n";
        String tobeSign = String.format(signFormat, "POST", WemeetingConfig.secretId, nonce, timestamp,
                url);
        System.out.println("tobeSign ==>" + tobeSign);
        request.setHeader("X-TC-Signature", getSign(tobeSign, WemeetingConfig.secretKey));
    }

    /**
     * 获取签名
     *
     * @param toSignStr 待签名字符串
     * @param signKey   签名key
     * @return 签名结果
     */
    public static String getSign(String toSignStr, String signKey) throws Exception {
        HMac hMac = new HMac(HmacAlgorithm.HmacSHA256, signKey.getBytes(UTF_8));
        String signature1 = hMac.digestHex(toSignStr);
        System.out.println("signature1 ==>" + signature1);
        String signature = DatatypeConverter.printHexBinary(hmac256(signKey.getBytes(UTF_8), toSignStr)).toLowerCase();
        System.out.println("signature ==>" + signature);
        return Base64.encodeBase64String(signature.getBytes(UTF_8));
    }

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("HmacSHA256 is not supported." + e.getMessage());
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        try {
            mac.init(secretKeySpec);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e.getClass().getName() + "-" + e.getMessage());
        }
        return mac.doFinal(msg.getBytes(UTF_8));
    }
}
