package org.bookstore.bookstore.repositories;

import org.bookstore.bookstore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}