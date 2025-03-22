//package com.example.gateway;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//
//@Slf4j
//public class CustomAuthorizationFilter implements WebFilter {
//
//    @Override
//    public Mono<Authentication> filter(ServerWebExchange exchange, WebFilterChain chain) {
//        String path = exchange.getRequest().getURI().getPath();
//        if (path.equals("/api/v1/users/sign-up") || path.equals("/api/v1/users/sign-in")) {
//            return chain.filter(exchange);  //회원가입, 로그인은 JWT 토큰인증 x
//        }
//
//        Authentication authentication = (Authentication) ReactiveSecurityContextHolder.getContext();
//
//        if (authentication == null || !authentication.isAuthenticated()) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_COMPANY"))) {
//            return chain.filter(exchange);
//        }
//
//        // 권한이 없으면 403 상태 코드 반환
//        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//        return exchange.getResponse().setComplete();
//    }
//}
