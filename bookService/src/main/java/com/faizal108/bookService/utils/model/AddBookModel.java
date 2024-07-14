package com.faizal108.bookService.utils.model;

import com.faizal108.commanEntity.book.Book;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AddBookModel implements Serializable {

    private String title;
    private String author;
    private String genre;
    private boolean isAvailable = true;

    public Book ConvertToBook(){
        Book b = new Book();
        b.setTitle(this.title);
        b.setAuthor(this.author);
        b.setGenre(this.genre);
        b.setAvailable(this.isAvailable);
        return b;
    }
}
