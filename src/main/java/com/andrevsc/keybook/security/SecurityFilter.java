package com.andrevsc.keybook.security;

import java.io.IOException;

import org.springframework.lang.NonNull; // <--- IMPORTANTE: Adicione isso
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.andrevsc.keybook.repository.UserRepository;
import com.andrevsc.keybook.service.auth.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, 
                                    @NonNull HttpServletResponse response, 
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        
        var token = recoverToken(request);
        
        // --- LOGS DE DEBUG ---
        if (token != null) {
            System.out.println("🔍 [SecurityFilter] Token recebido: " + token.substring(0, 10) + "...");
            try {
                var email = tokenService.validateToken(token);
                
                if (email != null) {
                    System.out.println("✅ [SecurityFilter] Token válido. Email: " + email);
                    UserDetails user = userRepository.findByEmail(email).orElse(null);
                    
                    if (user != null) {
                        System.out.println("👤 [SecurityFilter] Usuário encontrado: " + user.getUsername());
                        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        System.out.println("❌ [SecurityFilter] Usuário NÃO encontrado no banco para o email: " + email);
                    }
                } else {
                    System.out.println("❌ [SecurityFilter] Token inválido (validateToken retornou null)");
                }
            } catch (Exception e) {
                System.out.println("❌ [SecurityFilter] Erro ao validar token: " + e.getMessage());
            }
        } else {
            // Ignora logs para rotas públicas de auth
            if (!request.getRequestURI().contains("/auth")) {
                System.out.println("⚠️ [SecurityFilter] Sem token na requisição: " + request.getRequestURI());
            }
        }
        // ---------------------

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}