package com.langxy.house.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.langxy.house.pojo.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author langxy
 */
@Component
public class JwtService {

    private static final long EXPIRE_TIME = 5 * 60 * 1000;

    public String getToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withAudience(user.getId().toString())
                .withExpiresAt(date)
                .sign(Algorithm.HMAC256(user.getLoginPwd()));
    }

}
