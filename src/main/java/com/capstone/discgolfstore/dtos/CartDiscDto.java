package com.capstone.discgolfstore.dtos;
import com.capstone.discgolfstore.entities.CartDisc;
import com.capstone.discgolfstore.entities.Disc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDiscDto {

    private Long id;
    private int quantity;
    private int discId;

    public CartDiscDto(CartDisc cartDisc){
        if(cartDisc.getQuantity() > 0){
            this.quantity = cartDisc.getQuantity();
        }
        if(cartDisc.getDiscId() > 0){
            this.discId = cartDisc.getDiscId();
        }
        if(cartDisc.getId() > 0){
            this.id = cartDisc.getId();
        }
    }


}
