package com.capstone.discgolfstore.services;

import com.capstone.discgolfstore.dtos.CartDiscDto;
import com.capstone.discgolfstore.entities.CartDisc;
import com.capstone.discgolfstore.entities.Disc;

import javax.transaction.Transactional;
import java.util.List;

public interface CartDiscService {
    @Transactional
    String addDiscToCart(CartDiscDto cartDiscDto, Long userId);

    @Transactional
    String deleteDiscFromCart(Long userId, Long cartDiscId);

    List<CartDiscDto> getCartData(Long userId);

    @Transactional
    String addDiscToCartById(Long discId, Long userId);

    List<Disc> getAllDiscsInCart(Long userId);

    Disc findDiscByCartDiscId(Long discId);
}
