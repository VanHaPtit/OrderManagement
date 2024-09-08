//package com.example.WebDemo.configuration;
//
//import com.example.WebDemo.Model.Role;
//import com.example.WebDemo.Model.User;
//import com.example.WebDemo.Repository.RoleRepository;
//import com.example.WebDemo.Repository.UserRepository;
//import com.example.WebDemo.constant.PredefinedRole;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.experimental.NonFinal;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.HashSet;
//
//@Configuration
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//@Slf4j
//public class ApplicationInitConfig {
//
//    PasswordEncoder passwordEncoder;
//
//    @NonFinal
//    static final String ADMIN_USER_NAME = "admin";
//
//    @NonFinal
//    static final String ADMIN_PASSWORD = "admin";
//
//    @Bean
//    @ConditionalOnProperty(
//            prefix = "spring",
//            value = "datasource.driverClassName",
//            havingValue = "com.mysql.cj.jdbc.Driver")
//    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
//        log.info("Initializing application.....");
//        return args -> {
//            if (userRepository.findByUserName(ADMIN_USER_NAME).isEmpty()) {
//                roleRepository.save(Role.builder()
//                        .name(PredefinedRole.USER_ROLE)
//                        .description("User role")
//                        .build());
//
//                Role adminRole = roleRepository.save(Role.builder()
//                        .name(PredefinedRole.ADMIN_ROLE)
//                        .description("Admin role")
//                        .build());
//
//                // Chuyển đổi Set<Role> thành Set<String> với tên vai trò
//                var roleNames = new HashSet<String>();
//                roleNames.add(adminRole.getName());
//
//                User user = User.builder()
//                        .userName(ADMIN_USER_NAME)
//                        .passWord(passwordEncoder.encode(ADMIN_PASSWORD))
//                        .roles(roleNames) // Truyền Set<String> vào đây
//                        .build();
//
//                userRepository.save(user);
//                log.warn("admin user has been created with default password: admin, please change it");
//            }
//            log.info("Application initialization completed .....");
//        };
//    }
//
//}
