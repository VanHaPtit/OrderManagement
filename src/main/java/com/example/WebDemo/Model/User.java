

package com.example.WebDemo.Model;

//import jakarta.persistence.*;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//
//import java.util.Set;
//
//@Data
//@AllArgsConstructor
//@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "username")
//    private String userName;
//    @Column(name = "password")
//    private String passWord;
//    @Column(name = "fullname")
//    private String fullName;
//    @Column(name = "email")
//    private String email;
//    @Column(name = "telephone")
//    private String telephone;
//    @Column(name = "RetypePassWord")
//    private String retypePassWord ;
//
//    public User(Long id, String userName, String passWord, String fullName, String email, String telephone) {
//        this.id = id;
//        this.userName = userName;
//        this.passWord = passWord;
//        this.fullName = fullName;
//        this.email = email;
//        this.telephone = telephone;
//    }
//
//    public User(String userName, String passWord, String fullName, String email, String retypePassWord) {
//        this.userName = userName;
//        this.passWord = passWord;
//        this.fullName = fullName;
//        this.email = email;
//        this.retypePassWord = retypePassWord;
//    }
//
//    public String getRetypePassWord() {
//        return retypePassWord;
//    }
//
//    public void setRetypePassWord(String retypePassWord) {
//        this.retypePassWord = retypePassWord;
//    }
//
//    public User() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassWord() {
//        return passWord;
//    }
//
//    public void setPassWord(String passWord) {
//        this.passWord = passWord;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//}





import com.example.WebDemo.Model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String userName;  // Changed from userName to username

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String passWord;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String userName, String email, String passWord) {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
    }


    //    public User(String username, String email, String password) {
//        this.username = username;  // Updated
//        this.email = email;
//        this.password = password;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 120) String getPassWord() {
        return passWord;
    }

    public void setPassWord(@NotBlank @Size(max = 120) String passWord) {
        this.passWord = passWord;
    }

    public @NotBlank @Size(max = 20) String getUserName() {
        return userName;
    }

    public void setUserName(@NotBlank @Size(max = 20) String userName) {
        this.userName = userName;
    }


    //    public @NotBlank @Size(max = 20) String getUsername() {
//        return username;  // Updated
//    }
//
//    public void setUsername(@NotBlank @Size(max = 20) String username) {  // Updated
//        this.username = username;
//    }

    public @NotBlank @Size(max = 50) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 50) @Email String email) {
        this.email = email;
    }

//    public @NotBlank @Size(max = 120) String getPassword() {
//        return password;
//    }
//
//    public void setPassword(@NotBlank @Size(max = 120) String password) {
//        this.password = password;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
