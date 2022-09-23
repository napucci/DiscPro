package com.capstone.discgolfstore.services;

import com.capstone.discgolfstore.dtos.CartDiscDto;

import javax.transaction.Transactional;
import java.util.List;

public interface CartDiscService {
    @Transactional
    String addDiscToCart(CartDiscDto cartDiscDto, Long userId);

    @Transactional
    String deleteDiscFromCart(Long cartDiscId);

    List<CartDiscDto> getCartData(Long userId);
}
