package com.zei.happy.utils;

import com.zei.happy.domain.Admin;
import com.zei.happy.domain.Friend;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 用户Friend jwt
 */
public class JwtUtils {

    //项目标识名
    private static final String SUBJECT = "happy";

    //过期时间 1天
    private static final Long EXPIRE = 1000 * 60 * 60 * 24L;

    //秘钥
    private static final String APPSECRET = "zzei666";

    /**
     * 生成token
     * @param friend
     * @return
     */
    public static String createToken(Friend friend){

        if(friend == null || friend.getId() == null || friend.getName() == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",friend.getId())
                .claim("name",friend.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }

    /**
     * 生成token 加入uuid一起加密
     * @param friend
     * @return
     */
    public static String createToken(Friend friend, String uuid){

        if(friend == null || friend.getId() == null || friend.getName() == null || friend.getHeadImg() == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",friend.getId())
                .claim("name",friend.getName())
                .claim("img",friend.getHeadImg())
                .claim("uuid",uuid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }

    /**
     * 生成管理员token 加入uuid一起加密
     * @param admin
     * @return
     */
    public static String createAdminToken(Admin admin, String uuid){

        if(admin == null || admin.getId() == null || admin.getName() == null || admin.getState() == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",admin.getId())
                .claim("name",admin.getName())
                .claim("state",admin.getState())
                .claim("uuid",uuid)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();

        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Claims checkToken(String token){
        try {
            Claims claims = Jwts.parser().setSigningKey(APPSECRET).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
