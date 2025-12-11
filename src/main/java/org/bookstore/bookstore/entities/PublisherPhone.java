package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Publisher_Phone")
@Data
@NoArgsConstructor
public class PublisherPhone {

    @EmbeddedId
    private PublisherPhoneId id;

    @MapsId("publisherId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Publisher_ID")
    private Publisher publisher;

    @Column(name = "Phone_Number", insertable = false, updatable = false)
    private String phoneNumber;
}
