package org.bookstore.bookstore.mappers;

import org.bookstore.bookstore.dtos.CartDto;
import org.bookstore.bookstore.dtos.CartItemDto;
import org.bookstore.bookstore.entities.Cart;
import org.bookstore.bookstore.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface CartMapper {
    @Mapping(source = "id", target = "cartId")
    CartDto toDto(Cart cart);

    @Mapping(target = "bookId", source = "book.bookId")
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target = "price", source = "book.sellingPrice")
    @Mapping(target = "subTotal", expression = "java(calculateSubTotal(item))")
    CartItemDto toDto(CartItem cartItem);

    default BigDecimal calculateSubTotal(CartItem item) {
        if (item.getBook() == null || item.getBook().getSellingPrice() == null) {
            return BigDecimal.ZERO;
        }
        return item.getBook().getSellingPrice()
                .multiply(BigDecimal.valueOf(item.getQuantity()));
    }

    default BigDecimal calculateTotal(List<CartItem> items) {
        if (items == null) return BigDecimal.ZERO;
        return items.stream()
                .map(this::calculateSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
