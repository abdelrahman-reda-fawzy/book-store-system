package org.bookstore.bookstore.services;

import lombok.AllArgsConstructor;
import org.bookstore.bookstore.dtos.CartDto;
import org.bookstore.bookstore.entities.Book;
import org.bookstore.bookstore.entities.Cart;
import org.bookstore.bookstore.entities.CartItem;
import org.bookstore.bookstore.entities.User;
import org.bookstore.bookstore.exceptions.BusinessException;
import org.bookstore.bookstore.mappers.CartMapper;
import org.bookstore.bookstore.repositories.BookRepository;
import org.bookstore.bookstore.repositories.CartItemRepository;
import org.bookstore.bookstore.repositories.CartRepository;
import org.bookstore.bookstore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;

    // ✅ ADD TO CART (native, safe, single source of truth)
    public void addToCart(Integer userId, int bookId, int quantity) {

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseGet(() -> createCartForUser(userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BusinessException("Book not found"));

        cartItemRepository.addToCart(
                cart.getId(),
                book.getBookID().longValue(),
                quantity
        );
    }

    // ✅ GET CART
    public CartDto getCartDetails(Integer userId) {
        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new BusinessException("Cart not found"));

        return cartMapper.toDto(cart);
    }

    // ✅ REMOVE ITEM (by bookId, not cartItemId)
    public void removeItem(Integer userId, Long bookId) {

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new BusinessException("Cart not found"));

        cartItemRepository.removeFromCart(cart.getId(), bookId);
    }

    public void decrementQuantity(Integer userId, Long bookId) {
        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new BusinessException("Cart not found"));

        cartItemRepository.decrementQuantity(cart.getId(), bookId);
        cartItemRepository.removeIfZero(cart.getId(), bookId);
    }


    public void clearCart(Integer userId) {

        Cart cart = cartRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new BusinessException("Cart not found"));

        cartRepository.deleteCartByUserId(userId);
    }

    // ✅ CREATE CART
    private Cart createCartForUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));

        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }


}
