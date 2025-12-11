package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bookstore.bookstore.enums.PublisherOrderStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "Publisher_Order")
@Data
@NoArgsConstructor
public class PublisherOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ISBN")
    private Book book;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Order_Date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status", length = 20)
    private PublisherOrderStatus status;
}
