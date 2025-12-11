package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "Publisher")
@Data
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Publisher_ID")
    private Integer publisherId;

    @Column(name = "Name", length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<PublisherAddress> addresses;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    private Set<PublisherPhone> phones;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;
}
