package org.redrock.ljj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.redrock.util.Const;


public class Crypt {
    public static void Crypt(HttpServletRequest request, HttpServletResponse response, Map<String, String> result) throws IOException {
        try {

            String encodingAeskey = Const.EncodingAESKey;
            String token = Const.Token;
            String appId = Const.AppId;
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String msgSignature = request.getParameter("msg_signature");

            if (request.getParameter("encrypt_type") != null && request.getParameter("encrypt_type").length() != 0) {
                String toUserName = result.get("ToUserName");
                String encrypt = result.get("Encrypt");
                String format = "<xml><ToUserName><![CDATA[%s]]></ToUserName><Encrypt><![CDATA[%s]]></Encrypt></xml>";
                String fromXML = String.format(format, toUserName, encrypt);

                WXBizMsgCrypt p = new WXBizMsgCrypt(token, encodingAeskey, appId);
                String result2 = p.decryptMsg(msgSignature, timestamp, nonce, fromXML);

                System.out.println("解密后明文: " + result2);

                WXBizMsgCrypt pc1 = new WXBizMsgCrypt(token, encodingAeskey, appId);
                String mingwen1 = pc1.encryptMsg(encodingAeskey, timestamp, nonce);
                System.out.println("加密后: " + mingwen1);

            }

        } catch (AesException e) {
            e.printStackTrace();
        }
    }
}
