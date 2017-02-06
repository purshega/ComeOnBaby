package com.ComeOnBaby.dao;




import com.ComeOnBaby.model.Images;

import java.util.List;

public interface ImagesDao {
    Long create(Images images);
    Images read(Long id);
    void update(Images images);
    void delete(Images images);
    List<Images> findAll();
}
