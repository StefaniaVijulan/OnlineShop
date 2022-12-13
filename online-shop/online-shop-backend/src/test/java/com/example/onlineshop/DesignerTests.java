package com.example.onlineshop;

import com.example.onlineshop.controllers.DesignerController;
import com.example.onlineshop.security.models.Designer;
import com.example.onlineshop.security.repositories.DesignerRepository;
import com.example.onlineshop.security.services.DesignerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class DesignerTests {

    private Designer mockDesigner = new Designer();

    @Autowired
    private DesignerService designerService;

    private String email = "test@gmail.com";
    private String firstName = "test";
    private String lastName = "test";

    private String password = "password";

    @Before
    public void setUp() {
        mockDesigner.setEmail(email);
        mockDesigner.setFirstNameDesigner(firstName);
        mockDesigner.setLastNameDesigner(lastName);
        mockDesigner.setPasswordDesigner(password);
    }

    @Test
    public void register() throws Exception {
        designerService.addDesigner(mockDesigner);

        Boolean exists = false;
        for (Designer designer : designerService.getDesigners()) {
            if (designer.getEmail().equals(email) && designer.getFirstNameDesigner().equals(firstName)
                    && designer.getLastNameDesigner().equals(lastName)) {
                exists = true;
                break;
            }
        }
        assert exists;
    }

    @Test
    public void register_login() throws Exception {
        designerService.addDesigner(mockDesigner);

        designerService.loginDesigner(email, "password");
    }

    @Test
    public void register_login_shouldFail() throws Exception {
        designerService.addDesigner(mockDesigner);

        try {
            designerService.loginDesigner("fakeEmail", "parola");
        } catch (IllegalStateException e){
            assert e.getMessage().equals("User don't exist!");
        }
    }
}
