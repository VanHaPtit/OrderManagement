//package com.example.WebDemo.Controller.Admin;
//
//
//import com.example.WebDemo.Model.Role;
//import com.example.WebDemo.Model.User;
//import com.example.WebDemo.Repository.RoleRepository;
//import com.example.WebDemo.Repository.UserRepository;
//import com.example.WebDemo.Service.UserService;
//import com.example.WebDemo.constant.PredefinedRole;
//import com.example.WebDemo.exception.AppException;
//import com.example.WebDemo.exception.ErrorCode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/User")
//public class UserController2 {
//    @Autowired
//    private UserService userService ;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder ;
//
//    @Autowired
//    private RoleRepository roleRepository ;
//
//    @Autowired
//    private UserRepository userRepository ;
//
//    @GetMapping("/CreateUser")
//    public ModelAndView showform() {
//        ModelAndView modelAndView = new ModelAndView("Admin/CreateAccount");
//        return modelAndView;
//    }
//
//    @PostMapping("/CreateUser")
//    public ModelAndView save(@Validated @ModelAttribute("user") User user , BindingResult bindingResult){
//        ModelAndView modelAndView = new ModelAndView("Admin/CreateAccount");
//        if (bindingResult.hasFieldErrors()) {
//            return modelAndView;
//        }
//        else{
//            userService.save(user);
//            ModelAndView modelAndView1 = new ModelAndView("Admin/login");
//            return modelAndView1;
//        }
//    }
//
//
////    @PostMapping("/CreateUser")
////    public ModelAndView save(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
////        ModelAndView modelAndView = new ModelAndView("Admin/CreateAccount");
////
////        if (bindingResult.hasFieldErrors()) {
////            return modelAndView;
////        } else {
////            user.setPassWord(passwordEncoder.encode(user.getPassWord()));
////
////            Set<Role> roles = new HashSet<>();
////            roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);
////            user.setRoles(roles);
////
////            try {
////                user = userRepository.save(user);
////            } catch (DataIntegrityViolationException exception) {
////                throw new AppException(ErrorCode.USER_EXISTED);
////            }
////            ModelAndView modelAndView1 = new ModelAndView("Admin/login");
////            return modelAndView1;
////        }
////    }
//
//
//
//}
