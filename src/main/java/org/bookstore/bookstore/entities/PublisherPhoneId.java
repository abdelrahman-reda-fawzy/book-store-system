package org.bookstore.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Embeddable
@Data
public class PublisherPhoneId implements Serializable {
    @Column(name = "Publisher_ID")
    private Integer publisherId;

    @Column(name = "Phone_Number", length = 20)
    private String phoneNumber;
}
