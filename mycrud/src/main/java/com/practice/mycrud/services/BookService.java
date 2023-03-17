package com.practice.mycrud.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.practice.mycrud.entities.BookEntity;

@Component
public class BookService {
    private static List<BookEntity> booklist = new ArrayList<>();

    // class load hole static block chole
    static {
        booklist.add(new BookEntity(1, "java", "abc"));
        booklist.add(new BookEntity(2, "python", "def"));
        booklist.add(new BookEntity(3, "node", "xyz"));
    }

    // get all books
    public List<BookEntity> getAllBooks() {
        return booklist;
    }

    // get single books by id
    public BookEntity getBookById(int id) {
        BookEntity book = null;
        try {
            book = booklist.stream().filter(e -> e.getId() == id).findFirst().get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return book;
    }

    // adding books
    public BookEntity addBook(BookEntity book) {
        booklist.add(book);
        return book;
    }

    public void deleteBook(int id) {
        booklist = booklist.stream().filter(book -> {
            if (book.getId() != id) {
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }

    public List<BookEntity> updateBook(int id, BookEntity newbook) throws Exception {

        BookEntity book = this.getBookById(id);

        if (book==null){
            throw new Exception("Object not found");
        }

        return booklist.stream().map(b -> {
            if (b.getId() != id) {
                return b;
            } else {
                b.setAuthor(newbook.getTitle());
                b.setTitle(newbook.getAuthor());
                return b;
            }
        }).collect(Collectors.toList());
    }
}
