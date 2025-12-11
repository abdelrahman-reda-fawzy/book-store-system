package org.bookstore.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class BookAuthorId implements Serializable {
    @Column(name = "ISBN", length = 20)
    private String isbn;

    @Column(name = "Author_Name", length = 100)
    private String authorName;
}
