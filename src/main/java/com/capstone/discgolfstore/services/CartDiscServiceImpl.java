package com.capstone.discgolfstore.services;

import com.capstone.discgolfstore.dtos.CartDiscDto;
import com.capstone.discgolfstore.dtos.DiscDto;
import com.capstone.discgolfstore.entities.CartDisc;
import com.capstone.discgolfstore.entities.Disc;
import com.capstone.discgolfstore.entities.User;
import com.capstone.discgolfstore.repositories.CartDiscRepository;
import com.capstone.discgolfstore.repositories.DiscRepository;
import com.capstone.discgolfstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartDiscServiceImpl implements CartDiscService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiscRepository discRepository;
    @Autowired
    private CartDiscRepository cartDiscRepository;

    @Override
    @Transactional
    public String addDiscToCart(CartDiscDto cartDiscDto, Long discId, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Disc> discOptional = discRepository.findById(discId);
        if(userOptional.isPresent() && discOptional.isPresent()){
            CartDisc cartDisc = new CartDisc(cartDiscDto);
            userOptional.ifPresent(cartDisc::setUser);
            cartDiscRepository.saveAndFlush(cartDisc);


            return "Disc added to cart";
        }
        return "Disc could not be added.";

    }

    @Override
    @Transactional
    public String deleteDiscFromCart(Long cartDiscId) {

        Optional<CartDisc> cartDiscOptional = cartDiscRepository.findById(cartDiscId);
        if ( cartDiscOptional.isPresent()) {
            cartDiscOptional.ifPresent(cartDisc -> cartDiscRepository.delete(cartDisc));
            return "Disc removed from cart";
        }
        return "Disc could not be removed.";
    }

    @Override
    public List<CartDiscDto> getCartData(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            List<CartDisc> cartdiscList = cartDiscRepository.findAllByUserEquals(userOptional.get());
            return  cartdiscList.stream().map(cartDisc -> new CartDiscDto(cartDisc)).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

}
