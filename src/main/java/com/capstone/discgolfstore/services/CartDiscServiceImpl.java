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
import java.util.*;
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
    public String addDiscToCart(CartDiscDto cartDiscDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            CartDisc cartDisc = new CartDisc(cartDiscDto);
            userOptional.ifPresent(cartDisc::setUser);
            cartDiscRepository.saveAndFlush(cartDisc);


            return "Disc added to cart";
        }
        return "Disc could not be added.";

    }

    @Override
    @Transactional
    public String deleteDiscFromCart(Long userId, Long cartDiscId) {

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<CartDisc> cartDiscOptional = cartDiscRepository.findById(cartDiscId);
        if (userOptional.isPresent() && cartDiscOptional.isPresent()) {
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
    @Override
    @Transactional
    public String addDiscToCartById(Long discId, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Disc> discOptional = discRepository.findById(discId);
        List<CartDisc> cartDiscList = cartDiscRepository.findAllByUserEquals(userOptional.get());
        boolean condition = false;
        if(cartDiscList.isEmpty()){
            condition = true;
        }
        else{
            condition = !cartDiscList.stream().anyMatch(cartDisc -> cartDisc.getDiscId() == discId.intValue());

        }
        if(userOptional.isPresent() && discOptional.isPresent() && condition){
            CartDiscDto cartDiscDto = new CartDiscDto();
            cartDiscDto.setDiscId(discId.intValue());
            cartDiscDto.setQuantity(1);
            CartDisc cartDisc = new CartDisc(cartDiscDto);
            userOptional.ifPresent(cartDisc::setUser);
            cartDiscRepository.saveAndFlush(cartDisc);


            return "Disc added to cart";
        }
        return "Disc could not be added.";

    }
    @Override
    @Transactional
    public void addToQuantity(Long cartDiscId){
          Optional<CartDisc> cartDiscOptional = cartDiscRepository.findById(cartDiscId);

                  cartDiscOptional.ifPresent(cartDisc -> {
                      cartDisc.setQuantity(cartDisc.getQuantity() + 1);
                      cartDiscRepository.saveAndFlush(cartDisc);
                  });
          }
          @Override
          @Transactional
          public void subtractFromQuantity(Long cartDiscId){
              Optional<CartDisc> cartDiscOptional = cartDiscRepository.findById(cartDiscId);

              cartDiscOptional.ifPresent(cartDisc -> {
                  if(cartDisc.getQuantity() > 1) {
                      cartDisc.setQuantity(cartDisc.getQuantity() - 1);
                      cartDiscRepository.saveAndFlush(cartDisc);
                  }
              });
          }




//    @Override
//    public Disc findDiscByCartDiscId(Long discId) {
//        Optional<Disc> discOptional = discRepository.findById(discId);
//        Disc disc = null;
//        if (discOptional.isPresent()) {
//            DiscDto discDto = new DiscDto(discOptional.get());
//            disc = new Disc(discDto);
//        }
//        return disc;
//    }
//    @Override
//    public List<DiscDto> getAllDiscsInCart(Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
////        List<DiscDto> discList = Collections.emptyList();
//        if (userOptional.isPresent()) {
//            Set<CartDisc> cartDiscSet = userOptional.get().getCartDiscSet();
//            List<DiscDto> discList = cartDiscRepository;
//            return discList.stream().map(disc -> new DiscDto(disc)).collect(Collectors.toList());
//            cartDiscSet.forEach(cartDisc -> {
//                Long discId = Long.valueOf(cartDisc.getDiscId());
//                Optional<Disc> discOptional = discRepository.findById(discId);
//                if (discOptional.isPresent()) {
//                    DiscDto discDto = new DiscDto(discOptional.get());
//                    discList.add(discDto);
//                }
//            });
////
//        }
//        return discList;
//    }

//    @Override
    public List<DiscDto> getAllDiscsInCart(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<DiscDto> discDtoList = new ArrayList<DiscDto>();
        if (userOptional.isPresent()) {
            List<CartDisc> cartDiscList = cartDiscRepository.findAllByUserEquals(userOptional.get());
//            return cartDiscList.stream().map(cartDisc -> new CartDiscDto(cartDisc)).collect(Collectors.toList());
//            Set<CartDisc> cartDiscSet = userOptional.get().getCartDiscSet();
            cartDiscList.forEach(cartDisc -> {
                Long discId = Long.valueOf(cartDisc.getDiscId());
                Optional<Disc> discOptional = discRepository.findById(discId);
                if (discOptional.isPresent()) {
                    DiscDto discDto = new DiscDto(discOptional.get());
                    discDtoList.add(discDto);
                }
            });

        }
//        CartDiscDto cartDiscDto = new CartDiscDto();
//        cartDiscDto.setQuantity(5);
//        cartDiscDto.setDiscId(1);
////        userOptional.ifPresent(cartDisc::setUser);
//        CartDisc cartDisc = new CartDisc(cartDiscDto);
//       List<CartDiscDto> cartDiscDtoList = new ArrayList<CartDiscDto>();
//       cartDiscDtoList.add(cartDiscDto);
        return discDtoList;
    }

    public List<CartDiscDto> getAllCartDiscs(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        List<CartDiscDto> cartDiscDtoList = new ArrayList<>();
        if (userOptional.isPresent()) {
            List<CartDisc> cartDiscList = cartDiscRepository.findAllByUserEquals(userOptional.get());
            cartDiscList.forEach(cartDisc -> {
                CartDiscDto cartDiscDto = new CartDiscDto(cartDisc);
                cartDiscDtoList.add(cartDiscDto);
            });

        }
        return cartDiscDtoList;
    }


}
