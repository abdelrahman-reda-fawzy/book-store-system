package org.bookstore.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class OrderItemId implements Serializable {
    @Column(name = "Order_ID")
    private Integer orderId;

    @Column(name = "ISBN", length = 20)
    private String isbn;
}
