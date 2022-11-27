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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class UserTest {

    private User mockUser;

    @Autowired
    private UserService userService;

    private String email = "test@gmail.com";
    private String firstName = "test";
    private String lastName = "test";

    private String password = "password";

    @Before
    public void setUp() {
        mockUser.setEmail(email);
        mockUser.setFirstName(firstName);
        mockUser.setLastName(lastName);
        mockUser.setPassword(password);
    }

    @Test
    public void register() throws Exception {
        userService.registerUser(mockUser);

       UserDetails user = userService.loadUserByUsername(mockUser.getUsername());

       assert user != null;
    }

    @Test
    public void register_login() throws Exception {
        userService.registerUser(mockUser);

        userService.loginUser(email, password);
    }

    @Test
    public void register_loginFail() throws Exception {
        userService.registerUser(mockUser);

        try {
            userService.loginUser(email, password);
        } catch (IllegalStateException e){
            assert e.getMessage().equals("Acest email nu exista!");
        }
    }

    @Test
    public void changePassword() throws Exception {
        userService.registerUser(mockUser);

       userService.changePassword(password, "password2", mockUser.getUsername());

       assert mockUser.getPassword().equals("password2");
    }

    @Test
    public void forgotPassword() throws Exception {
        userService.registerUser(mockUser);

        userService.forgotPass(mockUser.getEmail());

        assert mockUser.getPassword().equals("password2");
    }
}
