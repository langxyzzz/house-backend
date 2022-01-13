package com.langxy.house.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.langxy.house.pojo.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author langxy
 */
@Component
public class JwtService {

    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    public String getToken(User user) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withAudience(user.getId().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(date)
                .withClaim("loginName", user.getLoginName())
                .sign(Algorithm.HMAC256(user.getId().toString()));
    }

    public Long getUserId(HttpServletRequest request) {
        final String token = request.getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        return Long.valueOf(userId);
    }

}
