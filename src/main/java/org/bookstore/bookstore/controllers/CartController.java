package org.bookstore.bookstore.controllers;


import lombok.AllArgsConstructor;
import org.bookstore.bookstore.dtos.CartDto;
import org.bookstore.bookstore.services.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController("/cart")
public class CartController {

    private final CartService cartService;


    @GetMapping("/${userId}")
    public ResponseEntity<CartDto> viewCart(@PathVariable Integer userId) {
        CartDto cartDto = cartService.getCartDetails(userId);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("${userId}/add")
    public ResponseEntity<Void> addToCart(@PathVariable Integer userId, @RequestParam Long bookId, @RequestParam int quantity) {
        cartService.AddToCart(userId, bookId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("${userId}/${itemId}/remove")
    public ResponseEntity<String> removeFromCart(@PathVariable Integer userId, @PathVariable Long itemId) {
        cartService.removeItem(userId, itemId);
        return ResponseEntity.ok("Item removed from cart");
    }

}
