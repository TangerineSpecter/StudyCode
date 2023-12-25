package com.tangerine.specter.jwt.tencent;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TencentMeetingDemo1 {

    private static final String host = "https://api.meeting.qq.com";

    public static void main(String[] args) throws Exception {
        String meetingId = "12269377189710152723";
        CancelMeetingRequest request = new CancelMeetingRequest();
        request.setMeetingId(meetingId);
        request.setUserid("rds_zhoul0556");
        request.setInstanceid(1);
        request.setReasonCode(1);
//        getRequest("/v1/users/list?page=1&pageSize=10");
        String s = JSON.toJSONString(request);
        String s1 = new GsonBuilder().create().toJson(request);
        postRequest(StrUtil.format("/v1/meetings/{}/cancel", meetingId), s1);
    }

    private static void postRequest(String apiUrl, String body) throws Exception {
        HttpRequest httpRequest = HttpUtil.createPost(host + apiUrl).addHeaders(getHeaders(apiUrl, body))
                .body(body);
        String result = httpRequest.execute().body();
        System.out.println(result);
    }

    public static void getRequest(String apiUrl) throws Exception {
        HttpRequest httpRequest = HttpUtil.createGet(host + apiUrl)
                .addHeaders(getHeaders(apiUrl, null));
        String result = httpRequest.execute().body();
        System.out.println(result);
    }

    private static Map<String, String> getHeaders(String url, String body) throws Exception {
        Map<String, String> headers = new HashMap<>();
        //生成随机正整数
        String nonce = String.valueOf(Math.abs(new SecureRandom().nextInt()));
        //当前时间秒数
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        headers.put("Content-Type", "application/json");
        headers.put("X-TC-Key", WemeetingConfig.secretId);
        headers.put("X-TC-Timestamp", timestamp);
        headers.put("X-TC-Nonce", nonce);
        headers.put("AppId", WemeetingConfig.appId);
        headers.put("SdkId", WemeetingConfig.skdId);
        String signFormat = "%s\nX-TC-Key=%s&X-TC-Nonce=%s&X-TC-Timestamp=%s\n%s\n";
        String tobeSign = String.format(signFormat, "POST", WemeetingConfig.secretId, nonce, timestamp,
                url);
        if(body != null) {
            tobeSign += body;
        }
        System.out.println("tobeSign ==>" + tobeSign);
        headers.put("X-TC-Signature", getSign(tobeSign, WemeetingConfig.secretKey));
        return headers;
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
