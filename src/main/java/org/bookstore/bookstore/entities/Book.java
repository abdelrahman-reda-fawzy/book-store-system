package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Book")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "ISBN", length = 20)
    private String isbn;

    @Column(name = "Title", length = 200, nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Publisher_ID")
    private Publisher publisher;

    @Column(name = "Publication_Year")
    private Integer publicationYear;

    @Column(name = "Price", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "Category", nullable = false, length = 50)
    private BookCategory category;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Threshold")
    private Integer threshold;

    @Column(name = "Image_URL", length = 255)
    private String imageUrl;

    @Column(name = "Description", columnDefinition = "text")
    private String description;

    // optional convenience mappings
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookAuthor> authors;

    @OneToMany(mappedBy = "book")
    private Set<OrderItem> orderItems;

    @OneToMany(mappedBy = "book")
    private Set<PublisherOrder> publisherOrders;
}
