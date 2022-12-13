package com.example.onlineshop;

import com.example.onlineshop.security.models.Comments;
import com.example.onlineshop.security.models.Product;
import com.example.onlineshop.security.services.CommentsService;
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
public class CommentsTest {

    private Comments mockComment = new Comments();
    private Product mockProduct = new Product();

    @Autowired
    private CommentsService commentsService;

    private String description = "test";

    @Before
    public void setUp() {
       mockComment.setDescription(description);
       mockProduct.setId(1L);
    }

    @Test
    public void checkAdd(){
        commentsService.addComments(mockComment);

        for(Comments c: commentsService.getAllComments(1L)){
            assert c.getDescription().equals("test");
        }
    }

    @Test
    public void update(){
        commentsService.addComments(mockComment);

        commentsService.updateComments(mockComment, "test2");

        for(Comments c: commentsService.getAllComments(1L)){
            assert c.getDescription().equals("test");
        }
    }

    @Test
    public void delete(){
        commentsService.addComments(mockComment);

        commentsService.deleteComments(1L);

        Boolean exists = false;

        for(Comments c: commentsService.getAllComments(1L)){
            if(c.getDescription().equals("test")){
                exists = true;
                break;
            }
        }

        assert !exists;
    }
}
