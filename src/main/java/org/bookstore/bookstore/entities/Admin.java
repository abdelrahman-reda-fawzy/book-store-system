package org.bookstore.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Admin")
@Data
@NoArgsConstructor
public class Admin {
    @Id
    @Column(name = "UserName", length = 50)
    private String userName;

    @Column(name = "Password", length = 255, nullable = false)
    private String password;
}
