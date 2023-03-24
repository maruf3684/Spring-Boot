package com.practice.mycrud.services;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.practice.mycrud.dao.BookRepository;
import com.practice.mycrud.entities.BookEntity;

import jakarta.transaction.Transactional;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // get all books
    public List<BookEntity> getAllBooks() {
        List<BookEntity> books = this.bookRepository.findAll();
        return books;
    }

    // get single books by id
    public BookEntity getBookById(int id) {
        BookEntity book = null;
        try {
            //book = booklist.stream().filter(e -> e.getId() == id).findFirst().get();
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    // adding books
    public BookEntity addBook(BookEntity book) {
        BookEntity result = this.bookRepository.save(book);
        return result;
    }
    

    public void deleteBook(int id) {
        this.bookRepository.deleteById(id);;
    }

    public List<BookEntity> updateBook(int id, BookEntity newbook) throws Exception {

        BookEntity book = this.bookRepository.findById(id);

        if (book==null){
            throw new Exception("Object not found");
        }

        newbook.setId(id);
        this.bookRepository.save(newbook);

        return getAllBooks();
    }
}
