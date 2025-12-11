package org.bookstore.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class PublisherAddressId implements Serializable {
    @Column(name = "Publisher_ID")
    private Integer publisherId;

    @Column(name = "Address", length = 255)
    private String address;
}
