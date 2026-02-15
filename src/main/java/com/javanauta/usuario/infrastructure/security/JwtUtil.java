package com.javanauta.usuario.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {

    // Agora ele lê do application.properties.
    // Se não encontrar, usa a string longa depois do ":" como padrão.
    @Value("${jwt.secret:9a4f2c8d3e1f5a6b7c8d9e0f1a2b3c4d5e6f7g8h9i0j1k2l3m4n5o6p7q8r9s0t}")
    private String secret;

    // Método auxiliar para gerar a chave segura a partir da String
    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Gera um token JWT com o nome de usuário e validade de 1 hora
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Define o nome de usuário como o assunto do token
                .issuedAt(new Date()) // Define a data e hora de emissão do token
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Define a data e hora de expiração (1 hora a partir da emissão)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact(); // Constrói o token JWT
    }

    // Extrai as claims do token JWT (informações adicionais do token)
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token) // Analisa o token JWT e obtém as claims
                .getPayload(); // Retorna o corpo das claims
    }

    // Extrai o nome de usuário do token JWT

    public String extrairEmailToken(String token) {
    
        // Obtém o assunto (nome de usuário) das claims do token
        return extractClaims(token).getSubject();
    }

    // Verifica se o token JWT está expirado
    public boolean isTokenExpired(String token) {
        // Compara a data de expiração do token com a data atual
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token JWT verificando o nome de usuário e se o token não está expirado
    public boolean validateToken(String token, String username) {
        // Extrai o nome de usuário do token
        final String extractedUsername = extrairEmailToken(token);

        // Verifica se o nome de usuário do token corresponde ao fornecido e se o token não está expirado
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
