package com.ComeOnBaby.service;


import com.ComeOnBaby.dao.BlogDao;
import com.ComeOnBaby.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("blogService")
public class BlogServiceImpl implements BlogService{

    private BlogDao blogDao;

    @Autowired(required = true)
    public void setCaseDao(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @Override
    @Transactional
    public void addNewBlog(Blog blog) {
        blogDao.create(blog);
    }

    @Override
    @Transactional
    public void updateBlog(Blog blog) {
        blogDao.update(blog);
    }

    @Override
    @Transactional
    public void deleteBlog(Blog blog) {
        blogDao.delete(blog);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Blog> getAllBlog() {
        return blogDao.findAll();
    }

    @Override
    @Transactional
    public Blog findById(Long id) {return blogDao.read(id);}

    @Override
    @Transactional(readOnly = true)
    public List<Blog> findBlogByType(Integer type){return blogDao.findBlogByType(type);}
}