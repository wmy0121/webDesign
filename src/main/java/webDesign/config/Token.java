package webDesign.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import webDesign.domain.User;

import java.util.Date;

public class Token {

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 设置过期时间为1小时后

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("roleId", user.getRoleId()) // 添加角色ID
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "xEytQZCBHp/fla9SXLse2RawW5KzW+62wXKYP3RMbIsan81z32j6P84baA9fzVATl6O4TSm5FEXX+RnIVJ04Vg==")
                .compact();
    }

    public int extractUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("xEytQZCBHp/fla9SXLse2RawW5KzW+62wXKYP3RMbIsan81z32j6P84baA9fzVATl6O4TSm5FEXX+RnIVJ04Vg==")
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public int extractRoleId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("xEytQZCBHp/fla9SXLse2RawW5KzW+62wXKYP3RMbIsan81z32j6P84baA9fzVATl6O4TSm5FEXX+RnIVJ04Vg==")
                .parseClaimsJws(token)
                .getBody();

        return (int) claims.get("roleId");
    }
}

