package com.capstone.discgolfstore.controllers;

import com.capstone.discgolfstore.dtos.CartDiscDto;
import com.capstone.discgolfstore.dtos.DiscDto;
import com.capstone.discgolfstore.services.CartDiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartDiscController {

    @Autowired
    private CartDiscService cartDiscService;

    @GetMapping("/{cartDiscId}")
    public List<CartDiscDto> getCart(@PathVariable Long cartDiscId){
        return cartDiscService.getCartData(cartDiscId);
    }

    @PostMapping("/{cartDiscId}/{discId}")
    public void addDisc(@RequestBody CartDiscDto cartDiscDto,@PathVariable Long cartDiscId, @PathVariable Long discId){
        cartDiscService.addDiscToCart(cartDiscDto,cartDiscId, discId);
    }

    @DeleteMapping("/{cartDiscId}")
    public void deleteDisc (@PathVariable Long cartDiscId){
        cartDiscService.deleteDiscFromCart(cartDiscId);
    }



}
