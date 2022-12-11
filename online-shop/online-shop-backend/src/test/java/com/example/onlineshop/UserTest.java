package com.example.onlineshop;

import com.example.onlineshop.security.models.Designer;
import com.example.onlineshop.security.models.User;
import com.example.onlineshop.security.services.DesignerService;
import com.example.onlineshop.security.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class UserTest {

    private User mockUser = new User();

    @Autowired
    private UserService userService;

    private String email = "test@gmail.com";
    private String firstName = "test";
    private String lastName = "test";

    private String password = "password";


    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserTest() {
    }

    @Before
    public void setUp() {
        mockUser.setFirstName(firstName);
        mockUser.setLastName(lastName);
        mockUser.setPassword(password);
    }

    @Test
    public void register() throws Exception {
        mockUser.setEmail("register@gmail.com");
        userService.registerUser(mockUser);

       UserDetails user = userService.loadUserByUsername(mockUser.getUsername());

       assert user != null;
    }

    @Test
    public void register_login() throws Exception {

        email = "register_login@gmail.com";

        mockUser.setEmail(email);

        userService.registerUser(mockUser);

        userService.loginUser(email, password);
    }

    @Test
    public void register_loginFail() throws Exception {
        email = "register_loginFail@gmail.com";

        mockUser.setEmail(email);

        userService.registerUser(mockUser);

        try {
            userService.loginUser(email, password);
        } catch (IllegalStateException e){
            assert e.getMessage().equals("User don't exist");
        }
    }

    @Test
    public void register_passwordFail() throws Exception {
        email = "register_passwordFail@gmail.com";

        mockUser.setEmail(email);
        mockUser.setPassword("parola");
        userService.registerUser(mockUser);

        try {
            userService.loginUser(email, "parola2");
        } catch (IllegalStateException e){
            assert e.getMessage().equals("Passwords doesn't match");
        }
    }

    @Test
    public void changePassword() throws Exception {
        email = "changePassword@gmail.com";

        mockUser.setEmail(email);

        userService.registerUser(mockUser);

        User user = userService.changePassword(password, "password2", mockUser.getEmail());

        assert bCryptPasswordEncoder.matches("password2", user.getPassword());
    }

    @Test
    public void changePassword_failed() throws Exception {
        email = "changePassword_failed@gmail.com";
        mockUser.setEmail(email);
        userService.registerUser(mockUser);
        User user = userService.changePassword("password3", "password2", mockUser.getUsername());
        assert user == null;
    }

    @Test
    public void forgotPassword() throws Exception {
        email = "forgotPassword@gmail.com";
        mockUser.setEmail(email);

        userService.registerUser(mockUser);

        User user = userService.forgotPass(mockUser.getEmail());

        assert bCryptPasswordEncoder.matches("parola2", user.getPassword());
    }
}
