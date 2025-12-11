package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Customer_Order")
@Data
@NoArgsConstructor
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_ID")
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Customer_UserName")
    private Customer customer;

    @Column(name = "Order_Date")
    private LocalDateTime orderDate;

    @Column(name = "Total_Amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "Credit_Card_Number", length = 20)
    private String creditCardNumber;

    @Column(name = "Expiry_Date", length = 5)
    private String expiryDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items;
}
