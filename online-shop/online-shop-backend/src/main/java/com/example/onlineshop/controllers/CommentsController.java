package com.example.onlineshop.controllers;

import com.example.onlineshop.security.models.Comments;
import com.example.onlineshop.security.models.ShopCart;
import com.example.onlineshop.security.models.User;
import com.example.onlineshop.security.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addComments")
    public ResponseEntity<Comments> addComments(@RequestBody Comments comments)
    {
        return ResponseEntity.ok(commentsService.addComments(comments));
    }

    @GetMapping("/getAllComments/{productId}")
    public ResponseEntity<List<Comments>> getAllComments(@PathVariable Long productId)
    {
        return ResponseEntity.ok(commentsService.getAllComments(productId));
    }

    @PatchMapping("/updateComment/{newComment}")
    public ResponseEntity<Comments> updateComments(@RequestBody Comments comments, @PathVariable String newComment)
    {
        return ResponseEntity.ok(commentsService.updateComments(comments,newComment));
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public void deleteComment(@PathVariable Long commentId)
    {
        commentsService.deleteComments(commentId);
    }

}
