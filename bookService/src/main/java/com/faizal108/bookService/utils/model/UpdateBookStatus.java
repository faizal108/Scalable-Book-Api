package com.faizal108.bookService.utils.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
public class UpdateBookStatus implements Serializable {
    private UUID bookId;
    private UUID userId;
}
