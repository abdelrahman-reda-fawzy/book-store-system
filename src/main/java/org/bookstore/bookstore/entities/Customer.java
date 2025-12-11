package org.bookstore.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "UserName", length = 50)
    private String userName;

    @Column(name = "Password", length = 255, nullable = false)
    private String password;

    @Column(name = "First_Name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "Last_Name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "Email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "Phone", length = 20)
    private String phone;

    @Column(name = "Shipping_Address", length = 255)
    private String shippingAddress;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerOrder> orders;

    @OneToMany(mappedBy = "customer")
    private Set<ShoppingCart> shoppingCart;
}
