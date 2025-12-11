package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Order_Item")
@Data
@NoArgsConstructor
public class OrderItem {

    @EmbeddedId
    private OrderItemId id;

    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Order_ID")
    private CustomerOrder order;

    @MapsId("isbn")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ISBN")
    private Book book;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Unit_Price", precision = 10, scale = 2)
    private java.math.BigDecimal unitPrice;
}
