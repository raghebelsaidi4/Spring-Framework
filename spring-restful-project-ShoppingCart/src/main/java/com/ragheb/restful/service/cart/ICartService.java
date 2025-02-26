package com.ragheb.restful.service.cart;

import com.ragheb.restful.model.Cart;
import com.ragheb.restful.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
}
