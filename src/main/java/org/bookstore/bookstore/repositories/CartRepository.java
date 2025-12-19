package org.bookstore.bookstore.repositories;

import org.bookstore.bookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {


//  @Query(value = "SELECT * FROM Carts c WHERE c.user_id = :userId", nativeQuery = true)
    Optional<Cart> findByUser_UserId(@Param("userId") Integer userId);




}

