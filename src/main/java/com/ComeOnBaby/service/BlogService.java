package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Blog;

import java.util.List;


public interface BlogService {
    void addNewBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlog(Blog blog);
    List<Blog> getAllBlog();
    List<Blog> findBlogByType(Integer type);
}
