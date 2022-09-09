package com.maple.ucenter.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.alibaba.fastjson2.JSON;
import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import com.maple.ucenter.model.JwtModel;
import com.sun.net.httpserver.Headers;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class JwtUtils {

    private static String jwtKey = "leonardoBat";

    public static String generate(JwtModel model, int expire, TimeUnit timeUnit) {


        return "12312";
    }

    public static void main(String[] args) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        int expire = 10;


        Date expireDate = new Date(System.currentTimeMillis() + timeUnit.toMillis(expire));
        HashMap<String, Object> map = new HashMap<String, Object>() {
            {
                put("subject", "tfr971018");
                put("expire_date", expireDate.getTime());
            }
        };
        KeyPair keyPair = KeyUtil.generateKeyPair("RSA");
        JWTSigner jwtSigner = JWTSignerUtil.createSigner(AlgorithmUtil.getId("RS512"), keyPair);
        JWT jwt = JWT.create()
                          .addPayloads(map)
                          .setSigner(jwtSigner);

        System.out.println("JSON.toJSONString(jwt) = " + JSON.toJSONString(jwt));

        String sign = jwt.sign();
        System.out.println(sign);
        jwt = JWTUtil.parseToken(sign);
        System.out.println("JSON.toJSONString(jwt) = " + JSON.toJSONString(jwt));



    }

}
