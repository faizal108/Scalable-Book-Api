package com.faizal108.bookService.utils.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class UpdateBookModel implements Serializable {

    private UUID id;
    private String title;
    private String author;
    private String genre;
}
