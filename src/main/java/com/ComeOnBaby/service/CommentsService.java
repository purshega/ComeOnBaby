package com.ComeOnBaby.service;



import com.ComeOnBaby.model.Comments;

import java.util.List;

public interface CommentsService {
    void addNewComments(Comments comments);
    void updateComments(Comments comments);
    void deleteComments(Comments comments);
    List<Comments> getAllComments();
}
