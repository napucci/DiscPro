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

//    @GetMapping("/{userId}")
//    public List<CartDiscDto> getCart(@PathVariable Long userId){
//        return cartDiscService.getCartData(userId);
//    }

    @PostMapping("/{userId}")
    public void addDisc(@RequestBody CartDiscDto cartDiscDto,@PathVariable Long userId){
        cartDiscService.addDiscToCart(cartDiscDto,userId);
    }

    @DeleteMapping("/{userId}/{cartDiscId}")
    public void deleteDisc (@PathVariable Long userId, @PathVariable Long cartDiscId){
        cartDiscService.deleteDiscFromCart(userId, cartDiscId);
    }

    @PostMapping("/from/{userId}")
    public void addDiscById(@RequestBody Long discId,@PathVariable Long userId){
        cartDiscService.addDiscToCartById(discId,userId);
    }
//    @GetMapping("/{discId}")
//    public void findDiscByCartDiscId(@RequestBody Long discId){
//        cartDiscService.findDiscByCartDiscId(discId);
//    }
    @GetMapping("/using/{userId}")
    public List<DiscDto> getDiscsInCart(@PathVariable Long userId){
        return cartDiscService.getAllDiscsInCart(userId);
    }

    @GetMapping("/userdiscs/{userId}")
    public List<CartDiscDto> getAllUserDiscs(@PathVariable Long userId){
        return cartDiscService.getAllCartDiscs(userId);
    }

    @PutMapping("/{cartDiscId}")
    public void moreQuantity(@PathVariable Long cartDiscId){
        cartDiscService.addToQuantity(cartDiscId);
    }

    @PutMapping("/subtract/{cartDiscId}")
    public void lessQuantity(@PathVariable Long cartDiscId){
        cartDiscService.subtractFromQuantity(cartDiscId);
    }
}
