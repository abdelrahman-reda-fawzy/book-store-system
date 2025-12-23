package org.bookstore.bookstore.repositories;

import jakarta.transaction.Transactional;
import org.bookstore.bookstore.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {


    // ✅ Find cart by user id
    @Query(value = "SELECT * FROM Carts c WHERE c.user_id = :userId", nativeQuery = true)
    Optional<Cart> findByUser_UserId(@Param("userId") Integer userId);


    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM Carts
        WHERE user_id = :userId;
        """, nativeQuery = true)
    void deleteCartByUserId(@Param("userId") Integer userId);

}

