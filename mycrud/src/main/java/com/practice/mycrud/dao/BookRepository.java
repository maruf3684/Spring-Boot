package com.practice.mycrud.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.mycrud.entities.BookEntity;


public interface BookRepository extends JpaRepository<BookEntity,Integer>{
    public BookEntity findById(int id);
    public BookEntity deleteById(int id);
}
