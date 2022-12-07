package com.example.onlineshop.security.services;

import com.example.onlineshop.security.models.Comments;
import com.example.onlineshop.security.models.ShopCart;
import com.example.onlineshop.security.repositories.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public Comments addComments(Comments comments)
    {
        commentsRepository.save(comments);
        return comments;
    }

    public List<Comments> getAllComments(Long productId)
    {
        return commentsRepository.findCommentsByProduct_Id(productId);
    }

    public Comments updateComments(Comments comments, String newComments)
    {
        Optional<Comments> commentsFound = commentsRepository.findCommentsByClientId(comments.getId());
        if(commentsFound.isPresent())
        {
            comments.setDescription(newComments);
            return commentsRepository.save(comments);
        }
        else
        {
            throw new NoSuchElementException(String.valueOf(comments));
        }
    }

    public void deleteComments(Long clintId)
    {
        Optional<Comments> commentsFound = commentsRepository.findCommentsByClientId(clintId);
        if(commentsFound.isPresent())
        {
            commentsRepository.delete(commentsFound.get());
        }
    }

}
