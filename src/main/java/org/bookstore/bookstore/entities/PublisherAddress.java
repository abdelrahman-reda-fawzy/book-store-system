package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Publisher_Address")
@Data
@NoArgsConstructor
public class PublisherAddress {

    @EmbeddedId
    private PublisherAddressId id;

    @MapsId("publisherId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Publisher_ID")
    private Publisher publisher;

    @Column(name = "Address", insertable = false, updatable = false)
    private String address;
}
