package com.example.WebDemo.Controller.Admin;

import com.example.WebDemo.Jwt.JwtUtils;
import com.example.WebDemo.Model.JwtResponse;
import com.example.WebDemo.Model.Role;
import com.example.WebDemo.Model.User;
import com.example.WebDemo.Repository.RoleRepository;
import com.example.WebDemo.Repository.UserRepository;
import com.example.WebDemo.Service.UserDetailsImpl;
import com.example.WebDemo.enums.ERole;
import com.example.WebDemo.request.LoginRequest;
import com.example.WebDemo.request.SignupRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @GetMapping("/signin")
    public ModelAndView showsignin() {
        ModelAndView modelAndView = new ModelAndView("Admin/login");
        return modelAndView;
    }


    @PostMapping("/signin")
    public ModelAndView authenticateUser(@ModelAttribute LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            ModelAndView modelAndView = new ModelAndView("Admin/index");
            modelAndView.addObject("jwt", jwt);
            modelAndView.addObject("username", userDetails.getUsername());
            modelAndView.addObject("roles", roles);
            return modelAndView;
        } catch (AuthenticationException e) {
            ModelAndView modelAndView = new ModelAndView("Admin/login");
            modelAndView.addObject("error", "Invalid username or password.");
            return modelAndView;
        }
    }







//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));
//    }

















    @GetMapping("/signup")
    public ModelAndView showsignup() {
        ModelAndView modelAndView = new ModelAndView("Admin/signup");
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView registerUser(@Valid @ModelAttribute SignupRequest signUpRequest, BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView("Admin/signup");

        if (result.hasErrors()) {
            model.addAttribute("error", "Please correct the errors in the form");
            return modelAndView;
        }

        // kiểm tra user và gmail xem đã được dùng chưa
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            model.addAttribute("error", "Error: Username is already taken!");
            return modelAndView;
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            model.addAttribute("error", "Error: Email is already in use!");
            return modelAndView;
        }

        // Create new user's account
        User user = new User(signUpRequest.getUserName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassWord()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        modelAndView.addObject("success", "User registered successfully!");
        return modelAndView;
    }
}
