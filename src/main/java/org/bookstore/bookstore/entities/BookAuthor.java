package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Book_Author")
@Data
@NoArgsConstructor
public class BookAuthor {

    @EmbeddedId
    private BookAuthorId id;

    @MapsId("isbn")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ISBN")
    private Book book;

    // author name is part of the PK but we still expose it
    @Column(name = "Author_Name", length = 100, insertable = false, updatable = false)
    private String authorName;
}
