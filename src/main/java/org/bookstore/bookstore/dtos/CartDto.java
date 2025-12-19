package org.bookstore.bookstore.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bookstore.bookstore.entities.CartItem;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CartDto {

    private Long cartId;
    private List<CartItemDto> cartItems;

}
