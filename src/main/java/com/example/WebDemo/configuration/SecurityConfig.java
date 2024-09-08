package com.example.WebDemo.configuration;
//
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.experimental.NonFinal;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.catalina.filters.CorsFilter;
//import org.h2.engine.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtValidators;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.crypto.spec.SecretKeySpec;
////
////@Configuration
////@EnableWebSecurity
////@EnableMethodSecurity
////public class SecurityConfig {
////    private final String[] PUBLIC_ENDPOINTS = {
////            "/users", "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh"
////    };
////
////    @Autowired
////    private CustomJwtDecoder customJwtDecoder;
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
////        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
////                .permitAll()
////                .anyRequest()
////                .authenticated());
////
////        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
////                        .decoder(customJwtDecoder)
////                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
////                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
////        httpSecurity.csrf(AbstractHttpConfigurer::disable);
////
////        return httpSecurity.build();
////    }
////
////    @Bean
////    public CorsFilter corsFilter() {
////        CorsConfiguration corsConfiguration = new CorsConfiguration();
////
////        corsConfiguration.addAllowedOrigin("*");
////        corsConfiguration.addAllowedMethod("*");
////        corsConfiguration.addAllowedHeader("*");
////
////        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
////        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
////
////        return new CorsFilter(urlBasedCorsConfigurationSource);
////    }
////
////    @Bean
////    JwtAuthenticationConverter jwtAuthenticationConverter() {
////        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
////        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
////
////        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
////
////        return jwtAuthenticationConverter;
////    }
////
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
//}











//
//@Slf4j
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@EnableJpaAuditing
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    final static String[] NotAuthentication_Url = { "/signup", "/logoutnow", "/login/**", "/refreshtoken" };
//    final static String[] Authentication_Url = {"/friend/**", "/follow**", "/message/**", "/notification/**", "/inform/**", "/user/**", "/like/**", "/post/**", "/posts", "/comment/**", "/find/**"};
//    @Value("${jwt.signerKey}")
//    @NonFinal
//    String signerKey;
//    CustomJwtDecoder customJwtDecoder;
//    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        long reqest = 0;
//        System.err.println("number requets: " + reqest++);
//        httpSecurity
//                .authorizeRequests(request ->
//                        request.requestMatchers(NotAuthentication_Url ).permitAll()
//                                .requestMatchers(Authentication_Url ).hasRole(String.valueOf(Role.USER))
//                                .anyRequest()
//                                .authenticated());
////                .oauth2Login(oauth2 -> oauth2
////                        .defaultSuccessUrl("/login/google", true)
////                        .userInfoEndpoint(userInfo -> userInfo
////                                .userService(new DefaultOAuth2UserService())
////                        )
////                );
//        httpSecurity.oauth2ResourceServer(oauth2 ->
//                oauth2.jwt(jwtConfigurer
//                                -> jwtConfigurer.decoder(customJwtDecoder)
//                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//        ).csrf(csrf -> csrf.disable());
//        return httpSecurity.build();
//    }
//
//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter(){
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HmacSHA512");
//        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//        jwtDecoder.setJwtValidator(JwtValidators.createDefaultWithIssuer("hamom.com"));
//        return jwtDecoder;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
//}