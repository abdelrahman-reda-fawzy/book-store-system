package org.bookstore.bookstore.repositories;

import org.bookstore.bookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query(value = "SELECT * FROM Cart c WHERE c.id = ?1", nativeQuery = true)
    Optional<Cart> findById(Long id);




}

