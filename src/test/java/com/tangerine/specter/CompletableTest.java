package com.tangerine.specter;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.tencentyun.TLSSigAPIv2;

public class CompletableTest {

    private static final long appId = 1400809701;
    private static final String secretKey = "";
    private static final String roomId = "test_room";

    public static void main(String[] args) {
        String userid1 = "zhou_0556";
        TLSSigAPIv2 api = new TLSSigAPIv2(appId, secretKey);
        final String sig = api.genUserSig(userid1, 180 * 86400);
        System.out.println(StrUtil.format("--- 主播[{}]签名 ---", userid1));
        System.out.println(sig);
        //rtmp://rtmp.rtc.qq.com/push/房间号?sdkappid=应用&userid=用户名&usersig=签名
        System.out.println(StrUtil.format("--- 主播[{}]推流秘钥 ---", userid1));
        //秘钥：房间号?sdkappid=应用&userid=用户名&usersig=签名
        System.out.println(roomId + "?sdkappid=" + appId + "&userid=" + userid1 + "&usersig=" + sig);
        String userid2 = "user_momo";
        final String sig1 = api.genUserSig(userid2, 180 * 86400);
        System.out.println(StrUtil.format("--- 观众[{}]签名 ---", userid2));
        System.out.println(sig1);
        //拉流地址：rtmp://rtmp.rtc.qq.com/pull/房间号?sdkappid=应用&userid=用户名&usersig=签名&remoteuserid=对端用户名&sessionid=会话id
        System.out.println(StrUtil.format("--- 观众[{}]拉流地址 ---", userid2));
        System.out.println(StrUtil.format("rtmp://rtmp.rtc.qq.com/pull/{}?sdkappid={}&userid={}&usersig={}&remoteuserid={}&sessionid={}", roomId, appId, userid2, sig1, userid1, IdUtil.fastSimpleUUID()));
    }
}
