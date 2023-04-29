package com.example.endpointmonitoring.jwt;

import com.example.endpointmonitoring.dto.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;

import static com.example.endpointmonitoring.jwt.SecurityConstants.MESSAGE;
import static com.example.endpointmonitoring.jwt.SecurityConstants.SECRET;

@Service
public class JWTGeneratorImpl implements JWTGeneratorInterface{

    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken="";
        jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, SECRET).compact();
        Map<String, String> jwtTokenGen = new HashMap<>();
        jwtTokenGen.put("token", jwtToken);
        jwtTokenGen.put("message", MESSAGE);
        return jwtTokenGen;
    }
}
