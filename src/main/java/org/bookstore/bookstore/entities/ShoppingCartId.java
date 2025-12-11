package org.bookstore.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ShoppingCartId implements Serializable {
    @Column(name = "Customer_UserName", length = 50)
    private String customerUserName;

    @Column(name = "ISBN", length = 20)
    private String isbn;
}
